package com.github.georgovassilis.gmps.client.events;

import com.github.georgovassilis.gmps.common.domain.AddressDto;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class AddressUpdatedEvent extends GwtEvent<AddressUpdatedEvent.Handler>{

	public interface Handler extends EventHandler{
		
		void addressUpdated(AddressDto address);

	}

	public static Type<Handler> TYPE = new Type<Handler>();
	
	public final AddressDto address;
	
	public AddressUpdatedEvent(AddressDto address) {
		this.address = address;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AddressUpdatedEvent.Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddressUpdatedEvent.Handler handler) {
		handler.addressUpdated(address);
	}

}
