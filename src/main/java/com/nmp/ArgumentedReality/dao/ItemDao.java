package com.nmp.ArgumentedReality.dao;

import com.nmp.ArgumentedReality.entity.Item;

import java.util.ArrayList;

/**
 * Created by Dominik on 2017-06-15.
 */
public interface ItemDao {

    void createItem(Item video);

    void updateItem(Item video);

    void deleteItem(int id);

    Item getItemById(int id);

    Item getItemByName(String name);

    ArrayList<Item> getItems();
}
