package com.cowin.exception;

@SuppressWarnings("serial")
public class VaccinatedDetailsNotFoundException extends RuntimeException {
	public VaccinatedDetailsNotFoundException(String msg) {

		super(msg);
	}

}
