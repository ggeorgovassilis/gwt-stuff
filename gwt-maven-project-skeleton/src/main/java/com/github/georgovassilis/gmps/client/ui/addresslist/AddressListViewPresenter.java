package com.github.georgovassilis.gmps.client.ui.addresslist;

import java.util.List;

import com.github.georgovassilis.gmps.common.domain.AddressDto;
import com.github.georgovassilis.gmps.client.events.AddressUpdatedEvent;
public interface AddressListViewPresenter extends AddressUpdatedEvent.Handler{

	void updateEntries(List<AddressDto> addresses);
	void onEditAddressButtonClicked(Long addressId);
	void onDeleteAddressButtonClicked(Long addressId);
	void onAddAddressButtonClicked();
}
