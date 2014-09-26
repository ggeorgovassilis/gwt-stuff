package com.github.georgovassilis.gmps.client.ui;

import com.github.georgovassilis.gmps.client.ui.main.MainViewImpl;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;

public abstract class BaseViewImpl implements BaseView{

	protected MainViewImpl main;
	protected HTMLPanel basePanel;
	
	@Override
	public IsWidget getBasePanel() {
		return basePanel;
	}
	
	public BaseViewImpl(Element element, MainViewImpl main){
		this.main = main;
		constructUi();
		basePanel = HTMLPanel.wrap(element);
	}
	
	protected void constructUi(){
	}
	
	@Override
	public void setAsMainView() {
		main.showView(this);
	}
}
