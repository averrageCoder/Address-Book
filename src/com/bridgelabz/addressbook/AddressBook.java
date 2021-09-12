package com.bridgelabz.addressbook;

import java.util.LinkedHashSet;
//import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class AddressBook {
	
	private String addressBookName;
	static Contact addressBook[] = new Contact[10];
	private static Set<String> nameSet = new LinkedHashSet<>();
	static int total_contacts=0;
	static Scanner scan = new Scanner(System.in);
	
	public void setAddressBookName(String addressBookName) {
		this.addressBookName = addressBookName;
	}
	
	public String getAddressBookName() {
		return this.addressBookName;
	}

	public static void manageAddressBook() {
		
		int choice;
		do {
			System.out.println("\n---ADDRESS BOOK - Menu--- \n1. ADD \n2. EDIT \n3. DELETE \n4. DISPLAY\n0. CHANGE ADDRESS BOOK \n\nENTER CHOICE:");
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
			
			case 0: break;

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
				if(contact==null) {
					System.out.println("Name already exists!");
				}
				else {
					addressBook[i] = contact;
					System.out.println("Contact added successfully!");
					flag=0;
					break;
				}
			}
		}
		
		if(flag!=0)
			System.out.println("Name not found!");
		System.out.flush();
	}

	private static void addContact() {
			Contact contact = createNewContact();
			//addressBook.add(contact);
			if(contact==null) {
				System.out.println("Name already exists!");
			}
			else {
				addressBook[total_contacts] = contact;
				total_contacts++;
			}
			
		}

	private static Contact createNewContact() {
		Contact contact=new Contact();
		System.out.println("Enter First Name: ");
		String firstName = scan.next();
		System.out.println("Enter Last Name: ");
		String lastName = scan.next();
		if(!nameSet.add(firstName+' '+lastName))
			return null;
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
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
	
	public String toString() {
		
		return "\nAddressBook Details: "+"\n"
		+"Name: "+this.getAddressBookName()+"\n"
		+"Total Contacts: "+this.total_contacts+"\n";
		
	}

}
