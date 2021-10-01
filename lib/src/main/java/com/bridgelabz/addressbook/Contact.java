package com.bridgelabz.addressbook;

import java.util.Objects;

import com.opencsv.bean.CsvBindByName;

public class Contact {
	@CsvBindByName(column = "FIRSTNAME")
	private String firstName;
	
	@CsvBindByName(column = "LASTNAME")
	private String lastName;
	
	private ContactAddress address;
	
	@CsvBindByName(column = "PHONENUMBER")
	private String phoneNumber;
	
	@CsvBindByName(column = "EMAIL")
	private String email;
	
	public Contact() {
		this.address = new ContactAddress();
	}
	
	public Contact(String firstName, String lastName, String city, String state, String zipCode,
			String phoneNumber, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = new ContactAddress(city, state, zipCode);
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return this.lastName;
	}

	public String getName() {
		return this.firstName+" "+this.lastName;
	}
	
	public void setAddress(ContactAddress address) {
		this.address = address;
	}
	
	public ContactAddress getAddress() {
		return this.address;
	}
	
	public void setCity(String city) {
		this.address.setCity(city);
	}
	
	public String getCity() {
		return this.address.getCity();
	}
	
	public void setState(String state) {
		this.address.setState(state);
	}
	
	public String getState() {
		return this.address.getState();
	}
	
	public void setZipCode(String zipCode) {
		this.address.setZipCode(zipCode);
	}
	
	public String getZipCode() {
		return this.address.getZipCode();
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
		return "firstName=" + firstName + ", lastName=" + lastName + ", city=" + this.getCity()
				+ ", state=" + this.getState() + ", zipCode=" + this.getZipCode() + ", phoneNumber=" + phoneNumber + ", email=" + email;
	}
	
	
}
