package com.github.georgovassilis.gmps.client.ui.addresslist;

import com.github.georgovassilis.gmps.client.ui.BaseView;

public interface AddressListView extends BaseView{

	void clearEntries();
	void addEntry(Long id, String streetAndNumber, String city);
	void setPresenter(AddressListViewPresenter presenter);
	void showNoEntriesFound();
	void hideNoEntriesFound();
	AddressEntryWidget getEntryWidget(Long id);
}
