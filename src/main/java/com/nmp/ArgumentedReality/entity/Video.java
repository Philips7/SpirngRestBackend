package com.nmp.ArgumentedReality.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dominik on 2017-06-03.
 */
@Entity
public class Video implements Serializable {

    private static final long serialVersionUID = -3072383924339090063L;

    @Id
    @GeneratedValue
    private Integer videoId;

    @Column(name = "filename")
    private String filename;

    @Column(name = "description")
    private String description;

    @Column(name = "extension")
    private String extension;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "lectureId")
    private Lecture lecture;

    public Video() {
    }

    public Video(Integer videoId, String filename, String description, String extension, Lecture lecture) {
        this.videoId = videoId;
        this.filename = filename;
        this.description = description;
        this.extension = extension;
        this.lecture = lecture;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }
}
