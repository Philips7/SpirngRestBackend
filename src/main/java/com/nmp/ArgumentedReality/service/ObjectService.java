package com.nmp.ArgumentedReality.service;

import com.nmp.ArgumentedReality.entity.Object;

/**
 * Created by Dominik on 2017-05-18.
 */
public interface ObjectService {
    boolean createObject(Object object);

    boolean updateObject(Object object);

    boolean deleteObject(int id);

    Object getObjectById(int id);

    Object getObjectByDescription(String description);
}
