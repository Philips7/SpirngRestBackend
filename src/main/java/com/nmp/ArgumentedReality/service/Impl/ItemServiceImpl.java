package com.nmp.ArgumentedReality.service.Impl;

import com.nmp.ArgumentedReality.dao.ItemDao;
import com.nmp.ArgumentedReality.entity.Item;
import com.nmp.ArgumentedReality.service.ItemService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Dominik on 2017-06-15.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemDao itemDao;

    @Override
    public void createItem(Item item) {
        boolean success = false;
        try {
            itemDao.createItem(item);
            success = true;
        } catch (HibernateException ex) {
            success = false;
        }
    }

    @Override
    public void updateItem(Item item) {
        boolean success = false;
        try {
            itemDao.updateItem(item);
            success = true;
        } catch (HibernateException ex) {
            success = false;
        }
    }

    @Override
    public void deleteItem(int id) {
        boolean success = false;
        try {
            itemDao.deleteItem(id);
            success = true;
        } catch (HibernateException ex) {
            success = false;
        }
    }

    @Override
    public Item getItemById(int id) {
        try {
            return itemDao.getItemById(id);
        } catch (HibernateException ex) {
            return null;
        }
    }

    @Override
    public Item getItemByName(String name) {
        try {
            return itemDao.getItemByName(name);
        } catch (HibernateException ex) {
            return null;
        }
    }

    @Override
    public ArrayList<Item> getItems() {
        try {
            return itemDao.getItems();
        } catch (HibernateException ex) {
            return null;
        }
    }
}
