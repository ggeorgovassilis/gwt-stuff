package com.github.georgovassilis.gmps.client.usecase;

import com.github.georgovassilis.gmps.client.events.Bus;
import com.github.georgovassilis.gmps.client.ui.contactslist.ContactListPresenter;
import com.github.georgovassilis.gmps.client.ui.editcontact.EditContactPresenter;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.History;

public class PageTransitionsImpl implements ValueChangeHandler<String>, PageTransitions{

	private Bus bus;
	private ContactListPresenter contactListPresenter;
	private EditContactPresenter editContactPresenter;
	
	public PageTransitionsImpl(Bus eventBus, ContactListPresenter contactListPresenter, EditContactPresenter editContactPresenter){
		this.bus = eventBus;
		this.contactListPresenter = contactListPresenter;
		this.editContactPresenter = editContactPresenter;
		History.addValueChangeHandler(this);
	}
	
	@Override
	public void userJustStartedApplication(){
		bus.onAppStarted();
		History.fireCurrentHistoryState();
	}
	
	@Override
	public void switchToEditingNewContactPage(){
		History.newItem("/contacts/new");
	}

	
	@Override
	public void switchToEditingNewContactPage(Long contactId){
		History.newItem("/contacts/"+contactId);
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		RegExp editExistingContactRe = RegExp.compile("/contacts/(\\d+)");
		String tag = ""+event.getValue();
		if ("/contacts/new".equals(tag)){
			editContactPresenter.editNewContact();
		} else
		if (editExistingContactRe.test(tag)){
			editContactPresenter.editContact(Long.parseLong(editExistingContactRe.exec(tag).getGroup(1)));
		} else
		if ("".equals(tag)||"/".equals(tag))
			contactListPresenter.showOrRefreshContactList();
		else
			History.newItem("/");
	}

}
