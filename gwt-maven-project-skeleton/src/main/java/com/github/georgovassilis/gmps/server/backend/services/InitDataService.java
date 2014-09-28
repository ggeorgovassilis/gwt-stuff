package com.github.georgovassilis.gmps.server.backend.services;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import com.github.georgovassilis.gmps.common.api.AddressBookService;
import com.github.georgovassilis.gmps.common.domain.AddressDto;
import com.github.georgovassilis.gmps.common.domain.ContactDto;
import com.github.georgovassilis.gmps.common.domain.PersonalDetailsDto;

@Transactional
public class InitDataService{

	@Resource
	AddressBookService service;
	
	ContactDto make(String name, String phoneNumber, String...addresses){
		ContactDto contact = new ContactDto();
		PersonalDetailsDto personalDetails = new PersonalDetailsDto();
		contact.setPersonalDetails(personalDetails);
		personalDetails.setName(name);
		personalDetails.setPhoneNumber(phoneNumber);
		contact = service.newContact(contact.getPersonalDetails());
		for (String addr:addresses){
			AddressDto address = new AddressDto();
			address.setCity("Testville");
			address.setStreetAndNumber(addr);
			address = service.newAddressForContact(contact.getPersonalDetails().getId(), address);
			contact.getAddresses().add(address);
		}
		return contact;
	}
	
	@PostConstruct
	public void init(){
		make("Test entry 1", "+0011234567-1","Test Str. 1");
		make("Test entry 2", "+0011234567-2","Test Str. 2 A", "Test Str. 2 B");
		make("Test entry 3", "+0011234567-3","Test Str. 3");
		make("Test entry 4", "","Test Str. 4");
		make("Test entry 5", "+0011234567-1");
	}
}
