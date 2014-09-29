package com.github.georgovassilis.gmps.ui;

import java.util.List;

import com.github.georgovassilis.gmps.common.api.AddressBookServiceAsync;
import com.github.georgovassilis.gmps.common.domain.AddressDto;
import com.github.georgovassilis.gmps.common.domain.ContactDto;
import com.github.georgovassilis.gmps.common.domain.PersonalDetailsDto;
import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ServiceMock implements AddressBookServiceAsync{

	@Override
	public Request deleteAddress(Long addressId,
			AsyncCallback<ContactDto> callback) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public Request deleteContact(Long id, AsyncCallback<Void> callback) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public Request getContact(Long id, AsyncCallback<ContactDto> callback) {
		ContactDto contact = new ContactDto();
		PersonalDetailsDto d = new PersonalDetailsDto();
		contact.setPersonalDetails(d);
		d.setId(1l);
		d.setName("test name");
		d.setPhoneNumber("phone number");
		callback.onSuccess(contact);
		return null;
	}

	@Override
	public Request listContacts(AsyncCallback<List<PersonalDetailsDto>> callback) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public Request newAddressForContact(Long contactId, AddressDto address,
			AsyncCallback<AddressDto> callback) {
		address.setId(1l);
		callback.onSuccess(address);
		return null;
	}

	@Override
	public Request newContact(PersonalDetailsDto contact,
			AsyncCallback<ContactDto> callback) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public Request update(AddressDto address, AsyncCallback<AddressDto> callback) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public Request updateContactDetails(PersonalDetailsDto contactDetails,
			AsyncCallback<ContactDto> callback) {
		throw new RuntimeException("Not implemented");
	}

}
