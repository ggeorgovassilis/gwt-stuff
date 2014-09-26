package com.github.georgovassilis.gmps.client.events;

import com.google.gwt.event.shared.EventHandler;


public interface ContactListsUpdatedEventHandler extends EventHandler{

	void onContactListsUpdated(ContactListsUpdatedEvent event);
}
