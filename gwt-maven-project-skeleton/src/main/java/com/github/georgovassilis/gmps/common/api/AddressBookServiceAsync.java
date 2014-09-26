package com.github.georgovassilis.gmps.common.api;

import java.util.List;

import com.github.georgovassilis.gmps.common.domain.AddressDto;
import com.github.georgovassilis.gmps.common.domain.ContactCoverDto;
import com.github.georgovassilis.gmps.common.domain.ContactDto;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AddressBookServiceAsync {
	void deleteAddress(Long addressId, AsyncCallback<Void> callback);

	void deleteContact(Long id, AsyncCallback<Void> callback);

	void getContact(Long id, AsyncCallback<ContactDto> callback);

	void listContacts(AsyncCallback<List<ContactCoverDto>> callback);

	void newAddressForContact(Long contactId, AddressDto address, AsyncCallback<AddressDto> callback);

	void newContact(ContactCoverDto contact, AsyncCallback<ContactCoverDto> callback);

	void update(AddressDto address, AsyncCallback<AddressDto> callback);

	void updateContactDetails(ContactCoverDto contactDetails,
			AsyncCallback<ContactCoverDto> callback);
}