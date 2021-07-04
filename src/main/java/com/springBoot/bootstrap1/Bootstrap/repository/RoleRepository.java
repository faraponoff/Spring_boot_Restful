package com.springBoot.bootstrap1.Bootstrap.repository;
import com.springBoot.bootstrap1.Bootstrap.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RoleRepository extends CrudRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.id IN (:userIds)")
    List<Role> getRoles(@Param("userIds")List<Long> userIds);
}
