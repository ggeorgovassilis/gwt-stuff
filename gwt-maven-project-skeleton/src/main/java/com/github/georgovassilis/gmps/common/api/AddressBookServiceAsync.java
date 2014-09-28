package com.github.georgovassilis.gmps.common.api;

import java.util.List;

import com.github.georgovassilis.gmps.common.domain.AddressDto;
import com.github.georgovassilis.gmps.common.domain.PersonalDetailsDto;
import com.github.georgovassilis.gmps.common.domain.ContactDto;
import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Async version of the {@link AddressBookService} interface
 * @author George Georgovassilis
 *
 */
public interface AddressBookServiceAsync {
	
	Request deleteAddress(Long addressId, AsyncCallback<ContactDto> callback);

	Request deleteContact(Long id, AsyncCallback<Void> callback);

	Request getContact(Long id, AsyncCallback<ContactDto> callback);

	Request listContacts(AsyncCallback<List<PersonalDetailsDto>> callback);

	Request newAddressForContact(Long contactId, AddressDto address, AsyncCallback<AddressDto> callback);

	Request newContact(PersonalDetailsDto contact, AsyncCallback<ContactDto> callback);

	Request update(AddressDto address, AsyncCallback<AddressDto> callback);

	Request updateContactDetails(PersonalDetailsDto contactDetails,
			AsyncCallback<ContactDto> callback);
}