package com.app.restapi.exceptions;

import com.app.restapi.constantes.Const;
import com.app.restapi.constantes.Const.ErrorCode;

public class UserException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5085405222570249285L;

	public UserException(ErrorCode err) {
		super(String.format("Utilisateur %s",err.getMessage()));	
	}
	public UserException(ErrorCode err, ErrorCode err2) {
		super(String.format("Utilisateur %s et %s",err.getMessage(),err2.getMessage()));	
	}
}
