package com.nmp.ArgumentedReality.wrapper;

import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik on 2017-05-23.
 */
public class Errors implements Serializable{

    private static final long serialVersionUID = -5991235839658432642L;

    private List<Error> errors;
    private List<FieldError> fieldErrors;

    public Errors(List<FieldError> fieldErrors) {
        this.errors = errors = new ArrayList<Error>();
        this.fieldErrors = fieldErrors;
    }

    public void setAllErrors(){
        for (FieldError fieldError : fieldErrors){
            Error error = new Error(fieldError.getField() + " " + fieldError.getDefaultMessage());
            errors.add(error);
        }
    }


    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    class Error implements Serializable{
        private static final long serialVersionUID = 3979592531407952111L;
        private String error;

        public Error(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }

}

