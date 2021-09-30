package com.bridgelabz.addressbook;

import java.util.Objects;

import com.opencsv.bean.CsvBindByName;

public class ContactAddress {

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactAddress other = (ContactAddress) obj;
		return Objects.equals(city, other.city) && Objects.equals(state, other.state)
				&& Objects.equals(zipCode, other.zipCode);
	}
	@CsvBindByName(column = "CITY")
	private String city;
	
	@CsvBindByName(column = "STATE")
	private String state;
	
	@CsvBindByName(column = "ZIPCODE")
	private String zipCode;
	
	public ContactAddress(String city, String state, String zipCode) {
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	public ContactAddress() {
		// TODO Auto-generated constructor stub
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	@Override
	public String toString() {
		return "city=" + city + ", state=" + state + ", zipCode=" + zipCode;
	}
	
}
