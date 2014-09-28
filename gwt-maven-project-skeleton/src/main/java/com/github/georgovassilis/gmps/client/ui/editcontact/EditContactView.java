package com.github.georgovassilis.gmps.client.ui.editcontact;

import com.github.georgovassilis.gmps.client.ui.BaseView;
import com.github.georgovassilis.gmps.client.ui.addresslist.AddressListView;

public interface EditContactView extends BaseView{

	void setSubmitButtonEnabled(boolean enabled);
	void setName(String name);
	void setPhone(String phone);
	String getName();
	String getPhone();
	void setPresenter(EditContactPresenter presenter);
	void switchToSaveNewContactButton();
	void switchToSaveExistingContactButton();
	AddressListView getAddressListView();
}
