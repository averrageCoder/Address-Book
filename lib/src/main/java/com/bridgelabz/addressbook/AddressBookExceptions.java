package com.bridgelabz.addressbook;

public class AddressBookExceptions extends RuntimeException {
	
	enum ExceptionType {
		SQL_ERROR
	}
	
	ExceptionType type;
	
	public AddressBookExceptions(ExceptionType type, String message) {
		super(message);
		this.type = type;
	}
	
}
