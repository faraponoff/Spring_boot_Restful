package repository.service;

import org.springframework.stereotype.Service;
import repository.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import repository.RoleRepository;

import java.util.List;
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role getRoleById(Integer[] id) {
        return (Role) roleRepository.findById(id).get();
    }

    public List<Role> getAllRoles() {

        return roleRepository.findAll();

    }

    public void deleteRole(int id) {
        roleRepository.deleteById(id);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(int id) {
        return roleRepository.save(roleRepository.getOne(id));
    }

//    public Role getRoleByName(String roleName) {
//        return null;
//    }

}
