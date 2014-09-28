package com.github.georgovassilis.gmps.client.ui.editcontact;

import java.util.List;

import com.github.georgovassilis.gmps.client.Application;
import com.github.georgovassilis.gmps.client.services.AddressBookServiceFacade;
import com.github.georgovassilis.gmps.client.ui.BaseViewPresenter;
import com.github.georgovassilis.gmps.client.ui.addresslist.AddressEntryWidget;
import com.github.georgovassilis.gmps.client.ui.addresslist.AddressListView;
import com.github.georgovassilis.gmps.client.ui.addresslist.AddressListViewPresenter;
import com.github.georgovassilis.gmps.common.domain.AddressDto;
import com.github.georgovassilis.gmps.common.domain.PersonalDetailsDto;
import com.github.georgovassilis.gmps.common.domain.ContactDto;
import com.github.georgovassilis.gmps.client.events.Bus;
import com.github.georgovassilis.gmps.client.events.ContactUpdatedListener;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;

public class EditContactPresenter extends BaseViewPresenter<EditContactView>
		implements ContactUpdatedListener, AddressListViewPresenter {

	enum Mode {
		editingNewContact, editingExistingContact
	};

	private AddressBookServiceFacade service;
	private Mode mode = Mode.editingNewContact;
	private Long contactId = null;

	public EditContactPresenter(Bus bus, EditContactView view,
			AddressBookServiceFacade service) {
		super(bus, view);
		this.service = service;
		view.setPresenter(this);
		bus.register(this);
	}

	public void editNewContact() {
		view.switchToSaveNewContactButton();
		view.setName("");
		view.setPhone("");
		view.setAsMainView();
		view.getAddressListView().clearEntries();
		view.getAddressListView().showNoEntriesFound();
		contactId = null;
		mode = Mode.editingNewContact;
		view.getAddressListView().setAddAddressButtonEnabled(false);
	}

	public void onSaveButtonClicked() {
		PersonalDetailsDto dto = new PersonalDetailsDto();
		dto.setId(contactId);
		dto.setName(view.getName());
		dto.setPhoneNumber(view.getPhone());

		if (dto.getName() == null || dto.getName().isEmpty()) {
			Window.alert("Name required");
			return;
		}
		view.setSubmitButtonEnabled(false);
		if (mode == Mode.editingNewContact) {
			service.saveNewContact(dto);
		}
		if (mode == Mode.editingExistingContact) {
			service.saveExistingContact(dto);
		}
	}

	protected void show(ContactDto contact) {
		view.setName(contact.getPersonalDetails().getName());
		view.setPhone(contact.getPersonalDetails().getPhoneNumber());
		contactId = contact.getPersonalDetails().getId();
	}

	@Override
	public void onContactUpdated(ContactDto contact) {
		mode = Mode.editingExistingContact;
		view.setAsMainView();
		show(contact);
		view.switchToSaveExistingContactButton();
		view.setSubmitButtonEnabled(true);
		updateEntries(contact.getAddresses());
		Application.pageTransitions.switchToEditingNewContactPage(contact.getPersonalDetails().getId());
		view.getAddressListView().setAddAddressButtonEnabled(true);
	}

	public void editContact(Long id) {
		view.setSubmitButtonEnabled(false);
		service.retrieveContact(id);
	}

	@Override
	public void updateEntries(List<AddressDto> addresses) {
		AddressListView addressListView = view.getAddressListView();
		addressListView.clearEntries();
		for (AddressDto a : addresses)
			addressListView.addEntry(a.getId(), a.getStreetAndNumber(),
					a.getCity());
		if (addresses.isEmpty())
			addressListView.showNoEntriesFound();
	}

	@Override
	public void onEditAddressButtonClicked(Long addressId) {
		AddressEntryWidget widget = view.getAddressListView().getEntryWidget(
				addressId);
		if (widget.getMode() == AddressEntryWidget.Mode.view)
			widget.setMode(AddressEntryWidget.Mode.edit);
		else if (widget.getMode() == AddressEntryWidget.Mode.edit) {
			widget.setMode(AddressEntryWidget.Mode.view);
			AddressDto address = new AddressDto();
			address.setId(widget.getId());
			address.setStreetAndNumber(widget.getStreetAndNumber());
			address.setCity(widget.getCity());
			service.updateAddress(address);
		}
	}

	@Override
	public void onDeleteAddressButtonClicked(Long addressId) {
		AddressEntryWidget widget = view.getAddressListView().getEntryWidget(
				addressId);
		if (widget.getMode() == AddressEntryWidget.Mode.view) {
			if (Window.confirm("Are you sure?")) {
				service.deleteAddress(addressId);
			}
		} else if (widget.getMode() == AddressEntryWidget.Mode.edit) {
			widget.setMode(AddressEntryWidget.Mode.view);
		}
	}

	@Override
	public void onAddAddressButtonClicked() {
		service.addAddress(contactId, new AddressDto());
	}

	@Override
	public void onAddressUpdated(AddressDto address) {
		AddressListView alv = view.getAddressListView();
		alv.hideNoEntriesFound();
		AddressEntryWidget widget = alv.getEntryWidget(address.getId());
		if (widget == null) {
			alv.addEntry(address.getId(),
					address.getStreetAndNumber(), address.getCity());
		} else {
			widget.update(address.getId(), address.getStreetAndNumber(),
					address.getCity());
		}
	}

}
