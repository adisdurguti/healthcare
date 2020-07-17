package lab2.healthcare.healthcaresystem.config;


import lab2.healthcare.healthcaresystem.controller.user.UserController;
import lab2.healthcare.healthcaresystem.service.UserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)

public class SpringSecurityConfigTest {

    public static final String USERNAME = "admin";
    public static final String WRONG_USERNAME = "wrong_username";
    public static final String PASSWORD = "7000000";
    public static final String WRONG_PASSWORD = "wrongPassword";

    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;

    @Test
    public void testLoginWithValidParameters_ShouldLogin() throws Exception {
        this.mvc
                .perform(formLogin("/login").user(USERNAME).password(PASSWORD))
                .andExpect(status().is3xxRedirection())
                .andExpect(authenticated().withUsername(USERNAME));
    }
    @Test
    @Ignore
    public void testLoginWithInValidUrl_ShouldNotLogin() throws Exception {
        this.mvc
                . perform(MockMvcRequestBuilders
                        .get("/patient/").accept(USERNAME,PASSWORD))
                .andExpect(status().isNotFound());
                //.andExpect(authenticated().withUsername(USERNAME));
    }
    @Test
    public void testLoginWithInvalidUsername_ShouldNotLogin() throws Exception {
        this.mvc
                .perform(formLogin("/login").user(WRONG_USERNAME).password(PASSWORD))
                .andExpect(status().is3xxRedirection())
                .andExpect(unauthenticated());
    }

    @Test
    public void testLoginWithInvalidPassword_ShouldNotLogin() throws Exception {
        this.mvc
                .perform(formLogin("/login").user(USERNAME).password(WRONG_PASSWORD))
                .andExpect(status().is3xxRedirection())
                .andExpect(unauthenticated());
    }

    @Configuration
    static class TestSpringConfig extends WebSecurityConfigurerAdapter {

        BCryptPasswordEncoder bCryptPasswordEncoder;
        @Autowired
        protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            auth
                    .inMemoryAuthentication()
                    .withUser(USERNAME)
                    .password(encoder.encode(PASSWORD))
                    .roles("ADMIN");
        }
    }
}