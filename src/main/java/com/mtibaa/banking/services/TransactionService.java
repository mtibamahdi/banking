package com.mtibaa.banking.services;

import com.mtibaa.banking.dto.TransactionDto;

import java.util.List;


public interface TransactionService extends AbstractService<TransactionDto>{

    List<TransactionDto> findAllByUserId(Integer userId);
}
