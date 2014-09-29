package com.github.georgovassilis.gmps.ui;


import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.georgovassilis.gmps.client.Application;
import com.github.georgovassilis.gmps.client.events.AddressUpdatedListener;
import com.github.georgovassilis.gmps.client.events.Bus;
import com.github.georgovassilis.gmps.client.services.AddressBookServiceFacade;
import com.github.georgovassilis.gmps.client.ui.addresslist.AddressListViewPresenter;
import com.github.georgovassilis.gmps.client.ui.editcontact.EditContactPresenter;
import com.github.georgovassilis.gmps.client.ui.editcontact.EditContactView;
import com.github.georgovassilis.gmps.client.ui.main.MainViewImpl;
import com.github.georgovassilis.gmps.client.usecase.PageTransitions;
import com.github.georgovassilis.gmps.common.domain.AddressDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Exemplary test of how to test a class in an GWT environment
 * @author george georgovassilis
 * 
 */

public class EditContactViewPresenterGwtTest extends GWTTestCase{

	protected EditContactPresenter editContactPresenter;
	protected Bus bus;
	protected AddressBookServiceFacade serviceFacade;
	protected ServiceMock serviceMock;
	
	@Override
	public String getModuleName() {
		return "com.github.georgovassilis.gmps.Tests";
	}

	void addElement(String tag, String id){
		Element e = DOM.createElement(tag);
		e.setId(id);
		RootPanel.get().getElement().appendChild(e);
	}
	
	@Before
	@Override
	public void gwtSetUp(){
		Application.pageTransitions = new PageTransitions() {
			
			@Override
			public void userJustStartedApplication() {
			}
			
			@Override
			public void switchToEditingNewContactPage(Long contactId) {
			}
			
			@Override
			public void switchToEditingNewContactPage() {
			}
			
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
			}
		};
		// that's a downside with templating: there is no DOM, so we have to rebuild it
		addElement("div","contacts-panel");
		addElement("button","new-contact-button");
		addElement("div","contact-list");
		addElement("div","edit-contact-form");
		addElement("input","name");
		addElement("input","phone");
		addElement("button","save-contact-button");
		addElement("div","address-list");
		addElement("button","add-address-button");

		bus = GWT.create(Bus.class);
		MainViewImpl mainView = new MainViewImpl();
		serviceMock = new ServiceMock();
		serviceFacade = new AddressBookServiceFacade(bus, serviceMock);
		editContactPresenter = new EditContactPresenter(bus, mainView.getEditContactView(), serviceFacade);
	}
	
	@Test
	public void testEditContact() {
		editContactPresenter.editContact(1l);
		assertEquals("test name", DOM.getElementById("name").getPropertyString("value"));
	}

}
