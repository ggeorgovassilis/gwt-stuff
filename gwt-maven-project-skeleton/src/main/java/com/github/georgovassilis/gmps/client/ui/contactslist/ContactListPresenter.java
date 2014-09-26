package com.github.georgovassilis.gmps.client.ui.contactslist;

import java.util.ArrayList;
import java.util.List;

import com.github.georgovassilis.gmps.client.Application;
import com.github.georgovassilis.gmps.client.events.ContactListsUpdatedEvent;
import com.github.georgovassilis.gmps.client.events.ContactListsUpdatedEventHandler;
import com.github.georgovassilis.gmps.client.services.AddressBookService;
import com.github.georgovassilis.gmps.client.ui.BaseViewPresenter;
import com.github.georgovassilis.gmps.common.api.AddressBookServiceAsync;
import com.github.georgovassilis.gmps.common.domain.ContactCoverDto;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ContactListPresenter extends BaseViewPresenter<ContactListView> implements ContactListsUpdatedEventHandler{

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
		for (ContactCoverDto c:event.contacts){
			view.addContactEntry(c.getId(), c.getName(), c.getPhoneNumber());
		}
		if (event.contacts.isEmpty())
			view.showNoContacts();
	}

}
