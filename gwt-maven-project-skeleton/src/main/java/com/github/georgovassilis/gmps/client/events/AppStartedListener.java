package com.github.georgovassilis.gmps.client.events;

import com.bazarooma.flatbus.shared.BusListener;

public interface AppStartedListener extends BusListener {

	void onAppStarted();
}
