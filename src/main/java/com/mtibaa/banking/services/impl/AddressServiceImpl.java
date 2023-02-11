package com.mtibaa.banking.services.impl;


import com.mtibaa.banking.dto.AddressDto;
import com.mtibaa.banking.models.Address;
import com.mtibaa.banking.repositories.AddressRepository;
import com.mtibaa.banking.services.AddressService;
import com.mtibaa.banking.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    private final ObjectsValidator<AddressDto> validator;

    @Override
    public Integer save(AddressDto dto) {
        validator.validate(dto);
        Address address = AddressDto.toEntity(dto);
        return repository.save(address).getId();
    }

    @Override
    public List<AddressDto> findAll() {
        return repository.findAll()
                .stream()
                .map(AddressDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {
        return repository.findById(id)
                .map(AddressDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("No address was found with the id : "+id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
