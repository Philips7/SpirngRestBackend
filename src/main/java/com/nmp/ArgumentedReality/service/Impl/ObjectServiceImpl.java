package com.nmp.ArgumentedReality.service.Impl;

import com.nmp.ArgumentedReality.dao.ObjectDao;
import com.nmp.ArgumentedReality.entity.Object;
import com.nmp.ArgumentedReality.service.ObjectService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Dominik on 2017-05-18.
 */
@Service
public class ObjectServiceImpl implements ObjectService {

    @Autowired
    ObjectDao objectDao;

    @Override
    public boolean createObject(Object object) {
        boolean success = false;
        try {
            objectDao.createObject(object);
            success = true;
        } catch (HibernateException ex) {
            success = false;
        }
        return success;
    }

    @Override
    public boolean updateObject(Object object) {
        boolean success = false;
        try {
            objectDao.updateObject(object);
            success = true;
        } catch (HibernateException ex) {
            success = false;
        }
        return success;
    }

    @Override
    public boolean deleteObject(int id) {
        boolean success = false;
        try {
            objectDao.deleteObject(id);
            success = true;
        } catch (HibernateException ex) {
            success = false;
        }
        return success;
    }

    @Override
    public Object getObjectById(int id) {
        try {
            return objectDao.getObjectById(id);
        } catch (HibernateException ex) {
            return null;
        }
    }

    @Override
    public Object getObjectByDescription(String description) {
        try {
            return objectDao.getObjectByDescription(description);
        } catch (HibernateException ex) {
            return null;
        }
    }
}
