package com.nmp.ArgumentedReality.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Dominik on 2017-06-03.
 */
@Entity
public class Lecture implements Serializable {

    private static final long serialVersionUID = 3550231720350189758L;

    @Id
    @GeneratedValue
    @Column(name = "lectureId")
    private Integer lectureId;

    @Column(name = "name")
    private String name;

    @Column(name = "teacherName")
    private String teacherName;

    @Column(name = "teacherSurname")
    private String teacherSurname;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lecture", fetch = FetchType.LAZY)
    private List<Video> videos;

    public Lecture() {
    }

    public Lecture(Integer lectureId, String name, String teacherName, String teacherSurname, List<Video> videos) {
        this.lectureId = lectureId;
        this.name = name;
        this.teacherName = teacherName;
        this.teacherSurname = teacherSurname;
        this.videos = videos;
    }

    public Integer getLectureId() {
        return lectureId;
    }

    public void setLectureId(Integer lectureId) {
        this.lectureId = lectureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherSurname() {
        return teacherSurname;
    }

    public void setTeacherSurname(String teacherSurname) {
        this.teacherSurname = teacherSurname;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}



