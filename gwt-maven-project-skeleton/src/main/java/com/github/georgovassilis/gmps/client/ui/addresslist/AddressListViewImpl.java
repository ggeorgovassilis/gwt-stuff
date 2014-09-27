package com.github.georgovassilis.gmps.client.ui.addresslist;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;

public class AddressListViewImpl extends Composite implements AddressListView{

	private HTMLPanel panel;
	private AddressListViewPresenter presenter;
	
	public AddressListViewImpl(Element element) {
		panel = HTMLPanel.wrap(element);
		initWidget(panel);
	}
	
	@Override
	public void setAsMainView() {
		throw new RuntimeException("This is not a main view");
	}

	@Override
	public IsWidget getBasePanel() {
		return panel;
	}

	@Override
	public void addEntry(Long id, String streetAndNumber, String city) {
		panel.add(new AddressEntryWidget(id, streetAndNumber, city));
	}

	@Override
	public void setPresenter(AddressListViewPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void clearEntries() {
		panel.clear();
		panel.add(new Label("This contact has no addresses"));
	}

}
