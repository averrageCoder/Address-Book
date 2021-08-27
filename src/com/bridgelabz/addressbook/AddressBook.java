package com.bridgelabz.addressbook;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {
	
	static Contact addressBook[] = new Contact[10];
	static int total_contacts=0;
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		
		System.out.println("=== Welcome to Address Book Program ===");
		
		int choice;
		do {
			System.out.println("\n---Main Menu--- \n1. ADD \n2. EDIT \n3. DELETE \n4. DISPLAY\n0. EXIT \n\nENTER CHOICE:");
			choice = scan.nextInt();

			switch(choice) {

			case 1: addContact();
			break;

			case 2: editContact();
			break;
			
			case 3: deleteContact();
			break;
			
			case 4: displayContacts();
			break;
			
			case 0: System.exit(0);

			default: System.out.println("Invalid choice!");
		
			}
		
		}while(choice!=0);
	}

	private static void displayContacts() {
		
		if(total_contacts < 1) {
			System.out.println("No contacts in addressbook!");
			return;
		}
		
		Contact tempContact;
		for (int i = 0; i < total_contacts; i++) {
			tempContact=addressBook[i];
			System.out.println("\n"+tempContact);
		}
		System.out.flush();
	}

	private static void deleteContact() {
		
		if(total_contacts < 1) {
			System.out.println("No contacts in addressbook!");
			return;
		}
		
		String name;
		int flag=-1;
		System.out.println("Enter the first name of the person to delete: ");
		name=scan.next();
		Contact tempContact;
		
		for (int i = 0; i < total_contacts; i++) {
			tempContact=addressBook[i];
			if(name.equals(tempContact.getFirstName())) {
				System.out.println("Name found! DELETING...");
				for(int j=i+1; j<total_contacts;j++) {
					addressBook[j-1]=addressBook[j];
				}
				total_contacts--;
				flag=0;
				break;
			}
		}
		
		if(flag!=0)
			System.out.println("Name not found!");
		System.out.flush();
	}

	private static void editContact() {
		
		if(total_contacts < 1) {
			System.out.println("No contacts in addressbook!");
			return;
		}
		
		String name;
		int flag=-1;
		System.out.println("Enter the first name of the person to edit: ");
		name=scan.next();
		Contact tempContact;
		
		for (int i = 0; i < total_contacts; i++) {
			tempContact=addressBook[i];
			if(name.equals(tempContact.getFirstName())) {
				System.out.println("Name found! Enter new details: ");
				Contact contact = createNewContact();
				addressBook[i] = contact;
				System.out.println("Contact added successfully!");
				flag=0;
				break;
			}
		}
		
		if(flag==0)
			System.out.println("Name not found!");
		System.out.flush();
	}

	private static void addContact() {
			Contact contact = createNewContact();
			//addressBook.add(contact);
			addressBook[total_contacts] = contact;
			total_contacts++;
			
		}

	private static Contact createNewContact() {
		Contact contact=new Contact();
		System.out.println("Enter First Name: ");
		contact.setFirstName(scan.next());
		System.out.println("Enter Last Name: ");
		contact.setLastName(scan.next());
		System.out.println("Enter City: ");
		contact.setAddress(scan.next());
		System.out.println("Enter State: ");
		contact.setCity(scan.next());
		System.out.println("Enter Address: ");
		contact.setState(scan.next());
		System.out.println("Enter zip code: ");
		contact.setZipCode(scan.next());
		System.out.println("Enter Phone number: ");
		contact.setPhoneNumber(scan.next());
		System.out.println("Enter Email: ");
		contact.setEmail(scan.next());
	
		System.out.println(contact);
		return contact;
	}

}
