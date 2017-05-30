package com.nmp.ArgumentedReality.dao;

import com.nmp.ArgumentedReality.entity.Object;

/**
 * Created by Dominik on 2017-05-18.
 */

public interface ObjectDao {

    void createObject(Object object);

    void updateObject(Object object);

    void deleteObject(int id);

    Object getObjectById(int id);

    Object getObjectByDescription(String description);
}
