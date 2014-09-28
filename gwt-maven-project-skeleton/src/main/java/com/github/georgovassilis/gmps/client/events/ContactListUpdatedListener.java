package com.github.georgovassilis.gmps.client.events;

import java.util.List;

import com.bazarooma.flatbus.shared.BusListener;
import com.github.georgovassilis.gmps.common.domain.PersonalDetailsDto;

public interface ContactListUpdatedListener extends BusListener {

	void onContactListsUpdated(List<PersonalDetailsDto> contacts);

}
