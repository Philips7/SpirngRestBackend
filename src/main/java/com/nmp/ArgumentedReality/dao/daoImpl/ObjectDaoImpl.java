package com.nmp.ArgumentedReality.dao.daoImpl;

import com.nmp.ArgumentedReality.dao.ObjectDao;
import com.nmp.ArgumentedReality.entity.Object;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Dominik on 2017-05-18.
 */
@Transactional
@Repository
public class ObjectDaoImpl implements ObjectDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void createObject(Object object) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(object);
        session.flush();
    }

    @Override
    public void updateObject(Object object) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(object);
        session.flush();
    }

    @Override
    public void deleteObject(int id) {
        Session session = sessionFactory.getCurrentSession();
        Object object = getObjectById(id);
        session.delete(object);
        session.flush();
    }

    @Override
    public Object getObjectById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Object object = session.get(Object.class, id);
        session.flush();
        return object;
    }

    @Override
    public Object getObjectByDescription(String description) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Object where description = :description").setString("description", description);
        return (Object) query.uniqueResult();
    }
}
