package com.springBootRest.bootstrapRest1.BootstrapRest.model;
import antlr.collections.List;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private List<User> users;

    public Role() {
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
