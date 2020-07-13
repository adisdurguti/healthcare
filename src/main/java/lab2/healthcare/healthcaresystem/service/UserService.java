package lab2.healthcare.healthcaresystem.service;


import lab2.healthcare.healthcaresystem.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findUserByUsername(String username);

    void saveUser(User user);

    void saveUserDoctor(User user);

    User currentUser();
}
