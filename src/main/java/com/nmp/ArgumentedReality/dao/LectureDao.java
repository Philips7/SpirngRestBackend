package com.nmp.ArgumentedReality.dao;

import com.nmp.ArgumentedReality.entity.Lecture;

import java.util.ArrayList;

/**
 * Created by Dominik on 2017-06-03.
 */
public interface LectureDao {
    void createLecture(Lecture lecture);

    void updateLecture(Lecture lecture);

    void deleteLecture(int id);

    Lecture getLectureById(int id);

    Lecture getLectureByName(String name);

    ArrayList<Lecture> getLectures();
}
