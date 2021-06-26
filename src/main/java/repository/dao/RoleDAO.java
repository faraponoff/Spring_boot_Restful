package repository.dao;

import repository.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleDAO{

    Role getRoleById(Integer[] role_id);

    List<Role> getAllRoles();

    void deleteRole(int id);

    Role saveRole(Role role);

    Role updateRole(Role role);

    Role getRoleByName(String roleName);
}
