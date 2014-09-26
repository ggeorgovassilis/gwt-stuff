package com.github.georgovassilis.gmps.client.ui.contactslist;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HTMLPanel;

public class ContactCoverWidget extends HTMLPanel{

	public ContactCoverWidget() {
		super("");
	}
	
	public void set(Long id, String name, String phone){
		getElement().setInnerHTML("<label>"+SafeHtmlUtils.htmlEscape(name)+"</label> <i>"+SafeHtmlUtils.htmlEscape(phone)+"</i>");
	}

}
