package com.nmp.ArgumentedReality.controller;

import com.nmp.ArgumentedReality.entity.Item;
import com.nmp.ArgumentedReality.service.ItemService;
import com.nmp.ArgumentedReality.wrapper.Errors;
import com.nmp.ArgumentedReality.wrapper.ItemsPost;
import com.nmp.ArgumentedReality.wrapper.ItemsPut;
import com.sun.org.apache.xalan.internal.xsltc.runtime.InternalRuntimeError;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "/items/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteItem(@PathVariable("id") Integer id) {
        try {
            itemService.deleteItem(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException ex) {
            JSONObject response = new JSONObject();
            response.put("code", HttpStatus.NOT_FOUND);
            response.put("message", ex.getMessage());
            return new ResponseEntity<JSONObject>(response, HttpStatus.NOT_FOUND);
        } catch (InternalRuntimeError ex) {
            JSONObject response = new JSONObject();
            response.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    public ResponseEntity<?> createItem(@Valid @RequestBody ItemsPost newItem, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println(result.toString());
            List<FieldError> errorsS = result.getFieldErrors();
            JSONObject response = new JSONObject();

            Errors myErrors = new Errors(errorsS);
            myErrors.setAllErrors();

            return new ResponseEntity<List>(myErrors.getErrors(), HttpStatus.BAD_REQUEST);
        }

        try {
            Item item = new Item(newItem.getName(), newItem.getMarkerNumber(), newItem.getModel());
            itemService.createItem(item);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            JSONObject response = new JSONObject();
            response.put("code", HttpStatus.NOT_FOUND);
            response.put("message", ex.getMessage());
            return new ResponseEntity<JSONObject>(response, HttpStatus.NOT_FOUND);
        } catch (InternalRuntimeError ex) {
            JSONObject response = new JSONObject();
            response.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/items", method = RequestMethod.PUT, headers = "Accept=application/json" )
    public ResponseEntity<?> updateItem(@Valid @RequestBody ItemsPut newItem, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println(result.toString());
            List<FieldError> errorsS = result.getFieldErrors();
            JSONObject response = new JSONObject();

            Errors myErrors = new Errors(errorsS);
            myErrors.setAllErrors();

            return new ResponseEntity<List>(myErrors.getErrors(), HttpStatus.BAD_REQUEST);
        }
        try {
            Item item = new Item(newItem.getItemId(), newItem.getName(), newItem.getMarkerNumber(), newItem.getModel());
            itemService.updateItem(item);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            JSONObject response = new JSONObject();
            response.put("code", HttpStatus.BAD_REQUEST);
            response.put("message", ex.getMessage());
            return new ResponseEntity<JSONObject>(response, HttpStatus.BAD_REQUEST);
        } catch (InternalRuntimeError ex) {
            System.out.println(ex.getMessage());
            JSONObject response = new JSONObject();
            response.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("message", ex.getMessage());
            return new ResponseEntity<JSONObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
