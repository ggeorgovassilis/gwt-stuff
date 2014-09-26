package com.github.georgovassilis.gmps.common.api;

import java.util.List;

import com.github.georgovassilis.gmps.common.domain.AddressDto;
import com.github.georgovassilis.gmps.common.domain.ContactCoverDto;
import com.github.georgovassilis.gmps.common.domain.ContactDto;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("../../api/addressbook")
public interface AddressBookService extends RemoteService{

	List<ContactCoverDto> listContacts();
	ContactDto newContact(ContactCoverDto contact);
	ContactCoverDto updateContactDetails(ContactCoverDto contactDetails);
	ContactDto getContact(Long id);
	void deleteContact(Long id);
	
	AddressDto newAddressForContact(Long contactId, AddressDto address);
	AddressDto update(AddressDto address);
	void deleteAddress(Long addressId);
}
