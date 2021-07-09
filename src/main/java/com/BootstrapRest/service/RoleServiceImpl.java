package com.BootstrapRest.service;

import com.BootstrapRest.dao.RoleDao;
import com.BootstrapRest.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getRoleList() {
        return roleDao.getRoleList();
    }

    @Override
    public Role getRoleById(int id) {
        return roleDao.getRoleById(id);
    }
}

