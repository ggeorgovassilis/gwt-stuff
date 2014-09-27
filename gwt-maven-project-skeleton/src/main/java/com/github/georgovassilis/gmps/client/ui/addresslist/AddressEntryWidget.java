package com.github.georgovassilis.gmps.client.ui.addresslist;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;

public class AddressEntryWidget extends FlowPanel{
	
	public AddressEntryWidget(Long id, String streetAndNumber, String city){
		add(new Label(streetAndNumber));
		add(new Label(city));
		Button editButton = new Button("Edit");
		add(editButton);
		Button deleteButton = new Button("Delete");
		add(deleteButton);
	}
}
