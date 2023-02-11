package com.mtibaa.banking.services.impl;

import com.mtibaa.banking.dto.TransactionDto;
import com.mtibaa.banking.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody TransactionDto transactionDto
    ){
        return ResponseEntity.ok(service.save(transactionDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<TransactionDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{transaction-id}")
    public ResponseEntity<TransactionDto>findById(
            @PathVariable("transaction-id") Integer transactionId
    ){
        return ResponseEntity.ok(service.findById(transactionId));
    }

    @DeleteMapping("/{transaction-id}")
    public ResponseEntity<Void>delete(
            @PathVariable("transaction-id") Integer transactionId
    ){
        service.delete(transactionId);
        return ResponseEntity.accepted().build();
    }
}
