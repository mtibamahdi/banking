package com.mtibaa.banking.repositories;

import com.mtibaa.banking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository <User, Integer> {
        // Select * from _user where firstName = 'ali'
        List<User> findAllByFirstName(String firstname);
        // Select * from _user where firstName like 'ali'
        List<User> findAllByFirstNameContaining(String firstname);
        // Select * from _user where firstName ilike 'ali'
        List<User> findAllByFirstNameContainingIgnoreCase(String firstname);
        // Select * from _user u inner join account a on u.id = a.id_user and a.iban = 'De12345678'
        List<User> findAllByAccountIban(String iban);
        // Select * from _user where firstname = %ali% and email = 'bouali.pro@gmail.com'
        User findAllByFirstNameContainingIgnoreCaseAndEmail(String firstname, String email);

        @Query("from User where firstName = :fn")
        List<User> searchByFirstName(@Param("fn") String firstname);

        @Query("from User u inner join Account a on u.id = a.user.id where a.iban = :iban")
        List<User> searchByIban(String iban);

        @Query(value = "select * from _user u inner join account a on u.id = a.id_user and a.iban = :iban", nativeQuery = true)
        List<User> searchByIbanNative(String iban);





}
