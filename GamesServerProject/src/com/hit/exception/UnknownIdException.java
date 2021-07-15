package com.hit.exception;

import java.io.Serializable;

//This exception is thrown when an unknown ID number is encountered
public class UnknownIdException extends Exception implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnknownIdException(String message,Throwable err)
	{
		super(message,err);
	}
	
	public UnknownIdException(Throwable err)
	{
		super(err);
	}
}
