package com.github.georgovassilis.gmps.client.events;

import com.bazarooma.flatbus.shared.BusListener;
import com.github.georgovassilis.gmps.common.domain.ContactDto;

public interface ContactUpdatedListener extends BusListener {

	void onContactUpdated(ContactDto contact);

}
