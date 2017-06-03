package com.nmp.ArgumentedReality.controller;

import com.nmp.ArgumentedReality.dao.LectureDao;
import com.nmp.ArgumentedReality.entity.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Dominik on 2017-06-03.
 */
@Controller
public class LectureController {

    @Autowired
    LectureDao lectureDao;

    @RequestMapping(value = "/lecture/{id}", method = RequestMethod.GET)
    ResponseEntity<?> getLecture(HttpServletRequest request,
                                HttpServletResponse response, @PathVariable("id") Integer id) throws Exception {
        Lecture lecture = lectureDao.getLectureById(id);
        if (lecture != null){
            return new ResponseEntity<Lecture>(lecture,HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }

    }
}
