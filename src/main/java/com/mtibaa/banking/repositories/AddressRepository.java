package com.mtibaa.banking.repositories;

import com.mtibaa.banking.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
