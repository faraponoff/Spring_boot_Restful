package repository.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import repository.UserRepository;
import repository.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.model.User;

import java.util.List;
@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDAO userDAO;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
//        this.passwordEncoder = passwordEncoder;
//        this.userRepository = userRepository;
//    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);


    }

    public User updateUser(User user, Long roleNames) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User show(int id){ return userDAO.show(id);}

   @Autowired
    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
   @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails user = userDAO.getUserByEmail(s);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Юзера %s не существует", s));
        }
        return user;
    }

}
