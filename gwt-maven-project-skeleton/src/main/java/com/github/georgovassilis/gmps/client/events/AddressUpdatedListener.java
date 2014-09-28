package com.github.georgovassilis.gmps.client.events;

import com.bazarooma.flatbus.shared.BusListener;
import com.github.georgovassilis.gmps.common.domain.AddressDto;

public interface AddressUpdatedListener extends BusListener{

	void onAddressUpdated(AddressDto address);

}
