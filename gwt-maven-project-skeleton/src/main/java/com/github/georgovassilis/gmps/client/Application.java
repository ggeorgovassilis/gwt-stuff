package com.github.georgovassilis.gmps.client;

import com.github.georgovassilis.gmps.client.ui.contactslist.ContactListPresenter;
import com.github.georgovassilis.gmps.client.ui.editcontact.EditContactPresenter;
import com.github.georgovassilis.gmps.client.ui.main.MainViewImpl;
import com.github.georgovassilis.gmps.client.usecase.PageTransitions;
import com.github.georgovassilis.gmps.client.usecase.PageTransitionsImpl;
import com.github.georgovassilis.gmps.client.events.Bus;
import com.github.georgovassilis.gmps.client.services.AddressBookServiceFacade;
import com.github.georgovassilis.gmps.common.api.AddressBookServiceAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;

public class Application implements EntryPoint{
	
	public static PageTransitions pageTransitions;

	@Override
	public void onModuleLoad() {
		Bus bus = GWT.create(Bus.class);
		
		AddressBookServiceFacade addressBookServiceFacade = new AddressBookServiceFacade(bus, (AddressBookServiceAsync)GWT.create(com.github.georgovassilis.gmps.common.api.AddressBookService.class));
		MainViewImpl mainView = new MainViewImpl();
		ContactListPresenter contactListPresenter = new ContactListPresenter(bus, mainView.getContactsListView(), addressBookServiceFacade);
		EditContactPresenter editContactPresenter = new EditContactPresenter(bus, mainView.getEditContactView(), addressBookServiceFacade);
		
		pageTransitions = new PageTransitionsImpl(bus, contactListPresenter, editContactPresenter);

		pageTransitions.userJustStartedApplication();
		
	}

}
