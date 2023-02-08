package com.mtibaa.banking.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "_user")
public class User extends AbstractEntity{
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean active;
    //    private Address address;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;

    @OneToOne
    private Address address;

    @OneToOne
    private Account account;

    @OneToOne
    private Role role;

}
