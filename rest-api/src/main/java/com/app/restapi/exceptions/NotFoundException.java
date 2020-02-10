package com.app.restapi.exceptions;

public class NotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5085405222570249285L;

	public NotFoundException(int id) {
		super(String.format("Not found '%s'", id));
	}
}
