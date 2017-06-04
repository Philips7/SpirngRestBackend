package com.nmp.ArgumentedReality.controller;

import com.nmp.ArgumentedReality.dao.VideoDao;
import com.nmp.ArgumentedReality.entity.Video;
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
}
