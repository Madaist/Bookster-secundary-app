package com.rest.Register.repositories;

import com.rest.Register.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();

//    select * frm table where email = email_param
    Optional<User> findByEmail(String email);
/*
    @Transactional
    @Query(value = "SELECT * from user_tbl c WHERE c.id = :user_id", nativeQuery = true)
    Optional<User> findUserById(@Param("user_id") int user_id);
*/
    @Transactional
    @Modifying // pt query uri care modifica in baza
    @Query(value = "UPDATE [bookster].[dbo].user_tbl SET [first_name] = :first_name_param, " +
                                                        "[last_name] = :last_name_param, " +
                                                        "[email] = :email_param, " +
                                                        "[password]=:password_param, " +
                                                        "[user_name]=:username_param WHERE id = :user_id", nativeQuery = true)
    int updateUser(@Param("user_id") int user_id,
                   @Param("first_name_param") String first_name,
                   @Param("last_name_param") String last_name,
                   @Param("email_param") String email,
                   @Param("password_param") String password,
                   @Param("username_param") String username);



}
