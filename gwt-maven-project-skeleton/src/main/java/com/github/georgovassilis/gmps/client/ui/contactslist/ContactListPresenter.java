package com.github.georgovassilis.gmps.client.ui.contactslist;

import com.github.georgovassilis.gmps.client.Application;
import com.github.georgovassilis.gmps.client.events.ContactListsUpdatedEvent;
import com.github.georgovassilis.gmps.client.services.AddressBookService;
import com.github.georgovassilis.gmps.client.ui.BaseViewPresenter;
import com.github.georgovassilis.gmps.common.domain.PersonalDetailsDto;
import com.google.gwt.event.shared.EventBus;

public class ContactListPresenter extends BaseViewPresenter<ContactListView> implements ContactListsUpdatedEvent.Handler{

	private AddressBookService addressBookService;
	
	public ContactListPresenter(EventBus bus, ContactListView view, AddressBookService addressBookService) {
		super(bus, view);
		this.addressBookService = addressBookService;
		view.setPresenter(this);
		bus.addHandler(ContactListsUpdatedEvent.TYPE, this);
	}
	
	public void onNewContactButtonClicked(){
		Application.userCase.userClickedNewContactButton();
	}
	
	public void showOrRefreshContactList(){
		view.setAsMainView();
		view.clearContacts();
		view.showLoading();
		addressBookService.retrieveContactList();
	}

	@Override
	public void onContactListsUpdated(ContactListsUpdatedEvent event) {
		view.clearContacts();
		for (PersonalDetailsDto c:event.contacts){
			view.addContactEntry(c.getId(), c.getName(), c.getPhoneNumber());
		}
		if (event.contacts.isEmpty())
			view.showNoContacts();
	}

}
