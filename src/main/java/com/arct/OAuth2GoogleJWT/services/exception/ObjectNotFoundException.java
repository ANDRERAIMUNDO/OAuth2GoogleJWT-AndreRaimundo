package com.arct.OAuth2GoogleJWT.services.exception;

public class ObjectNotFoundException  extends RuntimeException {

    public ObjectNotFoundException (String msg) {
        super(msg);
    }

    public ObjectNotFoundException (String msg, Throwable cause) {
            
    }
}