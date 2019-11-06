package com.codeoftheweb.salvo.exception;


/*
* Custom exception que va a ser padre de todas las exceptions que tiremos en el negocio. Van a ser runtime, debido a
* que queremos que corte el proceso en el momento que falle nuestro programa
* */
public abstract class CustomException extends RuntimeException {

	/*Defino cuatro atributos basicos para que pueda leerlo un usuario*/
	private String code;
	private String messageCode;
	private String[] messageAtribs;
	private Integer httpStatusCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String[] getMessageAtribs() {
		return messageAtribs;
	}

	public void setMessageAtribs(String[] messageAtribs) {
		this.messageAtribs = messageAtribs;
	}

	public Integer getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(Integer httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
}
