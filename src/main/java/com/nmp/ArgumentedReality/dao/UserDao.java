package com.nmp.ArgumentedReality.dao;




import com.nmp.ArgumentedReality.entity.User;

import java.util.ArrayList;

/**
 * Created by Dominik on 2017-04-28.
 */

public interface UserDao {

    void createUser(User newUser);

    User getUserByUsername(String username);

    User getUserById(int id);

    ArrayList<User> getUsers();

    void updateUser(User newUser);

    void deleteUser(int id);
}
