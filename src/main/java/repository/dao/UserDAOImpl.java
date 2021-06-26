package repository.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import repository.model.Role;
import repository.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Repository("userDAO")
public class UserDAOImpl implements UserDAO {


    private EntityManager entityManager;

    @Override
    public User show(int id) {
        return entityManager.find(User.class, id);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByEmail(String email) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.email= :email", User.class)
                .setParameter("email", email.toLowerCase())
                .setHint("javax.persistence.fetchgraph", email)
                .getSingleResult();
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public User saveUser(User user, String[] roleNames) {
        Set<Role> roles = new HashSet<>();
        for (String roleName : roleNames) {
            roles.add(entityManager.createQuery("select r from Role r where r.role= :roleName", Role.class)
                    .setParameter("roleName", roleName)
                    .setHint("javax.persistence.fetchgraph", roleName)
                    .getSingleResult());
        }
        user.setRoles(roles);
        entityManager.persist(user);

        return user;
    }

    @Override
    public User updateUser(User user, String[] roleNames) {
        Set<Role> roles = new HashSet<>();
        for (String roleName : roleNames) {
            roles.add(entityManager.createQuery("select r from Role r where r.role= :roleName", Role.class)
                    .setParameter("roleName", roleName)
                    .setHint("javax.persistence.fetchgraph", roleName)
                    .getSingleResult());
        }
        user.setRoles(roles);

        entityManager.merge(user);
        return user;
    }
}
