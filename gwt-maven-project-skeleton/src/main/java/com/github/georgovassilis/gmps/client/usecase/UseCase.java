package com.github.georgovassilis.gmps.client.usecase;

import com.github.georgovassilis.gmps.client.events.AppStartedEvent;
import com.github.georgovassilis.gmps.client.ui.contactslist.ContactListPresenter;
import com.github.georgovassilis.gmps.client.ui.editcontact.EditContactPresenter;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.History;

public class UseCase implements ValueChangeHandler<String>{

	private EventBus bus;
	private ContactListPresenter contactListPresenter;
	private EditContactPresenter editContactPresenter;
	
	public UseCase(EventBus eventBus, ContactListPresenter contactListPresenter, EditContactPresenter editContactPresenter){
		this.bus = eventBus;
		this.contactListPresenter = contactListPresenter;
		this.editContactPresenter = editContactPresenter;
		History.addValueChangeHandler(this);
	}
	
	public void userJustStartedApplication(){
		bus.fireEvent(new AppStartedEvent());
		History.fireCurrentHistoryState();
	}
	
	public void userClickedNewContactButton(){
		History.newItem("/contacts/new");
	}

	public void userClickedSaveNewContactButton(){
	}
	
	public void editContact(Long contactId){
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
