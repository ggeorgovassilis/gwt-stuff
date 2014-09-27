package com.github.georgovassilis.gmps;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.github.georgovassilis.gmps.common.api.AddressBookService;
import com.github.georgovassilis.gmps.common.domain.AddressDto;
import com.github.georgovassilis.gmps.common.domain.PersonalDetailsDto;
import com.github.georgovassilis.gmps.common.domain.ContactDto;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:gmps/application-context.xml",
		"classpath:gmps/test-configuration-context.xml" })
@Transactional
public class AddressBookServiceImplTest {

	@Resource
	AddressBookService service;

	@Test
	public void testCRUD() {
		ContactDto contact = new ContactDto();
		contact.setPersonalDetails(new PersonalDetailsDto());
		contact.getPersonalDetails().setName("Joe Doe I");
		contact.getPersonalDetails().setPhoneNumber("12345");
		contact = service.newContact(contact.getPersonalDetails());
		assertEquals("Joe Doe I", contact.getPersonalDetails().getName());
		assertEquals("12345", contact.getPersonalDetails().getPhoneNumber());
		
		contact.getPersonalDetails().setName("Joe Doe");
		contact = service.updateContactDetails(contact.getPersonalDetails());

		AddressDto address1 = new AddressDto();
		address1.setCity("Gotham City");
		address1.setStreetAndNumber("Sunset ave");
		address1 = service.newAddressForContact(contact.getPersonalDetails()
				.getId(), address1);

		address1.setStreetAndNumber("Sunset bvd");
		address1 = service.update(address1);

		assertEquals("Gotham City", address1.getCity());
		assertEquals("Sunset bvd", address1.getStreetAndNumber());

		AddressDto address2 = new AddressDto();
		address2.setCity("Berlin");
		address2.setStreetAndNumber("Freiheitsplz");
		address2 = service.newAddressForContact(contact.getPersonalDetails()
				.getId(), address2);

		contact = service.getContact(contact.getPersonalDetails().getId());
		assertEquals("Joe Doe", contact.getPersonalDetails().getName());
		assertEquals("12345", contact.getPersonalDetails().getPhoneNumber());
		assertEquals("Gotham City", contact.getAddresses().get(0).getCity());
		assertEquals("Sunset bvd", contact.getAddresses().get(0)
				.getStreetAndNumber());

		assertEquals("Berlin", contact.getAddresses().get(1).getCity());
		assertEquals("Freiheitsplz", contact.getAddresses().get(1)
				.getStreetAndNumber());
		
		service.deleteAddress(contact.getAddresses().get(0).getId());
		contact = service.getContact(contact.getPersonalDetails().getId());
		assertEquals(1, contact.getAddresses().size());
		service.deleteContact(contact.getPersonalDetails().getId());
		contact = service.getContact(contact.getPersonalDetails().getId());
		assertNull(contact);
	}
}
