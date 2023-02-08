package com.mtibaa.banking.services;

import com.mtibaa.banking.dto.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto>{
    List<ContactDto> findAllByUserId(Integer userId);
}
