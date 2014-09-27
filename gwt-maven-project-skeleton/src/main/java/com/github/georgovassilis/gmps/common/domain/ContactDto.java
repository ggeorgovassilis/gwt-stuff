package com.github.georgovassilis.gmps.common.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContactDto implements Serializable{
	
	private PersonalDetailsDto personalDetails;
	private List<AddressDto> addresses = new ArrayList<AddressDto>();

	public PersonalDetailsDto getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalDetailsDto personalDetails) {
		this.personalDetails = personalDetails;
	}

	public List<AddressDto> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressDto> addresses) {
		this.addresses = addresses;
	}


}
