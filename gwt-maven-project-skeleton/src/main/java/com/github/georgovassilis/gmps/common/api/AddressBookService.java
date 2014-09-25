package com.github.georgovassilis.gmps.common.api;

import java.util.List;

import com.github.georgovassilis.gmps.common.domain.AddressDto;
import com.github.georgovassilis.gmps.common.domain.ContactCoverDto;
import com.github.georgovassilis.gmps.common.domain.ContactDto;

public interface AddressBookService {

	List<ContactCoverDto> listContacts();
	ContactDto newContact();
	ContactCoverDto updateContactDetails(ContactCoverDto contactDetails);
	ContactDto getContact(Long id);
	void deleteContact(Long id);
	
	AddressDto newAddressForContact(Long contactId);
	AddressDto update(AddressDto address);
	void deleteAddress(Long addressId);
}
