package repository.dao;

import repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUser(Long id);

    User saveUser(User user, String[] roleNames);

    User updateUser(User user, String[] roleNames);

    User getUserByEmail(String username);
    User show(int id);


}
