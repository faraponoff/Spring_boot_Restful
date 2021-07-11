package com.dao;

import com.model.Role;
import java.util.List;

public interface RoleDao {
    List<Role> getRoleList();
    Role getRoleById(int id);
}
