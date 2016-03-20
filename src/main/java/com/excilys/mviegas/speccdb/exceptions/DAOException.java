package com.excilys.mviegas.speccdb.exceptions;

@SuppressWarnings({"serial", "unused"})
public class DAOException extends Exception {

	public DAOException() {
		super();
	}

	public DAOException(String pMessage, Throwable pCause, boolean pEnableSuppression, boolean pWritableStackTrace) {
		super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
	}

	public DAOException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}

	public DAOException(String pMessage) {
		super(pMessage);
	}

	public DAOException(Throwable pCause) {
		super(pCause);
	}
	
}
