package com.springBootRest.bootstrapRest1.BootstrapRest.repository;
import com.springBootRest.bootstrapRest1.BootstrapRest.model.Role;

import org.springframework.data.repository.CrudRepository;



public interface RoleRepository extends CrudRepository<Role, Long> {
}
