package com.nmp.ArgumentedReality.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dominik on 2017-04-28.
 */
@Entity
@Table(name = "user_role2")
public class UserRole implements Serializable{

    private static final long serialVersionUID = 4328803599154970536L;

    @Id
    @GeneratedValue
    private int user_role_id;

    @Column(name = "username")
    private String username;

    @Column(name = "role")
    private String role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserRole() {
    }

    public UserRole(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public int getUser_role_id() {
        return user_role_id;
    }

    public void setUser_role_id(int user_role_id) {
        this.user_role_id = user_role_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
