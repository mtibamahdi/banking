package com.mtibaa.banking.repositories;

import com.mtibaa.banking.dto.ContactDto;
import com.mtibaa.banking.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findAllByUserId(Integer userId);
}
