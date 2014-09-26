package com.github.georgovassilis.gmps.client.ui;

import com.google.gwt.event.shared.EventBus;

public abstract class BaseViewPresenter<V extends BaseView> {

	protected EventBus bus;
	protected V view;
	
	public BaseViewPresenter(EventBus bus, V view){
		this.bus = bus;
		this.view = view;
	}
}
