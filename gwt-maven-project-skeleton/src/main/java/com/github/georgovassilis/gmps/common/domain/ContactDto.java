package com.github.georgovassilis.gmps.common.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContactDto implements Serializable{
	
	private ContactCoverDto cover;
	private List<AddressDto> addresses = new ArrayList<AddressDto>();

	public ContactCoverDto getCover() {
		return cover;
	}

	public void setCover(ContactCoverDto cover) {
		this.cover = cover;
	}

	public List<AddressDto> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressDto> addresses) {
		this.addresses = addresses;
	}


}
