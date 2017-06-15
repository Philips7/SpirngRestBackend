package com.nmp.ArgumentedReality.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Dominik on 2017-06-15.
 */
@Entity
public class Item implements Serializable{

    private static final long serialVersionUID = -1045102231733867985L;

    @Id
    @GeneratedValue
    @Column(name = "itemId")
    private Integer itemId;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "markerNumber")
    @NotNull
    private Integer markerNumber;

    public Item() {
    }

    public Item(String name, Integer markerNumber) {
        this.name = name;
        this.markerNumber = markerNumber;
    }

    public Item(Integer itemId, String name, Integer markerNumber) {
        this.itemId = itemId;
        this.name = name;
        this.markerNumber = markerNumber;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMarkerNumber() {
        return markerNumber;
    }

    public void setMarkerNumber(Integer markerNumber) {
        this.markerNumber = markerNumber;
    }
}
