package com.bridgelabz.addressbook;

import com.opencsv.bean.CsvBindByName;

public class Contact {
	@CsvBindByName(column = "FIRSTNAME")
	private String firstName;
	
	@CsvBindByName(column = "LASTNAME")
	private String lastName;
	
	@CsvBindByName(column = "ADDRESS")
	private String address;
	
	@CsvBindByName(column = "CITY")
	private String city;
	
	@CsvBindByName(column = "STATE")
	private String state;
	
	@CsvBindByName(column = "ZIPCODE")
	private String zipCode;
	
	@CsvBindByName(column = "PHONENUMBER")
	private String phoneNumber;
	
	@CsvBindByName(column = "EMAIL")
	private String email;
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getName() {
		return this.firstName+" "+this.lastName;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public void setState(String state) {
		this.state=state;
	}
	
	public String getState() {
		return this.state;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getZipCode() {
		return this.zipCode;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}

	@Override
	public String toString() {
		return "firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", zipCode=" + zipCode + ", phoneNumber=" + phoneNumber + ", email=" + email;
	}
	
	
}
