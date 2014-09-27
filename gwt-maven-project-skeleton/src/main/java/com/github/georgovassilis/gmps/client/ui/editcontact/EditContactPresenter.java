package com.github.georgovassilis.gmps.client.ui.editcontact;

import com.github.georgovassilis.gmps.client.services.AddressBookService;
import com.github.georgovassilis.gmps.client.ui.BaseViewPresenter;
import com.github.georgovassilis.gmps.client.ui.addresslist.AddressListViewPresenter;
import com.github.georgovassilis.gmps.client.usecase.UseCase;
import com.github.georgovassilis.gmps.common.domain.PersonalDetailsDto;
import com.github.georgovassilis.gmps.common.domain.ContactDto;
import com.github.georgovassilis.gmps.client.events.ContactUpdatedEvent;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;

public class EditContactPresenter extends BaseViewPresenter<EditContactView> implements ContactUpdatedEvent.Handler, AddressListViewPresenter{
	
	enum Mode{editingNewContact,editingExistingContact};

	private AddressBookService service;
	private Mode mode = Mode.editingNewContact;
	private Long contactId = null;
	private UseCase useCase;
	
	
	public EditContactPresenter(EventBus bus, EditContactView view, AddressBookService service, UseCase useCase) {
		super(bus, view);
		this.service = service;
		view.setPresenter(this);
		bus.addHandler(ContactUpdatedEvent.TYPE, this);
		this.useCase = useCase;
	}
	
	public void editNewContact(){
		view.switchToSaveNewContactButton();
		view.setName("");
		view.setPhone("");
		view.setAsMainView();
		contactId = null;
		mode = Mode.editingNewContact;
	}
	
	public void onSaveButtonClicked(){
		PersonalDetailsDto dto = new PersonalDetailsDto();
		dto.setId(contactId);
		dto.setName(view.getName());
		dto.setPhoneNumber(view.getPhone());

		if (dto.getName()==null||dto.getName().isEmpty()){
			Window.alert("Name required");
			return;
		}
		view.setSubmitButtonEnabled(false);
		if (mode == Mode.editingNewContact){
			service.saveNewContact(dto);
		}
		if (mode == Mode.editingExistingContact){
			service.saveExistingContact(dto);
		}
	}
	
	protected void show(ContactDto contact){
		view.setName(contact.getPersonalDetails().getName());
		view.setPhone(contact.getPersonalDetails().getPhoneNumber());
		contactId = contact.getPersonalDetails().getId();
	}

	@Override
	public void contactUpdated(ContactDto contact) {
		view.setAsMainView();
		show(contact);
		view.switchToSaveExistingContactButton();
		view.setSubmitButtonEnabled(true);
		useCase.editContact(contact.getPersonalDetails().getId());
	}

	public void editContact(Long id){
		view.setSubmitButtonEnabled(false);
		service.retrieveContact(id);
	}
	
}
