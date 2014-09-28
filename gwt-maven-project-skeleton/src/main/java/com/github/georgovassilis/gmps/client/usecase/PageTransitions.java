package com.github.georgovassilis.gmps.client.usecase;

import com.google.gwt.event.logical.shared.ValueChangeEvent;

public interface PageTransitions {

	void userJustStartedApplication();

	void switchToEditingNewContactPage();

	void switchToEditingNewContactPage(Long contactId);

	void onValueChange(ValueChangeEvent<String> event);

}