package com.stackroute.distributorservice.exceptions;

@SuppressWarnings("serial")
public class DistributorAlreadyExistsException extends Exception {
	 public DistributorAlreadyExistsException(String message) {
	        super(message);
	    }
}
