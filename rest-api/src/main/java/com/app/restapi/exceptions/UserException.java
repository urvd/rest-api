package com.app.restapi.exceptions;

import com.app.restapi.constantes.Const;

public class UserException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5085405222570249285L;

	public UserException(int id) {
		super(String.format("User '%d' Not found",id));	
	}
	
	public UserException(String msg) {
		super(String.format("User '%s' Not found",msg));	
	}
	
	public UserException() {
		super();
	}
}
