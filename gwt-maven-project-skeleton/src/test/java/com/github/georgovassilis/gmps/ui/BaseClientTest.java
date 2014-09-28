package com.github.georgovassilis.gmps.ui;

import static org.mockito.Mockito.mock;

import org.junit.Before;

import com.github.georgovassilis.gmps.client.Application;
import com.github.georgovassilis.gmps.client.events.Bus;
import com.github.georgovassilis.gmps.client.services.AddressBookServiceFacade;
import com.github.georgovassilis.gmps.client.ui.contactslist.ContactListPresenter;
import com.github.georgovassilis.gmps.client.ui.contactslist.ContactListView;
import com.github.georgovassilis.gmps.client.usecase.PageTransitions;
import com.github.georgovassilis.gmps.common.api.AddressBookServiceAsync;
import com.github.georgovassilis.gmps.common.domain.AddressDto;
import com.github.georgovassilis.gmps.common.domain.ContactDto;
import com.github.georgovassilis.gmps.common.domain.PersonalDetailsDto;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.testing.CountingEventBus;

public class BaseClientTest {

	protected Bus bus;
	protected AddressBookServiceFacade serviceFacade;
	protected AddressBookServiceAsync service;
	protected ContactDto contact1;
	protected ContactDto contact2;
	protected ContactDto contact3;
	protected PageTransitions pageTransitions;
	
	@Before
	public void setupBaseTest(){
		bus = new MockBus();
		service = mock(AddressBookServiceAsync.class);
		serviceFacade = new AddressBookServiceFacade(bus, service);
		pageTransitions = mock(PageTransitions.class);
		Application.pageTransitions = pageTransitions;
		
		contact1 = new ContactDto();
		PersonalDetailsDto details = new PersonalDetailsDto();
		details.setId(1l);
		contact1.setPersonalDetails(details);
		
		AddressDto a1 = new AddressDto();
		a1.setCity("C1 City 1");
		a1.setStreetAndNumber("C1 street 1");
		a1.setId(2l);
		
		AddressDto a2 = new AddressDto();
		a2.setCity("C1 City 2");
		a2.setStreetAndNumber("C1 street 2");
		a2.setId(3l);

		contact1.getAddresses().add(a1);
		contact1.getAddresses().add(a2);

		contact2 = new ContactDto();
		details = new PersonalDetailsDto();
		details.setId(3l);
		contact2.setPersonalDetails(details);
		
		a1 = new AddressDto();
		a1.setCity("C2 City 1");
		a1.setStreetAndNumber("C2 street 1");
		a1.setId(4l);
		
		a2 = new AddressDto();
		a2.setCity("C2 City 2");
		a2.setStreetAndNumber("C2 street 2");
		a2.setId(5l);

		contact2.getAddresses().add(a1);
		contact2.getAddresses().add(a2);

		contact3 = new ContactDto();
		details = new PersonalDetailsDto();
		details.setId(6l);
		contact3.setPersonalDetails(details);
		
		a1 = new AddressDto();
		a1.setCity("C3 City 1");
		a1.setStreetAndNumber("C3 street 1");
		a1.setId(7l);
		
		a2 = new AddressDto();
		a2.setCity("C3 City 2");
		a2.setStreetAndNumber("C3 street 2");
		a2.setId(8l);

		contact3.getAddresses().add(a1);
		contact3.getAddresses().add(a2);
}

}
