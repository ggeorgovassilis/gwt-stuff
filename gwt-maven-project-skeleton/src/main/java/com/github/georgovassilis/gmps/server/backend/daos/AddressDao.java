package com.github.georgovassilis.gmps.server.backend.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.georgovassilis.gmps.server.backend.domain.Address;

public interface AddressDao extends JpaRepository<Address, Long>{

}
