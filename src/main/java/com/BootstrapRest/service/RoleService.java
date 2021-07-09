package com.BootstrapRest.service;

import com.BootstrapRest.model.Role;
import java.util.List;

public interface RoleService {
    List<Role> getRoleList();
    Role getRoleById(int id);
}
