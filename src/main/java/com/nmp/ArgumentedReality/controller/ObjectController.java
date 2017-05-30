package com.nmp.ArgumentedReality.controller;

import com.nmp.ArgumentedReality.entity.Object;
import com.nmp.ArgumentedReality.service.ObjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Dominik on 2017-05-18.
 */

@Api(value="Argumented Reality", description="Operations pertaining to object in AR application")
@RestController
public class ObjectController {

    @Autowired
    ObjectService objectService;

    Path path;

    @RequestMapping(value = "/object", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "description")String description,
            HttpServletRequest request) {

        Object object = new Object(description,file);
        objectService.createObject(object);
        Object newObject = objectService.getObjectByDescription(description);

        MultipartFile multipartFile = file;
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory + newObject.getObjectId() + ".txt");

        if (multipartFile != null && !multipartFile.isEmpty()) {
            try {
                multipartFile.transferTo(new File(path.toString()));
                return new ResponseEntity<Object>(HttpStatus.OK);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException("Object file save failed", ex);
            }
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/object", method = RequestMethod.GET)
    ResponseEntity<?> getObject(HttpServletRequest request,
                                HttpServletResponse response, @RequestParam("id") int id) throws Exception{

        String rootDirectory = request.getSession().getServletContext().getRealPath("/" + id + ".txt");
        File file = new File(rootDirectory);
        if(file.exists() == false){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
        FileSystemResource systemResource = new FileSystemResource(file);
        return new ResponseEntity<>(systemResource, HttpStatus.OK);
        }
    }
}