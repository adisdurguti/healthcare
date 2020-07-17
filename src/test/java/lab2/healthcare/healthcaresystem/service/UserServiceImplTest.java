package lab2.healthcare.healthcaresystem.service;


import lab2.healthcare.healthcaresystem.models.Role;
import lab2.healthcare.healthcaresystem.models.User;
import lab2.healthcare.healthcaresystem.repository.RoleRepository;
import lab2.healthcare.healthcaresystem.repository.UserRepository;
import org.assertj.core.api.BDDAssumptions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.empty;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    RoleRepository roleRepository;


    @Test
    public void findUserByUsernameTest() {
        User actualUser = new User();
        actualUser.setEmail("drin14dalipi@gmail.com");
        actualUser.setUsername("drin");
        actualUser.setPassword("D12345678");
        actualUser.setActive(1);
        Role role = roleRepository.findByName("ROLE_PATIENT");
        actualUser.setRoles(new HashSet<Role>(Arrays.asList(role)));
        when(userRepository.findByUsername("drin")).thenReturn(actualUser);
        //when(userService.currentUser()).thenReturn(actualUser);
        Assert.assertEquals(actualUser, userServiceImpl.findUserByUsername("drin"));
    }


    @Test
    public void saveUserTest() {
        User user = new User();
        user.setId((long)1);
        user.setEmail("drin14dalipi@gmail.com");
        user.setUsername("drin");
        user.setPassword("D12345678");
        user.setActive(1);
        Role role = roleRepository.findByName("ROLE_PATIENT");
        user.setRoles(new HashSet<Role>(Arrays.asList(role)));
        /*when(userRepository.findByUsername(user.getUsername())).thenReturn(Mockito.any());
        when(userServiceImpl.createUser(Mockito.any())).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));*/

        when(userRepository.save(user)).thenReturn(new User());
        User userexcepted = new User();
        Assert.assertNotNull(userServiceImpl.createUser(userexcepted));
        /*User savedUser = userServiceImpl.createUser(user);
        Assert.assertNotNull(savedUser);*/
        //verify(userRepository).save(any(User.class));
    }
}
