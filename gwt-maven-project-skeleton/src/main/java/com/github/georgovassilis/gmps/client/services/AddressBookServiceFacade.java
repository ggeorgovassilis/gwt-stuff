package com.github.georgovassilis.gmps.client.services;

import java.util.List;

import com.github.georgovassilis.gmps.client.events.Bus;
import com.github.georgovassilis.gmps.common.api.AddressBookServiceAsync;
import com.github.georgovassilis.gmps.common.domain.AddressDto;
import com.github.georgovassilis.gmps.common.domain.PersonalDetailsDto;
import com.github.georgovassilis.gmps.common.domain.ContactDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class AddressBookServiceFacade {

	private AddressBookServiceAsync target;
	private Bus bus;
	
	public AddressBookServiceFacade(Bus bus, AddressBookServiceAsync target){
		this.bus = bus;
		this.target = target;
	}
	
	public void saveNewContact(PersonalDetailsDto contact){
		target.newContact(contact, new AsyncCallback<ContactDto>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(ContactDto result) {
				bus.onContactUpdated(result);
			}
		});
	}

	public void saveExistingContact(PersonalDetailsDto contact){
		target.updateContactDetails(contact, new AsyncCallback<ContactDto>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(ContactDto result) {
				bus.onContactUpdated(result);
			}
		});
	}

	public void retrieveContactList(){

		target.listContacts(new AsyncCallback<List<PersonalDetailsDto>>() {
			
			@Override
			public void onSuccess(List<PersonalDetailsDto> result) {
				bus.onContactListsUpdated(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}
	
	public void retrieveContact(Long id){
		target.getContact(id, new AsyncCallback<ContactDto>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				
			}

			@Override
			public void onSuccess(ContactDto result) {
				bus.onContactUpdated(result);
			}
		});
	}
	
	public void deleteAddress(Long addressId){
		target.deleteAddress(addressId, new AsyncCallback<ContactDto>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(ContactDto result) {
				bus.onContactUpdated(result);
			}
		});
	}
	
	public void addAddress(Long contactId, AddressDto address){
		target.newAddressForContact(contactId, address, new AsyncCallback<AddressDto>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(AddressDto result) {
				bus.onAddressUpdated(result);
			}
		});
	}

	public void updateAddress(AddressDto address){
		target.update(address, new AsyncCallback<AddressDto>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(AddressDto result) {
				bus.onAddressUpdated(result);
			}
		});
	}

}
