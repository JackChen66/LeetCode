package application.exception;

import javax.xml.crypto.Data;

public class CustomException extends Exception implements Data {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	public CustomException(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
}
