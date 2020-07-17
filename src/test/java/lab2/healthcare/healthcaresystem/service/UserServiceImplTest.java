package lab2.healthcare.healthcaresystem.service;


import lab2.healthcare.healthcaresystem.models.Role;
import lab2.healthcare.healthcaresystem.models.User;
import lab2.healthcare.healthcaresystem.repository.RoleRepository;
import lab2.healthcare.healthcaresystem.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    RoleRepository roleRepository;





    @Test
    public void findUserByUsernameTest(){

        User actualUser = new User();
        actualUser.setEmail("drin14dalipi@gmail.com");
        actualUser.setUsername("drin");
        actualUser.setPassword("D12345678");
        actualUser.setActive(1);

        Role role = roleRepository.findByName("ROLE_PATIENT");
        actualUser.setRoles(new HashSet<Role>(Arrays.asList(role)));

        when(userRepository.findByUsername("drin")).thenReturn(actualUser);
        //when(userService.currentUser()).thenReturn(actualUser);
        Assert.assertEquals(actualUser,userServiceImpl.findUserByUsername("drin"));


    }


    @Test
    public void saveUserTest(){




    }

}
