package com.github.georgovassilis.gmps.client.events;

import com.bazarooma.flatbus.shared.EventBus;

public interface Bus extends EventBus, AddressUpdatedListener, AppStartedListener, ContactListUpdatedListener, ContactUpdatedListener{

}
