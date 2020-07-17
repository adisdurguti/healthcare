package lab2.healthcare.healthcaresystem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank
    @Size(min = 3, max = 15)
    private String username;
    @NaturalId
    @Email
    @NotBlank
    @Size(max = 40)
    private String email;
    @NotBlank
    @Size(min = 8, max = 100)
    private String password;
    @Column(name = "active")
    private int active;

    private boolean isAccountNonExpired;

    private boolean isAccountNonLocked;

    private boolean isCredentialsNonExpired;

    private boolean isEnabled;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    @Override
    public Set<Role> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return isEnabled();
    }

}
