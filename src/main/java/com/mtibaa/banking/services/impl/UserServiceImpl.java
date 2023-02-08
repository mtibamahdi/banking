package com.mtibaa.banking.services.impl;

import com.mtibaa.banking.dto.AccountDto;
import com.mtibaa.banking.dto.UserDto;
import com.mtibaa.banking.models.Account;
import com.mtibaa.banking.models.User;
import com.mtibaa.banking.repositories.AccountRepository;
import com.mtibaa.banking.repositories.UserRepository;
import com.mtibaa.banking.services.UserService;
import com.mtibaa.banking.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final AccountServiceImpl accountService;
    private final ObjectsValidator<UserDto> validator;

    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user=UserDto.toEntity(dto);
        return repository.save(user).getId();
    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return repository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No user was found with the provided ID :" + id));
    }

    @Override
    public void delete(Integer id) {
        // todo check before delete
        repository.deleteById(id);
    }

    @Override
    public Integer validateAccount(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("No user was found for user account validation"));
        user.setActive(true);
        //create a bank account
        AccountDto account = AccountDto.builder()
                        .user(UserDto.fromEntity(user))
                        .build();
        accountService.save(account);
        repository.save(user);
        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("No user was found for user account validation"));
        user.setActive(false);
        repository.save(user);
        return user.getId();

    }
}
