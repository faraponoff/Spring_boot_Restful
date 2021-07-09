package com.BootstrapRest.dao;

import com.BootstrapRest.model.Role;
import java.util.List;

public interface RoleDao {
    List<Role> getRoleList();
    Role getRoleById(int id);
}
