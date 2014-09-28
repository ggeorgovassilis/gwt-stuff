package com.github.georgovassilis.gmps.client.ui;

import com.github.georgovassilis.gmps.client.events.Bus;

public abstract class BaseViewPresenter<V extends BaseView> {

	protected Bus bus;
	protected V view;
	
	public BaseViewPresenter(Bus bus, V view){
		this.bus = bus;
		this.view = view;
	}
}
