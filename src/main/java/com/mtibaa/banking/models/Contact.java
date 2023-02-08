package com.mtibaa.banking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contact extends AbstractEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String iban;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

}
