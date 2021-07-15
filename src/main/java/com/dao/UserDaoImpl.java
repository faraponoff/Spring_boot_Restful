package com.dao;

import com.model.User;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private RoleService roleService;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserByEmail(String email) {
        return (User) entityManager.createQuery("from User where email = :email")
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void addUser(User user, List<Integer> roles) {
        if (roles.size() > 0) {

            roles.forEach(roleIndex -> user.getRoles().add(roleService.getRoleById(roleIndex)));
        }
        entityManager.persist(user);
    }

    @Override
    public void updateUser(int id, User newUser, List<Integer> roles) {
        if (roles.size() > 0) {
            roles.forEach(roleIndex -> newUser.getRoles().add(roleService.getRoleById(roleIndex)));
        }
        User user = entityManager.find(User.class, id);
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setAge(newUser.getAge());
        user.setEmail(newUser.getEmail());
        user.setRoles(newUser.getRoles());
        if (!newUser.getPassword().equals("")) {
            user.setPassword(newUser.getPassword());
        }
    }

    @Override
    public void deleteUser(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User").getResultList();
    }
}
