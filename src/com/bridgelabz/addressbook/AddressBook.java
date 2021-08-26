package com.bridgelabz.addressbook;

public class AddressBook {

	public static void main(String[] args) {
		
		System.out.println("=== Welcome to Address Book Program ===");
		
		Contact contact = new Contact();
		
		contact.setFirstName("Adam");
		contact.setLastName("James");
		contact.setAddress("India");
		contact.setCity("Bangalore");
		contact.setState("Karnataka");
		contact.setZipCode("56100");
		contact.setPhoneNumber("9876543210");
		contact.setEmail("email@email.com");
		
		System.out.println("\nContact Details: ");
		System.out.println("Name: "+contact.getName());
		System.out.println("City: "+contact.getCity());
		System.out.println("State: "+contact.getState());
		System.out.println("Address: "+contact.getAddress());
		System.out.println("ZipCode: "+contact.getZipCode());
		System.out.println("Phone Number: "+contact.getPhoneNumber());
		System.out.println("Email: "+contact.getEmail());
	}

}
