package com.github.georgovassilis.gmps.client.ui.contactslist;

import com.github.georgovassilis.gmps.client.ui.BaseViewImpl;
import com.github.georgovassilis.gmps.client.ui.main.MainViewImpl;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;

public class ContactListViewImpl extends BaseViewImpl implements ContactListView{
	
	private ContactListPresenter presenter;
	private Button newContactButton;
	private HTMLPanel contactsList;

	public ContactListViewImpl(Element e, MainViewImpl main){
		super(e, main);
	}
	
	@Override
	protected void constructUi() {
		super.constructUi();
		contactsList = HTMLPanel.wrap(DOM.getElementById("contacts-panel"));
		newContactButton = Button.wrap(DOM.getElementById("new-contact-button"));
		newContactButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				presenter.onNewContactButtonClicked();
			}
		});
	}

	@Override
	public void setPresenter(ContactListPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void clearContacts() {
		contactsList.clear();
	}

	@Override
	public void showLoading() {
		contactsList.add(new Label("Loading..."));
	}

	@Override
	public void addContactEntry(Long id, String name, String phoneNumber) {
		PersonalDetailsWidget ccw = new PersonalDetailsWidget();
		ccw.set(id, name, phoneNumber);
		contactsList.add(ccw);
	}

	@Override
	public void showNoContacts() {
		contactsList.add(new Label("No contacts found"));
	}

}
