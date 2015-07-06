package com.eldotk.exceptions;

/**
 * Created by Elka on 06/07/2015.
 */
public class EntityException extends Exception {

    public EntityException() {
        super();
    }

    public EntityException(String message) {
        super(message);
    }

    public EntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityException(Throwable cause) {
        super(cause);
    }

}

