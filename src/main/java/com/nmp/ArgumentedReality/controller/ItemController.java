package com.nmp.ArgumentedReality.controller;

import com.nmp.ArgumentedReality.entity.Item;
import com.nmp.ArgumentedReality.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by Dominik on 2017-06-15.
 */
@Controller
public class ItemController {

    @Autowired
    ItemService itemService;

    @RequestMapping(value = "/items/{id}", method = RequestMethod.GET)
    ResponseEntity<?> getItemById(HttpServletRequest request,
                                 HttpServletResponse response, @PathVariable("id") Integer id) throws Exception {
        Item item = itemService.getItemById(id);
        if (item != null){
            return new ResponseEntity<Item>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/items/name/{name}", method = RequestMethod.GET)
    ResponseEntity<?> getItemByName(HttpServletRequest request,
                                 HttpServletResponse response, @PathVariable("name") String name) throws Exception {
        Item item = itemService.getItemByName(name);
        if (item != null){
            return new ResponseEntity<Item>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    ResponseEntity<?> getItems(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArrayList<Item> items = itemService.getItems();
        if (items != null) {
            return new ResponseEntity<ArrayList<Item>>(items, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
    }
}
