package com.github.georgovassilis.gmps.client.events;

import com.github.georgovassilis.gmps.common.domain.ContactDto;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ContactUpdatedEvent extends GwtEvent<ContactUpdatedEvent.Handler>{

	public interface Handler extends EventHandler{
		
		void contactUpdated(ContactDto contact);

	}

	public static Type<Handler> TYPE = new Type<Handler>();
	
	public final ContactDto contact;
	
	public ContactUpdatedEvent(ContactDto contact) {
		this.contact = contact;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ContactUpdatedEvent.Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ContactUpdatedEvent.Handler handler) {
		handler.contactUpdated(contact);
	}

}
