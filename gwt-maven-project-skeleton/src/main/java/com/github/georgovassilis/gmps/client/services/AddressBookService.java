package com.github.georgovassilis.gmps.client.services;

import java.util.List;

import com.github.georgovassilis.gmps.client.events.ContactListsUpdatedEvent;
import com.github.georgovassilis.gmps.client.events.ContactUpdatedEvent;
import com.github.georgovassilis.gmps.common.api.AddressBookServiceAsync;
import com.github.georgovassilis.gmps.common.domain.PersonalDetailsDto;
import com.github.georgovassilis.gmps.common.domain.ContactDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class AddressBookService {

	private AddressBookServiceAsync addressBookService = GWT.create(com.github.georgovassilis.gmps.common.api.AddressBookService.class);
	private EventBus bus;
	
	public AddressBookService(EventBus bus){
		this.bus = bus;
	}
	
	public void saveNewContact(PersonalDetailsDto contact){
		addressBookService.newContact(contact, new AsyncCallback<ContactDto>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(ContactDto result) {
				bus.fireEvent(new ContactUpdatedEvent(result));
			}
		});
	}

	public void saveExistingContact(PersonalDetailsDto contact){
		addressBookService.updateContactDetails(contact, new AsyncCallback<ContactDto>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(ContactDto result) {
				bus.fireEvent(new ContactUpdatedEvent(result));
			}
		});
	}

	public void retrieveContactList(){
		addressBookService.listContacts(new AsyncCallback<List<PersonalDetailsDto>>() {
			
			@Override
			public void onSuccess(List<PersonalDetailsDto> result) {
				bus.fireEvent(new ContactListsUpdatedEvent(result));
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}
	
	public void retrieveContact(Long id){
		addressBookService.getContact(id, new AsyncCallback<ContactDto>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				
			}

			@Override
			public void onSuccess(ContactDto result) {
				bus.fireEvent(new ContactUpdatedEvent(result));
			}
		});
	}

}
