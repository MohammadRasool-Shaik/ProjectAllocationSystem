package com.happiestminds.projectallocationsystem.exception;

public class DataException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;
	
	public DataException(String message) {
        this(message, null);
    }

    public DataException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
