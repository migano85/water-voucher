package com.wv.exceptions;

public class DataQueryException extends Exception {

	public DataQueryException(String messageTemplate, Object... params) {
        super(String.format(messageTemplate, params));
    }
 
    public DataQueryException(String message, Throwable cause) {
        super(message, cause);
    }
}
