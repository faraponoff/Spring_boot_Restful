package repository.dao;

import org.springframework.stereotype.Repository;
import repository.model.Role;
import repository.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("roleDAO")
public class RoleDAOImpl implements RoleDAO {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

//    @Override
    public Role getRoleById(int id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role getRoleById(Integer[] role_id) {
        return null;
    }

    @Override
    public List getAllRoles() {
        return entityManager.createQuery("SELECT r FROM Role r").getResultList();
    }


    @Override
    public Role getRoleByName(String roleName) {
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.roleName= :name",Role.class)
                .setParameter("name", roleName)
                .setHint("javax.persistence.fetchgraph", roleName)
                .getSingleResult();
    }

    @Override
    public void deleteRole(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public Role saveRole(Role role) {
        entityManager.persist(role);

        return role;
    }

    @Override
    public Role updateRole(Role role) {
        entityManager.merge(role);
        return role;
    }
}
