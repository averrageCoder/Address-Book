package com.bridgelabz.addressbook;

import java.util.Scanner;

public class AddressBook {

	public static void main(String[] args) {
		
		System.out.println("=== Welcome to Address Book Program ===");
		
		Scanner scan = new Scanner(System.in);
		Contact contact[] = new Contact[10];
		int i=0,choice;
		
		do {
			contact[i]=new Contact();
			System.out.println("Enter First Name: ");
			contact[i].setFirstName(scan.next());
			System.out.println("Enter Last Name: ");
			contact[i].setLastName(scan.next());
			System.out.println("Enter City: ");
			contact[i].setAddress(scan.next());
			System.out.println("Enter State: ");
			contact[i].setCity(scan.next());
			System.out.println("Enter Address: ");
			contact[i].setState(scan.next());
			System.out.println("Enter zip code: ");
			contact[i].setZipCode(scan.next());
			System.out.println("Enter Phone number: ");
			contact[i].setPhoneNumber(scan.next());
			System.out.println("Enter Email: ");
			contact[i].setEmail(scan.next());
		
			System.out.println(contact[i]);
			
			System.out.println("---Menu--- \n1. Add another \n0. Exit");
			choice=scan.nextInt();
			i++;
			
		}while(choice==1);
	}

}
