package com.mtibaa.banking.services.impl;

import com.mtibaa.banking.dto.AccountDto;
import com.mtibaa.banking.exceptions.OperationNonPermittedException;
import com.mtibaa.banking.models.Account;
import com.mtibaa.banking.repositories.AccountRepository;
import com.mtibaa.banking.repositories.UserRepository;
import com.mtibaa.banking.services.AccountService;
import com.mtibaa.banking.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final ObjectsValidator<AccountDto> validator;


    @Override
    public Integer save(AccountDto dto) {
        //block account update -> iban cannot be changed
        /* if(dto.getId() != null){
            throw new OperationNonPermittedException(
                    "Account cannot be updated",
                    "save account",
                    "Account",
                    "Update not permitted"
                    );
        }*/
        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);
        boolean userHasAlreadyAnAccount = repository.findByUserId(account.getUser().getId()).isPresent();
        if(userHasAlreadyAnAccount){
            throw new OperationNonPermittedException(
              "the selected user has already an active account",
              "Create account",
              "Account service",
              "Account creation"
            );
        }
        // todo generate random IBAN
        if(dto.getId() == null){
            account.setIban(generateRandomIban());
        }

        return repository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return repository.findAll()
                .stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return repository.findById(id)
                .map(AccountDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No account was found with the ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        //todo check delete account
        repository.deleteById(id);
    }

    private String generateRandomIban(){
        // generate an iban
        String iban = Iban.random(CountryCode.DE).toFormattedString();
        // check if the iban already exists
        boolean ibanExists = repository.findByIban(iban).isPresent();
        // if exists -> generate new random iban
        if(ibanExists){
            generateRandomIban();
        }
        // if not exist -> return generated iban

        return "";
    }
}
