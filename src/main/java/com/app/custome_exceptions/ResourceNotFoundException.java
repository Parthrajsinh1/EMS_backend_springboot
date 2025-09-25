package com.app.custome_exceptions;

public class ResourceNotFoundException extends RuntimeException {
public ResourceNotFoundException(String mesg) {
	super(mesg);
}
}
