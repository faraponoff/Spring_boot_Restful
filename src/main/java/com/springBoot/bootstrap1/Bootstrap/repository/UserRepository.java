package com.springBoot.bootstrap1.Bootstrap.repository;
import com.springBoot.bootstrap1.Bootstrap.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);
}
