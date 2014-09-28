package com.github.georgovassilis.gmps.client.ui.main;

import com.github.georgovassilis.gmps.client.events.AppStartedEvent;
import com.github.georgovassilis.gmps.client.ui.BaseViewPresenter;
import com.google.gwt.event.shared.EventBus;

public class MainViewPresenter extends BaseViewPresenter<MainView> implements AppStartedEvent.Handler{

	public MainViewPresenter(EventBus bus, MainView view){
		super(bus, view);
		bus.addHandler(AppStartedEvent.TYPE, this);
	}

	@Override
	public void onAppStarted(AppStartedEvent event) {
	}
	
}
