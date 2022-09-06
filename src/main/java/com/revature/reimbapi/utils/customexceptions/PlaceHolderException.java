package com.revature.reimbapi.utils.customexceptions;

public class PlaceHolderException extends RuntimeException{
    public PlaceHolderException() {
        super();
    }

    public PlaceHolderException(String message) {
        super(message);
    }

    public PlaceHolderException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlaceHolderException(Throwable cause) {
        super(cause);
    }

    protected PlaceHolderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
