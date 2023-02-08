package com.mtibaa.banking.dto;

import com.mtibaa.banking.models.Address;
import com.mtibaa.banking.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AddressDto {
    private Integer id;
    private String street;
    private Integer houseNumber;
    private Integer zipCode;
    private String city;
    private String country;
    private Integer userID;

    public static AddressDto fromEntity(Address address){
        return AddressDto.builder()
                .id(address.getId())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .country(address.getCountry())
                .userID(address.getUser().getId())
                .build();
    }
    public static Address toEntity(AddressDto address){
        return (Address) Address.builder()
                .id(address.getId())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .country(address.getCountry())
                .user(
                        User.builder()
                                .id(address.getUserID())
                                .build()
                )

                .build();
    }
}
