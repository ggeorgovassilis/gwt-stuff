package com.github.georgovassilis.gmps.client.events;

import java.util.List;

import com.github.georgovassilis.gmps.common.domain.ContactCoverDto;
import com.google.gwt.event.shared.GwtEvent;

public class ContactListsUpdatedEvent extends GwtEvent<ContactListsUpdatedEventHandler>{

	public static Type<ContactListsUpdatedEventHandler> TYPE = new Type<ContactListsUpdatedEventHandler>();

	public final List<ContactCoverDto> contacts;
	
	public ContactListsUpdatedEvent(List<ContactCoverDto> contacts){
		this.contacts = contacts;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ContactListsUpdatedEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ContactListsUpdatedEventHandler handler) {
		handler.onContactListsUpdated(this);
	}

}
