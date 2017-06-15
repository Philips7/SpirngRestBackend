package com.nmp.ArgumentedReality.wrapper;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Dominik on 2017-06-15.
 */
public class ItemsPost implements Serializable {

    private static final long serialVersionUID = 87429616345879812L;

    @NotBlank(message = "can not be blank")
    @Size(min = 1, max = 30, message = "Min size: 8, Max size:30")
    private String name;

    @NotNull(message = "can not be empty")
    private Integer markerNumber;

    public ItemsPost() {
    }

    public ItemsPost(String name, Integer markerNumber) {
        this.name = name;
        this.markerNumber = markerNumber;
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
