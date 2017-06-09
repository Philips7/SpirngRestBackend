package com.nmp.ArgumentedReality.controller;

import com.nmp.ArgumentedReality.dao.VideoDao;
import com.nmp.ArgumentedReality.entity.Video;
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
public class VideoController {

    @Autowired
    VideoDao videoDao;

    @RequestMapping(value = "/videos/{id}", method = RequestMethod.GET)
    ResponseEntity<?> getLecture(HttpServletRequest request,
                                 HttpServletResponse response, @PathVariable("id") Integer id) throws Exception {
        Video video = videoDao.getVideoById(id);
        if (video != null){
            return new ResponseEntity<Video>(video,HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/videos", method = RequestMethod.GET)
    ResponseEntity<?> getLectures(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        ArrayList<Video> videos = videoDao.getVideos();
        if (videos != null){
            return new ResponseEntity<ArrayList<Video>>(videos,HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/videos/file/{id}", method = RequestMethod.GET)
    ResponseEntity<?> getVideoFile(HttpServletRequest request,
                                      HttpServletResponse response, @PathVariable("id") int id) throws Exception {

        Video video = videoDao.getVideoById(id);
        Integer lecture_id = video.getLectureId();

        ClassPathResource classPathResource = new ClassPathResource("videos/video/" + lecture_id + "_" + id + ".mp4");

        InputStream inputStream = classPathResource.getInputStream();
        File file = File.createTempFile( "video"+id, ".mp4");
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
