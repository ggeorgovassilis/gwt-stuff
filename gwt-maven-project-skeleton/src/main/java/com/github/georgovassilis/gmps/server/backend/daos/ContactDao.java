package com.github.georgovassilis.gmps.server.backend.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.georgovassilis.gmps.common.domain.ContactDto;
import com.github.georgovassilis.gmps.server.backend.domain.Contact;

public interface ContactDao extends JpaRepository<Contact, Long>{

}
