package com.bridgelabz.addressbook;

public class Contact {

	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private String phoneNumber;
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
	
	public String toString() {
		
		return "\nContact Details: "+"\n"
		+"Name: "+this.getName()+"\n"
		+"City: "+this.getCity()+"\n"
		+"State: "+this.getState()+"\n"
		+"Address: "+this.getAddress()+"\n"
		+"ZipCode: "+this.getZipCode()+"\n"
		+"Phone Number: "+this.getPhoneNumber()+"\n"
		+"Email: "+this.getEmail()+"\n";
		
	}
}
