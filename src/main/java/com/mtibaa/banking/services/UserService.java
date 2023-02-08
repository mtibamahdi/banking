package com.mtibaa.banking.services;

import com.mtibaa.banking.dto.UserDto;

public interface UserService extends AbstractService<UserDto> {
    Integer validateAccount(Integer id);
    Integer invalidateAccount(Integer id);
}
