package com.mtibaa.banking.dto;

import com.mtibaa.banking.models.Contact;
import com.mtibaa.banking.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ContactDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String iban;
    private Integer userId;

    public static ContactDto fromEntity(Contact contact){
        return ContactDto.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .userId(contact.getUser().getId())
                .build();
    }

    public static Contact toEntity(ContactDto contact){
        return Contact.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .user(
                        User.builder()
                                .id(contact.getUserId())
                                .build()
                )
                .build();
    }
}
