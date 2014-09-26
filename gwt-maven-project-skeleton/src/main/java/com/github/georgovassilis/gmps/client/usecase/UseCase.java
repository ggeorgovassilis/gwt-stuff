package com.github.georgovassilis.gmps.client.usecase;

import com.github.georgovassilis.gmps.client.events.AppStartedEvent;
import com.github.georgovassilis.gmps.client.ui.contactslist.ContactListPresenter;
import com.github.georgovassilis.gmps.client.ui.editcontact.EditContactPresenter;
import com.google.gwt.event.shared.EventBus;

public class UseCase {

	private EventBus bus;
	private ContactListPresenter contactListPresenter;
	private EditContactPresenter editContactPresenter;
	
	public UseCase(EventBus eventBus, ContactListPresenter contactListPresenter, EditContactPresenter editContactPresenter){
		this.bus = eventBus;
		this.contactListPresenter = contactListPresenter;
		this.editContactPresenter = editContactPresenter;
	}
	
	public void userJustStartedApplication(){
		bus.fireEvent(new AppStartedEvent());
		contactListPresenter.showOrRefreshContactList();
	}
	
	public void userClickedNewContactButton(){
		editContactPresenter.editNewContact();
	}

	public void userClickedSaveNewContactButton(){
	}
}
