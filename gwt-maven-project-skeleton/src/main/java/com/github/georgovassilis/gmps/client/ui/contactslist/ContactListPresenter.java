package com.github.georgovassilis.gmps.client.ui.contactslist;

import java.util.List;

import com.github.georgovassilis.gmps.client.Application;
import com.github.georgovassilis.gmps.client.events.Bus;
import com.github.georgovassilis.gmps.client.events.ContactListUpdatedListener;
import com.github.georgovassilis.gmps.client.services.AddressBookServiceFacade;
import com.github.georgovassilis.gmps.client.ui.BaseViewPresenter;
import com.github.georgovassilis.gmps.common.domain.PersonalDetailsDto;

public class ContactListPresenter extends BaseViewPresenter<ContactListView> implements ContactListUpdatedListener{

	private AddressBookServiceFacade addressBookServiceFacade;
	
	public ContactListPresenter(Bus bus, ContactListView view, AddressBookServiceFacade addressBookServiceFacade) {
		super(bus, view);
		this.addressBookServiceFacade = addressBookServiceFacade;
		view.setPresenter(this);
		bus.register(this);
	}
	
	public void onNewContactButtonClicked(){
		Application.pageTransitions.switchToEditingNewContactPage();
	}
	
	public void showOrRefreshContactList(){
		view.setAsMainView();
		view.clearContacts();
		view.showLoading();
		addressBookServiceFacade.retrieveContactList();
	}

	@Override
	public void onContactListsUpdated(List<PersonalDetailsDto> contacts) {
		view.clearContacts();
		for (PersonalDetailsDto c:contacts){
			view.addContactEntry(c.getId(), c.getName(), c.getPhoneNumber());
		}
		if (contacts.isEmpty())
			view.showNoContacts();
	}

}
