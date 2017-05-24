package com.nmp.ArgumentedReality.wrapper;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Dominik on 2017-05-23.
 */
public class UsersPut implements Serializable {

    private static final long serialVersionUID = 5189843619717385927L;

    @NotNull(message = "can not be blank")
    private Integer id;

    @NotBlank(message = "can not be blank")
    @Size(min = 8, max = 30, message = "Min size: 8, Max size:30")
    private String username;

    @NotBlank(message = "can not be blank")
    @Size(min = 8, max = 30, message = "Min size: 8, Max size:30")
    private String password;

    @NotBlank(message = "can not be blank")
    @Size(min = 8, max = 30, message = "Min size: 8, Max size:30")
    private String name;

    @Email(message = "must be valid email")
    @NotBlank(message = "can not be blank")
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
