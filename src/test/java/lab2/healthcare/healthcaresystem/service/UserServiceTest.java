package lab2.healthcare.healthcaresystem.service;

import lab2.healthcare.healthcaresystem.models.Role;
import lab2.healthcare.healthcaresystem.models.User;
import lab2.healthcare.healthcaresystem.repository.RoleRepository;
import lab2.healthcare.healthcaresystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserServiceTest {


    @Autowired
    UserService userService;

    @Qualifier("userRepository")
    @Autowired
    UserRepository userRepository;

    @Qualifier("roleRepository")
    @Autowired
    RoleRepository roleRepository;


    @Test
    void findUserByUsername() {
    }

    @Test
    void saveUserPatient() {



        User actualUser = new User();
        actualUser.setEmail("unitTestPatient@gmail.com");
        actualUser.setUsername("unitTestPatient");
        actualUser.setPassword("T12345678");
        actualUser.setActive(1);

        Role role = roleRepository.findByName("ROLE_PATIENT");
        actualUser.setRoles(new HashSet<Role>(Arrays.asList(role)));
        userRepository.save(actualUser);

        String expectedUserName = userRepository.findByUsername("unitTestPatient").getUsername();
        assertEquals(actualUser.getUsername(),expectedUserName);




    }

    @Test
    void saveUserDoctor() {


        User actualUser = new User();
        actualUser.setEmail("unitTestDoctor@gmail.com");
        actualUser.setUsername("unitTestDoctor");
        actualUser.setPassword("T12345678");
        actualUser.setActive(1);

        Role role = roleRepository.findByName("ROLE_DOCTOR");
        actualUser.setRoles(new HashSet<Role>(Arrays.asList(role)));
        userRepository.save(actualUser);

        String expectedUserName = userRepository.findByUsername("unitTestDoctor").getUsername();
        assertEquals(actualUser.getUsername(),expectedUserName);



    }

    @Test
    void currentUser() {
    }
}