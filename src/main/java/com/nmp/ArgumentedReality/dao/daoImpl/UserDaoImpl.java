package com.nmp.ArgumentedReality.dao.daoImpl;


import com.nmp.ArgumentedReality.dao.UserDao;
import com.nmp.ArgumentedReality.entity.User;
import com.nmp.ArgumentedReality.entity.UserRole;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik on 2017-04-28.
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void createUser(User newUser) throws ConstraintViolationException, HibernateException {
        Session session = sessionFactory.getCurrentSession();

        UserRole userRole1 = new UserRole(newUser.getUsername(), "ROLE_USER");
//        UserRole userRole2 = new UserRole(newUser.getUsername(), "ROLE_ADMIN");
        List<UserRole> newRoles = new ArrayList<UserRole>();
        newRoles.add(userRole1);
//        newRoles.add(userRole2); TODO REMOVE ROLE-ADMIN
        newUser.setUserRoles(newRoles);
        for (UserRole role : newRoles) {
            role.setUser(newUser);
        }

        session.save(newUser);
        session.flush();
    }

    @Override
    public ArrayList<User> getUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<User> users = session.createCriteria(User.class).list();
        ArrayList<User> users1 = new ArrayList<User>();

        for (int i = 0; i<users.size(); i++) {
            users1.add(users.get(i));
        }
        return users1;
    }

    @Override
    public User getUserByUsername(String username) throws HibernateException {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where username = :username ").setString("username", username);
        return (User) query.uniqueResult();
    }

    @Override
    public User getUserById(int id) throws HibernateException {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        session.flush();
        return user;
    }

    @Override
    public void updateUser(User newUser) throws ConstraintViolationException, HibernateException {

        Session session = sessionFactory.getCurrentSession();
        session.update(newUser);
        session.flush();
    }

    @Override
    public void deleteUser(int id) throws HibernateException, InvalidDataAccessApiUsageException {
        Session session = sessionFactory.getCurrentSession();
        User user = getUserById(id);
        session.delete(user);
        session.flush();
    }
}
