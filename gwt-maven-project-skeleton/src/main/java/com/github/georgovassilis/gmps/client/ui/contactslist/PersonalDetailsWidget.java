package com.github.georgovassilis.gmps.client.ui.contactslist;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HTMLPanel;

public class PersonalDetailsWidget extends HTMLPanel{

	public PersonalDetailsWidget() {
		super("");
		setStyleName("PersonalDetailsWidget");
	}
	
	public void set(Long id, String name, String phone){
		getElement().setInnerHTML("<a href='#/contacts/"+id+"'>"+SafeHtmlUtils.htmlEscape(name)+"</a> <i>"+SafeHtmlUtils.htmlEscape(phone)+"</i>");
	}

}
