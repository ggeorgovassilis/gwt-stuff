package com.github.georgovassilis.gmps.client.events;

import java.util.List;

import com.github.georgovassilis.gmps.common.domain.PersonalDetailsDto;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ContactListsUpdatedEvent extends GwtEvent<ContactListsUpdatedEvent.Handler>{

	public interface Handler extends EventHandler{
		void onContactListsUpdated(ContactListsUpdatedEvent event);
	}

	public static Type<Handler> TYPE = new Type<Handler>();

	public final List<PersonalDetailsDto> contacts;
	
	public ContactListsUpdatedEvent(List<PersonalDetailsDto> contacts){
		this.contacts = contacts;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onContactListsUpdated(this);
	}

}
