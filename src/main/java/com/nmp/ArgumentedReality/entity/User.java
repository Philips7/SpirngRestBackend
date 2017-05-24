package com.nmp.ArgumentedReality.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Dominik on 2017-04-28.
 */
@Entity
@Table(name = "user2")
public class User implements Serializable {

    private static final long serialVersionUID = -9083726693828536598L;

    @Id
    @GeneratedValue
    @ApiModelProperty(notes = "The database generated product ID")
    private int user_id;

    @NotBlank
    @Size(min = 8, max = 30)
    @Column(name = "username", unique = true)
    @ApiModelProperty(notes = "Client username. Min length = 8. Max length = 30", required = true)
    private String username;

    @NotBlank
    @Size(min = 8, max = 30)
    @Column(name = "password")
    @ApiModelProperty(notes = "Client password. Min length = 8. Max length = 30", required = true)
    private String password;

    @NotNull
    @Column(name = "enabled")
    @JsonIgnore
    private boolean enabled;

    @NotBlank
    @Email
    @Column(name = "email")
    @ApiModelProperty(notes = "Client email has to be valid email", required = true)
    private String email;

    @NotBlank
    @Size(min = 8, max = 30)
    @Column(name = "name")
    @ApiModelProperty(notes = "Client name. Min length = 8. Max length = 30", required = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private Collection<UserRole> userRoles = new ArrayList<UserRole>();

    public User() {
    }

    public User(String username, String password, String email, String name) throws ConstraintViolationException {
        this.username = username;
        this.password = password;
        this.enabled = true;
        this.email = email;
        this.name = name;
    }

    public User(int user_id, String username, String password, String email, String name) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.enabled = true;
        this.email = email;
        this.name = name;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Collection<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", userRoles=" + userRoles +
                '}';
    }
}
