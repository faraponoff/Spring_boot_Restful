package com.springBootRest.bootstrapRest1.BootstrapRest.repository;
import com.springBootRest.bootstrapRest1.BootstrapRest.model.User;
import org.springframework.data.repository.CrudRepository;



public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);
}
