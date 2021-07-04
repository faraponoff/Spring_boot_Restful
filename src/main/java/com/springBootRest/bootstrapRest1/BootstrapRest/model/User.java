package com.springBootRest.bootstrapRest1.BootstrapRest.model;
import antlr.collections.List;
import lombok.Data;
import org.hibernate.mapping.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String username;
    @Column
    private int age;
    @Column
    private String password;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String toString() {
        return username + "     with roles: " + this.toStringRole();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String toStringRole() {
        StringBuilder builder = new StringBuilder();
        for (Role role : roles) {
            builder.append(role.getName()).append(" ");
        }
        return builder.toString().trim().substring(5);
    }

}
