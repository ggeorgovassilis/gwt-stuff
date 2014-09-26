package com.github.georgovassilis.gmps.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class AppStartedEvent extends GwtEvent<AppStartedEvent.Handler>{

	public interface Handler extends EventHandler{
		
		void onAppStarted(AppStartedEvent event);

	}

	public static Type<Handler> TYPE = new Type<Handler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onAppStarted(this);
	}

}
