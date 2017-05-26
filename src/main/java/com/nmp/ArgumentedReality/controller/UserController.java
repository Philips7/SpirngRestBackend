package com.nmp.ArgumentedReality.controller;


import com.nmp.ArgumentedReality.entity.User;
import com.nmp.ArgumentedReality.security.JwtAuthenticationRequest;
import com.nmp.ArgumentedReality.security.JwtAuthenticationResponse;
import com.nmp.ArgumentedReality.security.JwtTokenUtil;
import com.nmp.ArgumentedReality.service.UserService;
import com.nmp.ArgumentedReality.wrapper.Errors;
import com.nmp.ArgumentedReality.wrapper.UsersPost;
import com.nmp.ArgumentedReality.wrapper.UsersPut;
import com.sun.org.apache.xalan.internal.xsltc.runtime.InternalRuntimeError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Api(value="Argumented Reality", description="Operations pertaining to users in AR application")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    private String tokenHeader = "Authorization";

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException{

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getLogin(),
                        authenticationRequest.getPassword()
                )
        );

        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getLogin());
        String token = jwtTokenUtil.generateToken(userDetails);
        token = "Bearer " + token;
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }



    @ApiOperation(value = "Create new User",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User successfully created"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )


    @RequestMapping(value = "/users", method = RequestMethod.POST, headers = "Accept=application/json" )
    public ResponseEntity<?> createUser(@Valid @RequestBody UsersPost newUser, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println(result.toString());
            List<FieldError> errorsS = result.getFieldErrors();
            JSONObject response = new JSONObject();

            Errors myErrors = new Errors(errorsS);
            myErrors.setAllErrors();

            return new ResponseEntity<List>(myErrors.getErrors(), HttpStatus.BAD_REQUEST);
        }

        try {
            User user = new User(newUser.getUsername(), newUser.getPassword(), newUser.getEmail(), newUser.getName());
            userService.createUser(user);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            JSONObject response = new JSONObject();
            response.put("code", HttpStatus.BAD_REQUEST);
            response.put("message", ex.getMessage());
            return new ResponseEntity<JSONObject>(response, HttpStatus.BAD_REQUEST);
        } catch (InternalRuntimeError ex) {
            System.out.println(ex.getMessage());
            JSONObject response = new JSONObject();
            response.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("message", ex.getMessage());
            return new ResponseEntity<JSONObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ConstraintViolationException ex) {
            System.out.println(ex.getMessage());
            JSONObject response = new JSONObject();
            response.put("code", HttpStatus.BAD_REQUEST);
            response.put("message", ex.getMessage());
            return new ResponseEntity<JSONObject>(response, HttpStatus.BAD_REQUEST);
        }
    }



    @ApiOperation(value = "Get a list of users",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of users"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> getUsers() {

        try {
            ArrayList<User> users = userService.getUsers();
            return new ResponseEntity<Object>(users, HttpStatus.OK);
        } catch (InternalRuntimeError ex) {
            JSONObject response = new JSONObject();
            response.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("message", ex.getMessage());
            return new ResponseEntity<JSONObject>(response, HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Get user by id",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUsersById(@PathVariable("id") int id) {
        try {
            User user = userService.getUserById(id);
            if (user != null) {
                return new ResponseEntity<User>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (InternalRuntimeError ex) {
            JSONObject response = new JSONObject();
            response.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("message", ex.getMessage());
            return new ResponseEntity<JSONObject>(response, HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Get user by username",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/users/username/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getUsersByUsername(@PathVariable("username") String username) {
        try {
            User user = userService.getUserByUsername(username);
            if (user != null) {
                return new ResponseEntity<User>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (InternalRuntimeError ex) {
            JSONObject response = new JSONObject();
            response.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("message", ex.getMessage());
            return new ResponseEntity<JSONObject>(response, HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Update user",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User successfully created"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/users", method = RequestMethod.PUT, headers = "Accept=application/json" )
    public ResponseEntity<?> updateUser(@Valid @RequestBody UsersPut updatedUser, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println(result.toString());
            List<FieldError> errorsS = result.getFieldErrors();
            JSONObject response = new JSONObject();

            Errors myErrors = new Errors(errorsS);
            myErrors.setAllErrors();

            return new ResponseEntity<List>(myErrors.getErrors(), HttpStatus.BAD_REQUEST);
        }
        try {
            User user = new User(updatedUser.getId(), updatedUser.getUsername(), updatedUser.getPassword(), updatedUser.getEmail(), updatedUser.getName());
            userService.updateUser(user);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            JSONObject response = new JSONObject();
            response.put("code", HttpStatus.BAD_REQUEST);
            response.put("message", ex.getMessage());
            return new ResponseEntity<JSONObject>(response, HttpStatus.BAD_REQUEST);
        } catch (InternalRuntimeError ex) {
            System.out.println(ex.getMessage());
            JSONObject response = new JSONObject();
            response.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("message", ex.getMessage());
            return new ResponseEntity<JSONObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Delete user",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    }
    )
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
//    @PreAuthorize("(#id == authentication.principal.name) or (hasRole('ADMIN'))")
   // @PreAuthorize("@mySecurityService.hasPermission(authentication, #id)")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException ex) {
            JSONObject response = new JSONObject();
            response.put("code", HttpStatus.NOT_FOUND);
            response.put("message", ex.getMessage());
            return new ResponseEntity<JSONObject>(response, HttpStatus.NOT_FOUND);
        } catch (InternalRuntimeError ex) {
            JSONObject response = new JSONObject();
            response.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }



    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<JSONObject> HttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        JSONObject response = new JSONObject();
        String error = ex.getMessage().substring(0, 32);
        response.put("error", error);
        return new ResponseEntity<JSONObject>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<JSONObject> handleException(Exception ex) {
        JSONObject response = new JSONObject();
        response.put("error", ex.getMessage());
        return new ResponseEntity<JSONObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}