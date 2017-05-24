package com.nmp.ArgumentedReality.wrapper;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Dominik on 2017-05-23.
 */
public class UsersPost implements Serializable{

    private static final long serialVersionUID = 6312509243794348617L;

    @NotBlank(message = "can not be blank")
    @Size(min = 8, max = 30, message = "Min size: 8, Max size:30")
    private String name;

    @NotBlank(message = "can not be blank")
    @Size(min = 8, max = 30, message = "Min size: 8, Max size:30")
    private String username;

    @NotBlank(message = "can not be blank")
    @Size(min = 8, max = 30, message = "Min size: 8, Max size:30")
    private String password;

    @Email(message = "must be valid email")
    @NotBlank(message = "can not be blank")
    private String email;

    public UsersPost() {
    }

    public UsersPost(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
