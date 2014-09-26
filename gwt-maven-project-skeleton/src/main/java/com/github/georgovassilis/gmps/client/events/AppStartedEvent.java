package com.github.georgovassilis.gmps.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class AppStartedEvent extends GwtEvent<AppStartedEventHandler>{

	public static Type<AppStartedEventHandler> TYPE = new Type<AppStartedEventHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AppStartedEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AppStartedEventHandler handler) {
		handler.onAppStarted(this);
	}

}
