package com.service;

import com.model.Role;
import java.util.List;

public interface RoleService {
    List<Role> getRoleList();
    Role getRoleById(int id);
}
