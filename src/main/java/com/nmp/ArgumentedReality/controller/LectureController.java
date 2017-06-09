package com.nmp.ArgumentedReality.controller;

import com.nmp.ArgumentedReality.dao.LectureDao;
import com.nmp.ArgumentedReality.entity.Lecture;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Dominik on 2017-06-03.
 */
@Controller
public class LectureController {

    @Autowired
    LectureDao lectureDao;

    @RequestMapping(value = "/lectures/{id}", method = RequestMethod.GET)
    ResponseEntity<?> getLecture(HttpServletRequest request,
                                HttpServletResponse response, @PathVariable("id") Integer id) throws Exception {
        Lecture lecture = lectureDao.getLectureById(id);
        if (lecture != null){
            return new ResponseEntity<Lecture>(lecture,HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/lectures", method = RequestMethod.GET)
    ResponseEntity<?> getLectures(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArrayList<Lecture> lectures = lectureDao.getLectures();
        if (lectures != null) {
            return new ResponseEntity<ArrayList<Lecture>>(lectures, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/lectures/{lectureId}/image/{id}", method = RequestMethod.GET)
    ResponseEntity<?> getLectureImage(HttpServletRequest request,
                                HttpServletResponse response, @PathVariable("id") int id) throws Exception {

        ClassPathResource classPathResource = new ClassPathResource("lectures/lecture" + id + ".jpg");


        InputStream inputStream = classPathResource.getInputStream();
        File file = File.createTempFile( "lecture"+id, ".jpg");
        try {
            FileUtils.copyInputStreamToFile(inputStream, file);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }

        if (file.exists() == false) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            long length = file.length();

            if (length <= Integer.MAX_VALUE) {
                response.setContentLength((int) length);
            } else {
                response.addHeader("Content-Length", Long.toString(length));
            }
            InputStream is = new FileInputStream(file);
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
            return new ResponseEntity(HttpStatus.OK);
        }
    }
}
