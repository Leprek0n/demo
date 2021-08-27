package com.example.crud.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<User> getUserSet() {
        return users;
    }

    public void setUserSet(Set<User> userSet) {
        this.users = userSet;
    }

    @Override
    public String getAuthority() {
        return getUsername();
    }
}
