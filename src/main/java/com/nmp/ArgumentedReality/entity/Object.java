package com.nmp.ArgumentedReality.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dominik on 2017-05-18.
 */
@Entity
public class Object implements Serializable{

    private static final long serialVersionUID = -8142038568810277245L;

    @Id
    @GeneratedValue
    private int objectId;

    @Column(name = "description")
    private String description;

    @Transient
    @JsonIgnore
    private MultipartFile multipartFile;

    public Object() {
    }

    public Object(String description, MultipartFile multipartFile) {
        this.description = description;
        this.multipartFile = multipartFile;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
