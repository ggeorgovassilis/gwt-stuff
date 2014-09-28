package com.github.georgovassilis.gmps.ui.contactlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.github.georgovassilis.gmps.client.events.ContactListsUpdatedEvent;
import com.github.georgovassilis.gmps.client.events.ContactUpdatedEvent;
import com.github.georgovassilis.gmps.client.ui.contactslist.ContactListPresenter;
import com.github.georgovassilis.gmps.client.ui.contactslist.ContactListView;
import com.github.georgovassilis.gmps.common.domain.PersonalDetailsDto;
import com.github.georgovassilis.gmps.ui.BaseClientTest;
import com.google.gwt.user.client.rpc.AsyncCallback;

import static org.mockito.Mockito.*;

public class ContactListPresenterTest extends BaseClientTest {

	ContactListPresenter presenter;
	ContactListView view;

	@Before
	public void setup() {
		view = mock(ContactListView.class);
		presenter = new ContactListPresenter(bus, view, serviceFacade);
	}

	/**
	 * When a {@link ContactUpdatedEvent} is received then the view should be
	 * updated
	 */
	@Test
	public void testOnContactListsUpdated_with_entries() {

		ContactListsUpdatedEvent event = new ContactListsUpdatedEvent(
				Arrays.asList(contact1.getPersonalDetails(),
						contact2.getPersonalDetails(),
						contact3.getPersonalDetails()));

		presenter.onContactListsUpdated(event);
		verify(view).addContactEntry(contact1.getPersonalDetails().getId(),
				contact1.getPersonalDetails().getName(),
				contact1.getPersonalDetails().getPhoneNumber());
		verify(view).addContactEntry(contact2.getPersonalDetails().getId(),
				contact2.getPersonalDetails().getName(),
				contact2.getPersonalDetails().getPhoneNumber());
		verify(view).addContactEntry(contact3.getPersonalDetails().getId(),
				contact3.getPersonalDetails().getName(),
				contact3.getPersonalDetails().getPhoneNumber());
		verify(view, times(0)).showNoContacts();
	}

	/**
	 * When a {@link ContactUpdatedEvent} is received then the view should be
	 * updated
	 */
	@Test
	public void testOnContactListsUpdated_without_entries() {

		ContactListsUpdatedEvent event = new ContactListsUpdatedEvent(
				new ArrayList<PersonalDetailsDto>());

		presenter.onContactListsUpdated(event);
		verify(view, times(0)).addContactEntry(any(Long.class),
				any(String.class), any(String.class));
		verify(view).showNoContacts();

	}

	/**
	 * Test that when refreshing the contact list the server is asked for new
	 * contacts and the UI is updated
	 */
	@Test
	public void testOnNewContactButtonClicked() {

		when(service.listContacts(any(AsyncCallback.class))).then(
				new Answer<Void>() {

					@Override
					public Void answer(
							InvocationOnMock invocation) throws Throwable {
						AsyncCallback callback = (AsyncCallback)invocation.getArguments()[0];
						List list = Arrays.asList(
								contact1.getPersonalDetails(),
								contact2.getPersonalDetails(),
								contact3.getPersonalDetails());
						callback.onSuccess(list);
						return null;
					}
				});
		
		presenter.showOrRefreshContactList();

		verify(service).listContacts(any(AsyncCallback.class));
		verify(view).addContactEntry(contact1.getPersonalDetails().getId(), contact1.getPersonalDetails().getName(), contact1.getPersonalDetails().getPhoneNumber());
		verify(view).addContactEntry(contact2.getPersonalDetails().getId(), contact2.getPersonalDetails().getName(), contact2.getPersonalDetails().getPhoneNumber());
		verify(view).addContactEntry(contact3.getPersonalDetails().getId(), contact3.getPersonalDetails().getName(), contact3.getPersonalDetails().getPhoneNumber());
	}
}
