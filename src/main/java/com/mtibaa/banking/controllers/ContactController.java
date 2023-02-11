package com.mtibaa.banking.controllers;

import com.mtibaa.banking.dto.ContactDto;
import com.mtibaa.banking.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService service;
    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody ContactDto contactDto
            ){
        return ResponseEntity.ok(service.save(contactDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<ContactDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{account-id}")
    public ResponseEntity<ContactDto> findById(
            @PathVariable("account-id") Integer accountId
    ){
        return ResponseEntity.ok(service.findById(accountId));
    }

    @PatchMapping("/{account-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("account-id") Integer accountId
    ){
        service.delete(accountId);
        return ResponseEntity.accepted().build();
    }


}
