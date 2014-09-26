package com.github.georgovassilis.gmps.client.ui.editcontact;

import com.github.georgovassilis.gmps.client.services.AddressBookService;
import com.github.georgovassilis.gmps.client.ui.BaseViewPresenter;
import com.github.georgovassilis.gmps.client.usecase.UseCase;
import com.github.georgovassilis.gmps.common.domain.ContactCoverDto;
import com.github.georgovassilis.gmps.common.domain.ContactDto;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;

public class EditContactPresenter extends BaseViewPresenter<EditContactView>{
	
	enum Mode{editingNewContact,editingExistingContact};

	private AddressBookService service;
	private Mode mode = Mode.editingNewContact;
	private Long contactId = 1l;
	
	
	public EditContactPresenter(EventBus bus, EditContactView view, AddressBookService service) {
		super(bus, view);
		this.service = service;
	}
	
	public void editNewContact(){
		view.switchToSaveNewContactButton();
		view.setName("");
		view.setPhone("");
		view.setAsMainView();
		//contactId = null;
		mode = Mode.editingNewContact;
	}
	
	public void onSaveButtonClicked(){
		ContactCoverDto dto = new ContactCoverDto();
		dto.setId(contactId);
		Window.alert("1");
		dto.setName(view.getName());
		Window.alert("2");
		dto.setPhoneNumber(view.getPhone());
		Window.alert("3");
		if (mode == Mode.editingNewContact){
			service.saveNewContact(dto);
		}
		if (mode == Mode.editingExistingContact){
			service.saveExistingContact(dto);
		}
	}

}
