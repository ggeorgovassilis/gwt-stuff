package com.github.georgovassilis.gmps.client.ui.main;

import com.github.georgovassilis.gmps.client.ui.BaseView;
import com.github.georgovassilis.gmps.client.ui.contactslist.ContactListViewImpl;
import com.github.georgovassilis.gmps.client.ui.editcontact.EditContactViewImpl;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.IsWidget;

public class MainViewImpl implements MainView{

	private ContactListViewImpl contactsListView;

	private EditContactViewImpl editContactView;
	
	public MainViewImpl(){
		contactsListView = new ContactListViewImpl(DOM.getElementById("contact-list"), this);
		editContactView = new EditContactViewImpl(DOM.getElementById("edit-contact-form"), this);
	}
	
	public ContactListViewImpl getContactsListView() {
		return contactsListView;
	}

	public EditContactViewImpl getEditContactView() {
		return editContactView;
	}

	public void showView(BaseView view){
		Element e = DOM.getElementById("main");
		e.setClassName(view.getBasePanel().asWidget().getElement().getId()+"-visible");
	}

	@Override
	public void setAsMainView() {
	}

	@Override
	public IsWidget getBasePanel() {
		return null;
	}

}
