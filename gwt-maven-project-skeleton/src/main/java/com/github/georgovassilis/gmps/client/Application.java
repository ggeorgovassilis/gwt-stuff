package com.github.georgovassilis.gmps.client;

import com.github.georgovassilis.gmps.client.events.AppStartedEvent;
import com.github.georgovassilis.gmps.client.ui.contactslist.ContactListPresenter;
import com.github.georgovassilis.gmps.client.ui.main.MainView;
import com.github.georgovassilis.gmps.client.ui.main.MainViewImpl;
import com.github.georgovassilis.gmps.client.ui.main.MainViewPresenter;
import com.github.georgovassilis.gmps.client.usecase.UseCase;
import com.github.georgovassilis.gmps.client.services.AddressBookService;
import com.github.georgovassilis.gmps.common.api.AddressBookServiceAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;

public class Application implements EntryPoint{
	
	public static UseCase userCase;

	@Override
	public void onModuleLoad() {
		EventBus bus = GWT.create(SimpleEventBus.class);
		
		AddressBookService addressBookService = new AddressBookService(bus);
		MainViewImpl mainView = new MainViewImpl();
		MainViewPresenter mainPresenter = new MainViewPresenter(bus, mainView);
		ContactListPresenter contactListPresenter = new ContactListPresenter(bus, mainView.getContactsListView(), addressBookService);

		userCase = new UseCase(bus, contactListPresenter);
		
		userCase.userJustStartedApplication();
	}

}
