package com.nmp.ArgumentedReality.service.Impl;



import com.nmp.ArgumentedReality.dao.UserDao;
import com.nmp.ArgumentedReality.entity.User;
import com.nmp.ArgumentedReality.service.UserService;
import com.sun.org.apache.xalan.internal.xsltc.runtime.InternalRuntimeError;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;

/**
 * Created by Dominik on 2017-04-28.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public boolean createUser(User newUser) throws IllegalArgumentException, InternalRuntimeError{
        boolean success = false;
        try {
            User user = userDao.getUserByUsername(newUser.getUsername());
            if (user == null){
                userDao.createUser(newUser);
                success = true;
            } else {
                throw new IllegalArgumentException("User already exists");
            }
        } catch (HibernateException ex) {
            throw new InternalRuntimeError("Can not add user. Wrong data or server error");
        } catch (ConstraintViolationException ex) {
            throw new IllegalArgumentException("User not valid");
        }
        return success;
    }

    @Override
    public ArrayList<User> getUsers() {
        try {
            ArrayList<User> users = userDao.getUsers();
            return users;
        }catch (HibernateException ex){
            throw new InternalRuntimeError("Server error");
        }
    }

    @Override
    public User getUserByUsername(String username) {
        try {
            User user =  userDao.getUserByUsername(username);
            return user;
        } catch (HibernateException ex) {
            System.out.println(ex.getMessage());
            throw new InternalRuntimeError("More than one user with the sam username or Server error");
        }
    }

    @Override
    public User getUserById(int id) {
        try {
            User user =  userDao.getUserById(id);
            return user;
        } catch (HibernateException ex) {
            System.out.println(ex.getMessage());
            throw new InternalRuntimeError("Server error");
        }
    }

    @Override
    public boolean updateUser(User newUser) {
        boolean success = false;
        try {

            User user = userDao.getUserByUsername(newUser.getUsername());
            if (user != null){
                userDao.updateUser(newUser);
                success = true;
            } else {
                throw new IllegalArgumentException("User does not exist");
            }

        } catch (HibernateException ex) {
            throw new InternalRuntimeError("Server error" + "   " + ex.getMessage());
        }catch (ConstraintViolationException ex) {
            throw new IllegalArgumentException("User not valid");
        }
        return success;
    }

    @Override
    public boolean deleteUser(int id) {
        boolean success = false;
        try {
            userDao.deleteUser(id);
            success = true;
        } catch (HibernateException ex){
            throw new InternalRuntimeError("Server error");
        } catch (InvalidDataAccessApiUsageException ex) {
            throw new IllegalArgumentException("User does not exist");
        }
        return success;
    }
}
