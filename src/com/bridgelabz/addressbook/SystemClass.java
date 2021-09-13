package com.bridgelabz.addressbook;

import java.util.Scanner;

public class SystemClass {
	
	static AddressBook addressBooks[] = new AddressBook[10];
	static int total_addressBooks=0;
	static Scanner scan = new Scanner(System.in);
	
	public static void manageSystem() {
		
		System.out.println("=== Welcome to Address Book Program ===");
		
		int choice;
		do {
			System.out.println("\n---SYSTEM - Menu--- \n1. ADD ADDRESSBOOK \n2. EDIT ADDRESSBOOK \n3. MANAGE ADDRESSBOOK \n4. DELETE ADDRESSBOOK \n5. DISPLAY\n6. SEARCH PERSON & CITY\n7. VIEW PERSON BY CITY & STATE\n8. GET CITY AND STATE COUNT \n0. EXIT \n\nENTER CHOICE:");
			choice = scan.nextInt();

			switch(choice) {

			case 1: addAddressBook();
			break;
			
			case 2: editAddressBook();
			break;

			case 3: manageAddressBook();
			break;
			
			case 4: deleteAddressBook();
			break;
			
			case 5: displayAddressBook();
			break;
			
			case 6: searchPersonCity();
			break;
			
			case 7: viewPersonCityState();
			break;
			
			case 8: getCityAndStateCount();
			break;
			
			case 0: System.exit(0);

			default: System.out.println("Invalid choice!");
		
			}
		
		}while(choice!=0);

	}
	
	private static void getCityAndStateCount() {
		
		if(total_addressBooks < 1) {
			System.out.println("No contacts in addressbook!");
			return;
		}
		
		System.out.println("Enter the name of the city: ");
		String city=scan.next();
		
		System.out.println("Enter the name of the state: ");
		String state=scan.next();
		
		AddressBook tempAddressBook;
		for (int i = 0; i < total_addressBooks; i++) {
			tempAddressBook=addressBooks[i];
			if(tempAddressBook.total_contacts > 0) {
				System.out.println("\nAddressbook- "+tempAddressBook.getAddressBookName()+": ");
				tempAddressBook.getCityAndStateCount(city, state);
			}
			else {
				System.out.println("\nAddressbook- "+tempAddressBook.getAddressBookName()+": No contacts");
			}
		}
		
	}

	private static void viewPersonCityState() {
		
		if(total_addressBooks < 1) {
			System.out.println("No contacts in addressbook!");
			return;
		}
		
		System.out.println("Enter the name of the city: ");
		String city=scan.next();
		
		System.out.println("Enter the name of the state: ");
		String state=scan.next();
		
		AddressBook tempAddressBook;
		for (int i = 0; i < total_addressBooks; i++) {
			tempAddressBook=addressBooks[i];
			if(tempAddressBook.total_contacts > 0) {
				System.out.println("\nAddressbook- "+tempAddressBook.getAddressBookName()+": ");
				tempAddressBook.viewPersonByCityAndState(city, state);
			}
			else {
				System.out.println("\nAddressbook- "+tempAddressBook.getAddressBookName()+": No contacts");
			}
		}
	}

	private static void searchPersonCity() {
		
		if(total_addressBooks < 1) {
			System.out.println("No contacts in addressbook!");
			return;
		}
		
		String person, city;
		System.out.println("Enter the name of the person: ");
		person=scan.next();
		System.out.println("Enter the name of the city: ");
		city=scan.next();
		AddressBook tempAddressBook;
		System.out.println("ADDRESSBOOK\tFOUND_COUNT");
		for (int i = 0; i < total_addressBooks; i++) {
			tempAddressBook=addressBooks[i];
			System.out.println(tempAddressBook.getAddressBookName()+":\t"+tempAddressBook.searchPersonCity(person, city));
		}
		
	}

	private static void editAddressBook() {
		if(total_addressBooks < 1) {
			System.out.println("No contacts in addressbook!");
			return;
		}
		
		String name;
		int flag=-1;
		System.out.println("Enter the name of the addressBook to edit: ");
		name=scan.next();
		AddressBook tempAddressBook;
		
		for (int i = 0; i < total_addressBooks; i++) {
			tempAddressBook=addressBooks[i];
			if(name.equals(tempAddressBook.getAddressBookName())) {
				System.out.println("AddressBook found! Enter new details: ");
				AddressBook addressBook = createNewAddressBook();
				addressBooks[i] = addressBook;
				System.out.println("AddressBook edited successfully!");
				flag=0;
				break;
			}
		}
		
		if(flag!=0)
			System.out.println("AddressBook not found!");
		System.out.flush();
	}

	private static AddressBook createNewAddressBook() {
		AddressBook addressBook =new AddressBook();
		System.out.println("Enter Name of Address Book: ");
		addressBook.setAddressBookName(scan.next());
		System.out.println(addressBook);
		return addressBook;
	}

	private static void displayAddressBook() {
		if(total_addressBooks < 1) {
			System.out.println("No addressbook in system!");
			return;
		}
		
		AddressBook tempAddressBook;
		for (int i = 0; i < total_addressBooks; i++) {
			tempAddressBook=addressBooks[i];
			System.out.println("\n"+tempAddressBook);
		}
		System.out.flush();
		
	}

	private static void deleteAddressBook() {
		if(total_addressBooks < 1) {
			System.out.println("No addressbook in system!");
			return;
		}
		
		String name;
		int flag=-1;
		System.out.println("Enter the name of the addressBook to edit: ");
		name=scan.next();
		AddressBook tempAddressBook;
		
		for (int i = 0; i < total_addressBooks; i++) {
			tempAddressBook=addressBooks[i];
			if(name.equals(tempAddressBook.getAddressBookName())) {
				System.out.println("Name found! DELETING...");
				for(int j=i+1; j<total_addressBooks;j++) {
					addressBooks[j-1]=addressBooks[j];
				}
				total_addressBooks--;
				flag=0;
				break;
			}
		}
		
		if(flag!=0)
			System.out.println("AddressBook not found!");
		System.out.flush();
		
	}

	private static void manageAddressBook() {
		
		if(total_addressBooks < 1) {
			System.out.println("No addressbook in system!");
			return;
		}
		
		String name;
		int flag=-1;
		System.out.println("Enter the name of the addressBook to edit: ");
		name=scan.next();
		AddressBook tempAddressBook;
		
		for (int i = 0; i < total_addressBooks; i++) {
			tempAddressBook=addressBooks[i];
			if(name.equals(tempAddressBook.getAddressBookName())) {
				System.out.println("Name found!");
				tempAddressBook.manageAddressBook();
				flag=0;
				break;
			}
			addressBooks[i]=tempAddressBook;
		}
		
		if(flag!=0)
			System.out.println("AddressBook not found!");
		System.out.flush();
		
	}

	private static void addAddressBook() {
		AddressBook addressBook = createNewAddressBook();
		//addressBook.add(contact);
		addressBooks[total_addressBooks] = addressBook;
		total_addressBooks++;
		
	}

}
