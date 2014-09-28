package com.github.georgovassilis.gmps.common.api;

import java.util.List;

import com.github.georgovassilis.gmps.common.domain.AddressDto;
import com.github.georgovassilis.gmps.common.domain.PersonalDetailsDto;
import com.github.georgovassilis.gmps.common.domain.ContactDto;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("../../api/addressbook")
public interface AddressBookService extends RemoteService{

	List<PersonalDetailsDto> listContacts();
	ContactDto newContact(PersonalDetailsDto contact);
	ContactDto updateContactDetails(PersonalDetailsDto contactDetails);
	ContactDto getContact(Long id);
	void deleteContact(Long id);
	
	AddressDto newAddressForContact(Long contactId, AddressDto address);
	AddressDto update(AddressDto address);
	ContactDto deleteAddress(Long addressId);
}
