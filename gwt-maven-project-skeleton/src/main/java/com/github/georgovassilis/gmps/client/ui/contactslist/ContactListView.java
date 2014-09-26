package com.github.georgovassilis.gmps.client.ui.contactslist;

import java.util.List;

import com.github.georgovassilis.gmps.client.ui.BaseView;
import com.github.georgovassilis.gmps.common.domain.ContactCoverDto;

public interface ContactListView extends BaseView{

	void setPresenter(ContactListPresenter presenter);
	void clearContacts();
	void showLoading();
	void showNoContacts();
	void addContactEntry(Long id, String name, String phoneNumber);
}
