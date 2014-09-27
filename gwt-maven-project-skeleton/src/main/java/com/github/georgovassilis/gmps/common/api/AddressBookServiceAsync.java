package com.github.georgovassilis.gmps.common.api;

import java.util.List;

import com.github.georgovassilis.gmps.common.domain.AddressDto;
import com.github.georgovassilis.gmps.common.domain.PersonalDetailsDto;
import com.github.georgovassilis.gmps.common.domain.ContactDto;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AddressBookServiceAsync {
	
	void deleteAddress(Long addressId, AsyncCallback<Void> callback);

	void deleteContact(Long id, AsyncCallback<Void> callback);

	void getContact(Long id, AsyncCallback<ContactDto> callback);

	void listContacts(AsyncCallback<List<PersonalDetailsDto>> callback);

	void newAddressForContact(Long contactId, AddressDto address, AsyncCallback<AddressDto> callback);

	void newContact(PersonalDetailsDto contact, AsyncCallback<ContactDto> callback);

	void update(AddressDto address, AsyncCallback<AddressDto> callback);

	void updateContactDetails(PersonalDetailsDto contactDetails,
			AsyncCallback<ContactDto> callback);
}