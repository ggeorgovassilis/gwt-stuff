package com.github.georgovassilis.gmps.client.ui.addresslist;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;

public class AddressEntryWidgetImpl extends HTMLPanel implements
		AddressEntryWidget {

	private AddressListViewPresenter presenter;
	private TextBox streetLabel;
	private TextBox cityLabel;
	private Long id;
	private Button editButton;
	private Button deleteButton;
	private Mode mode = Mode.view;

	public AddressEntryWidgetImpl(final Long id, String streetAndNumber,
			String city, final AddressListViewPresenter presenter) {
		super(
				"<table><tr><td class=label>Street and number</td><td class=street><span id=street-"
						+ id
						+ "></span></td></tr>"
						+ "<tr><td class=label>City</td><td><span id=city-"
						+ id + "></span></td></tr></table>");

		streetLabel = new TextBox();
		streetLabel.setEnabled(false);
		cityLabel = new TextBox();
		cityLabel.setEnabled(false);
		addAndReplaceElement(streetLabel, "street-" + id);
		addAndReplaceElement(cityLabel, "city-" + id);
		editButton = new Button("Edit");
		add(editButton);
		editButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				presenter.onEditAddressButtonClicked(id);
			}
		});
		deleteButton = new Button("Delete");
		deleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				presenter.onDeleteAddressButtonClicked(id);
			}
		});
		add(deleteButton);

		update(id, streetAndNumber, city);
		setStyleName("AddressEntryWidgetImpl");
		addStyleName("card");
	}

	@Override
	public void update(Long id, String streetAndNumber, String city) {
		this.id = id;
		streetLabel.setText(streetAndNumber);
		cityLabel.setText(city);
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getStreetAndNumber() {
		return streetLabel.getText();
	}

	@Override
	public String getCity() {
		return cityLabel.getText();
	}

	@Override
	public void setMode(Mode mode) {
		this.mode = mode;
		if (mode == Mode.edit) {
			editButton.setText("Save");
			deleteButton.setText("Cancel");
			streetLabel.setEnabled(true);
			cityLabel.setEnabled(true);
		}
		if (mode == Mode.view) {
			editButton.setText("Edit");
			deleteButton.setText("Delete");
			streetLabel.setEnabled(false);
			cityLabel.setEnabled(false);

		}
	}

	@Override
	public Mode getMode() {
		return mode;
	}
}
