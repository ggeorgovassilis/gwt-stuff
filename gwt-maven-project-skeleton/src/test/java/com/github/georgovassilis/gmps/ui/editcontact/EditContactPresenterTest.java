package com.github.georgovassilis.gmps.ui.editcontact;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.github.georgovassilis.gmps.client.ui.addresslist.AddressListView;
import com.github.georgovassilis.gmps.client.ui.editcontact.EditContactPresenter;
import com.github.georgovassilis.gmps.client.ui.editcontact.EditContactView;
import com.github.georgovassilis.gmps.common.domain.AddressDto;
import com.github.georgovassilis.gmps.common.domain.PersonalDetailsDto;
import com.github.georgovassilis.gmps.ui.BaseClientTest;
import com.google.gwt.user.client.rpc.AsyncCallback;

import static org.mockito.Mockito.*;

public class EditContactPresenterTest extends BaseClientTest {

	EditContactPresenter presenter;
	EditContactView contactView;
	AddressListView addressListView;

	@Before
	public void setup() {
		contactView = mock(EditContactView.class);
		addressListView = mock(AddressListView.class);
		when(contactView.getAddressListView()).thenReturn(addressListView);

		presenter = new EditContactPresenter(bus, contactView, serviceFacade);
	}

	/**
	 * Editing a contact: disable edit button in view and ask service to fetch
	 * contact
	 */
	@Test
	public void testEditContact() {
		when(service.getContact(eq(1l), any(AsyncCallback.class))).then(
				new Answer() {
					@Override
					public Object answer(InvocationOnMock invocation)
							throws Throwable {
						AsyncCallback callback = (AsyncCallback) invocation
								.getArguments()[1];
						callback.onSuccess(contact1);
						return null;
					}
				});
		presenter.editContact(1l);
		verify(service).getContact(eq(1l), any(AsyncCallback.class));
		verify(contactView).setAsMainView();

		PersonalDetailsDto pd = contact1.getPersonalDetails();
		verify(contactView).setName(pd.getName());
		verify(contactView).setPhone(pd.getPhoneNumber());

		AddressDto a1 = contact1.getAddresses().get(0);
		verify(addressListView).clearEntries();
		verify(addressListView).addEntry(a1.getId(), a1.getStreetAndNumber(),
				a1.getCity());

		AddressDto a2 = contact1.getAddresses().get(1);
		verify(addressListView).clearEntries();
		verify(addressListView).addEntry(a2.getId(), a2.getStreetAndNumber(),
				a2.getCity());

	}
}
