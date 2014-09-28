package com.github.georgovassilis.gmps.client.ui.addresslist;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;

public class AddressListViewImpl extends Composite implements AddressListView{

	private HTMLPanel panel;
	private AddressListViewPresenter presenter;
	private Button addAddressButton;
	private Map<Long, AddressEntryWidgetImpl> entries = new HashMap<Long, AddressEntryWidgetImpl>();
	private Label noEntriesLabel = new Label("This address has no addresses");
	
	public AddressListViewImpl(Element element) {
		panel = HTMLPanel.wrap(element);
		initWidget(panel);
		// we have to call onAttach because this widget is kind of "auto constructed" by wrapping around the 'element' constructor argument
		// but nobody will call onAttach() for us. Without calling onAttach(), no UI events are processed (i.e. CickHandler)
		addAddressButton = Button.wrap(DOM.getElementById("add-address-button"));
		addAddressButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				presenter.onAddAddressButtonClicked();
			}
		});
		onAttach();
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
		AddressEntryWidgetImpl widget = new AddressEntryWidgetImpl(id, streetAndNumber, city, presenter);
		panel.add(widget);
		entries.put(id, widget);
	}

	@Override
	public void setPresenter(AddressListViewPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void clearEntries() {
		panel.clear();
		entries.clear();
	}

	@Override
	public void showNoEntriesFound() {
		hideNoEntriesFound();
		panel.add(noEntriesLabel);
	}

	@Override
	public AddressEntryWidget getEntryWidget(Long id) {
		return entries.get(id);
	}

	@Override
	public void hideNoEntriesFound() {
		panel.remove(noEntriesLabel);
	}

}
