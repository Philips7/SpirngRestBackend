package com.nmp.ArgumentedReality.dao.daoImpl;

import com.nmp.ArgumentedReality.dao.ItemDao;
import com.nmp.ArgumentedReality.entity.Item;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik on 2017-06-15.
 */
@Repository
public class ItemDaoImpl implements ItemDao {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    @Override
    public void createItem(Item item) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(item);
        session.flush();
    }

    @Transactional
    @Override
    public void updateItem(Item item) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(item);
        session.flush();
    }

    @Transactional
    @Override
    public void deleteItem(int id) {
        Session session = sessionFactory.getCurrentSession();
        Item item = getItemById(id);
        session.delete(item);
        session.flush();
    }

    @Transactional
    @Override
    public Item getItemById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Item item = session.get(Item.class, id);
        session.flush();
        return item;
    }

    @Transactional
    @Override
    public Item getItemByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Item where name = :name").setString("name", name);
        return (Item) query.uniqueResult();
    }

    @Transactional
    @Override
    public ArrayList<Item> getItems() {
        Session session = sessionFactory.getCurrentSession();
        List<Item> items = session.createCriteria(Item.class).list();
        ArrayList<Item> itemArrayList = new ArrayList<Item>();

        for (int i = 0; i < items.size(); i++) {
            itemArrayList.add(items.get(i));
        }
        return itemArrayList;
    }
}
