package lab2.healthcare.healthcaresystem.config;


import lab2.healthcare.healthcaresystem.controller.user.UserController;
import lab2.healthcare.healthcaresystem.service.UserService;
import org.hamcrest.CoreMatchers;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)

public class SpringSecurityConfigTest {

    public static final String USERNAME = "admin";
    public static final String WRONG_USERNAME = "wrong_username";
    public static final String PASSWORD = "123";
    public static final String WRONG_PASSWORD = "wrongPassword";

    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;


    @Test
    public void contextLoads()throws Exception{
        this.mvc.perform(MockMvcRequestBuilders.get("/login"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                /*.andExpect(content().string(CoreMatchers.containsString("Please sign in")))
                .andExpect(content().string(CoreMatchers.containsString("Username")))
                .andExpect(content().string(CoreMatchers.containsString("Password")))*/
                .andExpect(content().string(CoreMatchers.containsString("Sign in")));
    }


    @Test
    public void testLoginWithValidParameters_ShouldLogin() throws Exception {
        this.mvc
                .perform(formLogin("/login").user(USERNAME).password(PASSWORD))
                .andExpect(status().is3xxRedirection())
                .andExpect(authenticated().withUsername(USERNAME));
    }
    @Test
    public void testLoginWithUnAuthorized_ShouldNotLogin() throws Exception {
        this.mvc
                . perform(MockMvcRequestBuilders
                        .get("/patient").accept(USERNAME,PASSWORD))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
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