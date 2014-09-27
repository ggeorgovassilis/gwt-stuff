package com.github.georgovassilis.gmps.server.backend.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.georgovassilis.gmps.common.api.AddressBookService;
import com.github.georgovassilis.gmps.common.domain.AddressDto;
import com.github.georgovassilis.gmps.common.domain.PersonalDetailsDto;
import com.github.georgovassilis.gmps.common.domain.ContactDto;
import com.github.georgovassilis.gmps.server.backend.daos.AddressDao;
import com.github.georgovassilis.gmps.server.backend.daos.ContactDao;
import com.github.georgovassilis.gmps.server.backend.domain.Address;
import com.github.georgovassilis.gmps.server.backend.domain.Contact;

@Service("AddressBookService")
@Transactional
public class AddressBookServiceImpl implements AddressBookService {

	@Resource
	private ContactDao contactDao;
	
	@Resource
	private AddressDao addressDao;

	private PersonalDetailsDto convert(Contact c){
		if (c == null)
			return null;
		PersonalDetailsDto personalDetails = new PersonalDetailsDto();
		BeanUtils.copyProperties(c,  personalDetails);
		return personalDetails;
	}
	
	private AddressDto convert(Address a){
		if (a == null)
			return null;
		AddressDto dto = new AddressDto();
		BeanUtils.copyProperties(a, dto);
		return dto;
	}
	
	@Override
	public List<PersonalDetailsDto> listContacts() {
		List<Contact> contacts = contactDao.findAll();
		List<PersonalDetailsDto> personalDetails = new ArrayList<PersonalDetailsDto>();
		for (Contact c:contacts)
			personalDetails.add(convert(c));
		return personalDetails;
	}

	@Override
	public ContactDto newContact(PersonalDetailsDto personalDetails) {
		Contact c = new Contact();
		BeanUtils.copyProperties(personalDetails, c);
		c = contactDao.saveAndFlush(c);
		ContactDto dto = new ContactDto();
		dto.setPersonalDetails(convert(c));
		return dto;
	}

	@Override
	public ContactDto updateContactDetails(PersonalDetailsDto contactDetails) {
		Contact c = contactDao.findOne(contactDetails.getId());
		BeanUtils.copyProperties(contactDetails, c);
		c = contactDao.saveAndFlush(c);
		return getContact(c.getId());
	}

	@Override
	public ContactDto getContact(Long id) {
		Contact c = contactDao.findOne(id);
		if (c == null)
			return null;
		ContactDto dto = new ContactDto();
		PersonalDetailsDto personalDetails = convert(c);
		dto.setPersonalDetails(personalDetails);
		for (Address address:c.getAddresses()){
			AddressDto adto = convert(address);
			dto.getAddresses().add(adto);
		}
		return dto;
	}

	@Override
	public void deleteContact(Long id) {
		contactDao.delete(id);
	}

	@Override
	public AddressDto newAddressForContact(Long contactId, AddressDto addressDto) {
		Contact contact = contactDao.findOne(contactId);
		Address address = new Address();
		BeanUtils.copyProperties(addressDto, address);
		address.setContact(contact);
		address = addressDao.saveAndFlush(address);
		contact.getAddresses().add(address);
		contactDao.saveAndFlush(contact);
		return convert(address);
	}

	@Override
	public AddressDto update(AddressDto address) {
		Address a = addressDao.findOne(address.getId());
		BeanUtils.copyProperties(address, a);
		a = addressDao.saveAndFlush(a);
		return convert(a);
	}

	@Override
	public void deleteAddress(Long addressId) {
		Address address = addressDao.findOne(addressId);
		address.getContact().getAddresses().remove(address);
		contactDao.saveAndFlush(address.getContact());
	}

}
