package crud.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@Data//ломбок аннотация: генерирует геттеры, сеттеры, иквалс, хеш код методы
@NoArgsConstructor//ломбок аннотация: конструктор без аргуметов
public class User {
    //implements UserDetails
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank (message = "Login is mandatory")
    @Column(name = "login", unique = true)
    private String name;

    @NotBlank (message = "Password is mandatory")
    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;

//   @Override
//   public Collection<? extends GrantedAuthority> getAuthorities() {
//      return roles;
//   }
//
//   @Override
//   public boolean isAccountNonExpired() {
//      return true;
//   }
//
//   @Override
//   public boolean isAccountNonLocked() {
//      return true;
//   }
//
//   @Override
//   public boolean isCredentialsNonExpired() {
//      return true;
//   }
//
//   @Override
//   public boolean isEnabled() {
//      return true;
//   }
}
