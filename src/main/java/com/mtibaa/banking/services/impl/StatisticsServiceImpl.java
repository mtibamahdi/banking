package com.mtibaa.banking.services.impl;

import com.mtibaa.banking.models.Transaction;
import com.mtibaa.banking.models.TransactionType;
import com.mtibaa.banking.repositories.TransactionRepository;
import com.mtibaa.banking.services.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

@Service
@AllArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final TransactionRepository transactionRepository;

    @Override
    public Map<LocalDate, BigDecimal> findSumTransactionsByDate(LocalDate startDate, LocalDate endDate, Integer userId) {
        LocalDateTime start=LocalDateTime.of(startDate, LocalTime.of(0,0,0));
        LocalDateTime end=LocalDateTime.of(endDate, LocalTime.of(23,59,59));
        return transactionRepository.findSumTransactionByDate(start, end, userId);
    }

    @Override
    public BigDecimal getAccountBalance(Integer userId) {
        return transactionRepository.findAccountBalance(userId);
    }

    @Override
    public BigDecimal highestTransfer(Integer userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId, TransactionType.TRANSFER);
    }

    @Override
    public BigDecimal highestDeposit(Integer userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId, TransactionType.DEPOSIT);
    }
}
