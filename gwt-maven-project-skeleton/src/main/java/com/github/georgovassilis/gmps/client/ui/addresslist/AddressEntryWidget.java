package com.github.georgovassilis.gmps.client.ui.addresslist;

public interface AddressEntryWidget {

	public enum Mode{
		view,edit;
	}

	void update(Long id, String streetAndNumber, String city);
	Long getId();
	String getStreetAndNumber();
	String getCity();
	
	void setMode(Mode mode);
	
	Mode getMode();

}
