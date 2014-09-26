package com.github.georgovassilis.gmps;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.github.georgovassilis.gmps.common.api.AddressBookService;
import com.github.georgovassilis.gmps.common.domain.AddressDto;
import com.github.georgovassilis.gmps.common.domain.ContactCoverDto;
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
		contact.setCover(new ContactCoverDto());
		contact.getCover().setName("Joe Doe I");
		contact.getCover().setPhoneNumber("12345");
		contact = service.newContact(contact.getCover());
		assertEquals("Joe Doe I", contact.getCover().getName());
		assertEquals("12345", contact.getCover().getPhoneNumber());
		
		contact.getCover().setName("Joe Doe");
		contact = service.updateContactDetails(contact.getCover());

		AddressDto address1 = new AddressDto();
		address1.setCity("Gotham City");
		address1.setCountry("USA");
		address1.setExtra("ABCD");
		address1.setPostalCode("123");
		address1.setStreetAndNumber("Sunset bvd");
		address1 = service.newAddressForContact(contact.getCover()
				.getId(), address1);

		address1.setExtra("ABC");
		address1 = service.update(address1);

		assertEquals("Gotham City", address1.getCity());
		assertEquals("USA", address1.getCountry());
		assertEquals("ABC", address1.getExtra());
		assertEquals("123", address1.getPostalCode());
		assertEquals("Sunset bvd", address1.getStreetAndNumber());

		AddressDto address2 = new AddressDto();
		address2.setCity("Berlin");
		address2.setCountry("Germany");
		address2.setExtra("DEF");
		address2.setPostalCode("456");
		address2.setStreetAndNumber("Freiheitsplz");
		address2 = service.newAddressForContact(contact.getCover()
				.getId(), address2);

		contact = service.getContact(contact.getCover().getId());
		assertEquals("Joe Doe", contact.getCover().getName());
		assertEquals("12345", contact.getCover().getPhoneNumber());
		assertEquals("Gotham City", contact.getAddresses().get(0).getCity());
		assertEquals("USA", contact.getAddresses().get(0).getCountry());
		assertEquals("ABC", contact.getAddresses().get(0).getExtra());
		assertEquals("123", contact.getAddresses().get(0).getPostalCode());
		assertEquals("Sunset bvd", contact.getAddresses().get(0)
				.getStreetAndNumber());

		assertEquals("Berlin", contact.getAddresses().get(1).getCity());
		assertEquals("Germany", contact.getAddresses().get(1).getCountry());
		assertEquals("DEF", contact.getAddresses().get(1).getExtra());
		assertEquals("456", contact.getAddresses().get(1).getPostalCode());
		assertEquals("Freiheitsplz", contact.getAddresses().get(1)
				.getStreetAndNumber());
		
		service.deleteAddress(contact.getAddresses().get(0).getId());
		contact = service.getContact(contact.getCover().getId());
		assertEquals(1, contact.getAddresses().size());
		service.deleteContact(contact.getCover().getId());
		contact = service.getContact(contact.getCover().getId());
		assertNull(contact);
	}
}
