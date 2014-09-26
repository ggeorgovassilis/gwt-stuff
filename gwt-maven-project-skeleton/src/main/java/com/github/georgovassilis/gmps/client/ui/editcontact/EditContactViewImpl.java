package com.github.georgovassilis.gmps.client.ui.editcontact;

import com.github.georgovassilis.gmps.client.ui.BaseViewImpl;
import com.github.georgovassilis.gmps.client.ui.main.MainViewImpl;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;

public class EditContactViewImpl extends BaseViewImpl implements EditContactView{

	private TextBox name;
	private TextBox phone;
	private Button saveButton;
	private EditContactPresenter presenter;
	
	public EditContactViewImpl(Element e, MainViewImpl main){
		super(e, main);
	}
	
	@Override
	protected void constructUi() {
		super.constructUi();
		name = TextBox.wrap(DOM.getElementById("name"));
		phone = TextBox.wrap(DOM.getElementById("phone"));
		saveButton = Button.wrap(DOM.getElementById("save-contact-button"));
		saveButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				presenter.onSaveButtonClicked();
			}
		});
	}

	@Override
	public void setSubmitButtonEnabled(boolean enabled) {
		saveButton.setEnabled(enabled);
	}

	@Override
	public void setName(String name) {
		this.name.setText(name);
	}

	@Override
	public void setPhone(String phone) {
		this.phone.setText(phone);
	}

	@Override
	public String getName() {
		return name.getText();
	}

	@Override
	public String getPhone() {
		return phone.getText();
	}

	@Override
	public void setPresenter(EditContactPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void switchToSaveNewContactButton() {
		saveButton.setText("Create");
	}

	@Override
	public void switchToSaveExistingContactButton() {
		saveButton.setText("Save");
	}
}
