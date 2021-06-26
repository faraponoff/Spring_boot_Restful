package repository;

import repository.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.function.Supplier;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Supplier<Object> findById(Integer[] id);
}
