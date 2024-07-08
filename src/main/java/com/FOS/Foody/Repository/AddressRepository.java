package com.FOS.Foody.Repository;

import com.FOS.Foody.model.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Addresses,Long> {

}
