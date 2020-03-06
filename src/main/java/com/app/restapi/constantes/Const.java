package com.app.restapi.constantes;

public class Const {

	public final static int LENGHT_ID = 7;
	
	public enum ErrorCode{
		NOT_FOUND("non trouvé"),
		EXIST("déja existant"),
		EXIST_NOM_PRENOM("existe déja pour ce nom et prénom"),
		EXIST_MAIL("existe déja pour ce mail"),
		EXIST_TEL("existe déja pour ce numero de téléphone");
		
		private String msg;		
		ErrorCode(String msg) {
			this.msg = msg;
		}
		public String getMessage(){
			return msg;
		}
	}

}
