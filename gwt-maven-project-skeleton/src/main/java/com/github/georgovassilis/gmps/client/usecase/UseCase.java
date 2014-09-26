package com.github.georgovassilis.gmps.client.usecase;

import com.github.georgovassilis.gmps.client.events.AppStartedEvent;
import com.github.georgovassilis.gmps.client.ui.contactslist.ContactListPresenter;
import com.google.gwt.event.shared.EventBus;

public class UseCase {

	private EventBus bus;
	private ContactListPresenter contactListPresenter;
	
	public UseCase(EventBus eventBus, ContactListPresenter contactListPresenter){
		this.bus = eventBus;
		this.contactListPresenter = contactListPresenter;
	}
	
	public void userJustStartedApplication(){
		bus.fireEvent(new AppStartedEvent());
		contactListPresenter.showOrRefreshContactList();
	}
	
	public void userClickedNewContactButton(){
		
	}
}
