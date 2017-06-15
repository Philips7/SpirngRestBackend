package com.nmp.ArgumentedReality.wrapper;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Dominik on 2017-06-15.
 */
public class ItemsPut implements Serializable {


    private static final long serialVersionUID = -6179225782273418299L;
    @NotNull(message = "can not be blank")
    private Integer itemId;

    @NotBlank(message = "can not be blank")
    @Size(min = 1, max = 30, message = "Min size: 8, Max size:30")
    private String name;

    @NotNull(message = "can not be empty")
    private Integer markerNumber;

    @NotBlank(message = "can not be blank")
    private String model;

    public ItemsPut() {
    }

    public ItemsPut(Integer itemId, String name, Integer markerNumber, String model) {
        this.itemId = itemId;
        this.name = name;
        this.markerNumber = markerNumber;
        this.model = model;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
