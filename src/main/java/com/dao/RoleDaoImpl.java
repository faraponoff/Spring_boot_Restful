package com.dao;

import com.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> getRoleList() {
        return entityManager.createQuery("from Role").getResultList();
    }

    @Override
    public Role getRoleById(int id) {
        return entityManager.find(Role.class, id);
    }
}
