package com.nmp.ArgumentedReality.service;


import com.nmp.ArgumentedReality.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

/**
 * Created by Dominik on 2017-04-28.
 */
public interface UserService {

    boolean createUser(User newUser);

    User getUserByUsername(String username);

    User getUserById(int id);

    ArrayList<User> getUsers();

    boolean updateUser(User newUser);

    boolean deleteUser(int id);

    UserDetails loadUserByUsername(String username);

}
