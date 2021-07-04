package com.springBoot.bootstrap1.Bootstrap.repository;
import com.springBoot.bootstrap1.Bootstrap.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);
    @Query("SELECT DISTINCT u FROM  User u LEFT JOIN FETCH u.roles r")
    List<User> showAllUsers();

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles r WHERE u.id = :id")
    User showUser(@Param("id")Long id);
}
