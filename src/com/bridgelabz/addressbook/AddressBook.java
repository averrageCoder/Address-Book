package com.bridgelabz.addressbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
//import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class AddressBook {
	
	private String addressBookName;
	private Contact addressBook[] = new Contact[10];
	private Set<String> nameSet = new LinkedHashSet<>();
	private HashMap<String,ArrayList<String>> cityPersonMapping=new HashMap<>();
	private HashMap<String,ArrayList<String>> statePersonMapping=new HashMap<>();
	int total_contacts=0;
	Scanner scan = new Scanner(System.in);
	
	public void setAddressBookName(String addressBookName) {
		this.addressBookName = addressBookName;
	}
	
	public String getAddressBookName() {
		return this.addressBookName;
	}

	public void manageAddressBook() {
		
		int choice;
		do {
			System.out.println("\n---ADDRESS BOOK - Menu--- \n1. ADD CONTACT \n2. EDIT CONTACT \n3. DELETE CONTACT \n4. DISPLAY CONTACT \n0. EXIT ADDRESS BOOK (GO BACK) \n\nENTER CHOICE:");
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

	private void displayContacts() {
		
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

	private void deleteContact() {
		
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

	private void editContact() {
		
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

	private void addContact() {
			Contact contact = createNewContact();
			//addressBook.add(contact);
			if(contact==null) {
				System.out.println("Name already exists!");
			}
			else {
				this.addressBook[total_contacts] = contact;
				this.total_contacts++;
			}
			
		}

	private Contact createNewContact() {
		Contact contact=new Contact();
		System.out.println("Enter First Name: ");
		String firstName = scan.next();
		System.out.println("Enter Last Name: ");
		String lastName = scan.next();
		if(!nameSet.add(firstName+' '+lastName))
			return null;
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		System.out.println("Enter Address: ");
		contact.setAddress(scan.next());
		System.out.println("Enter City: ");
		contact.setCity(scan.next());
		System.out.println("Enter State: ");
		contact.setState(scan.next());
		System.out.println("Enter zip code: ");
		contact.setZipCode(scan.next());
		System.out.println("Enter Phone number: ");
		contact.setPhoneNumber(scan.next());
		System.out.println("Enter Email: ");
		contact.setEmail(scan.next());
		
		if(this.cityPersonMapping.containsKey(contact.getCity())) {
			ArrayList<String> tempArray = this.cityPersonMapping.get(contact.getCity());
			tempArray.add(firstName);
			this.cityPersonMapping.replace(contact.getCity(), tempArray);
		}
		else {
			ArrayList<String> tempArray = new ArrayList<>();
			tempArray.add(firstName);
			this.cityPersonMapping.put(contact.getCity(), tempArray);
		}
		
		if(this.statePersonMapping.containsKey(contact.getState())) {
			ArrayList<String> tempArray = this.statePersonMapping.get(contact.getState());
			tempArray.add(firstName);
			this.statePersonMapping.replace(contact.getState(), tempArray);
		}
		else {
			ArrayList<String> tempArray = new ArrayList<>();
			tempArray.add(firstName);
			this.statePersonMapping.put(contact.getState(), tempArray);
		}
		
		System.out.println(contact);
		return contact;
	}
	
	public String toString() {
		
		return "\nAddressBook Details: "+"\n"
		+"Name: "+this.getAddressBookName()+"\n"
		+"Total Contacts: "+this.total_contacts+"\n";
		
	}

	public int searchPersonCity(String person, String city) {
		
		Contact tempContact;
		int foundCount=0;
		for (int i = 0; i < total_contacts; i++) {
			tempContact=addressBook[i];
			if(tempContact.getFirstName().equals(person) && tempContact.getCity().equals(city)) {
				//System.out.println(tempContact);
				foundCount++;
			}
		}
		return foundCount;
		
	}

	public void viewPersonByCityAndState() {
		System.out.println("By City: ");
		for (Entry<String, ArrayList<String>> entry : this.cityPersonMapping.entrySet()) {
		    System.out.println("City: "+entry.getKey() + " Person: " + entry.getValue());
		}
		System.out.println("By State: ");
		for (Entry<String, ArrayList<String>> entry : this.statePersonMapping.entrySet()) {
		    System.out.println("City: "+entry.getKey() + " Person: " + entry.getValue());
		}
	}
	
	public void getCityAndStateCount() {
		System.out.println("By City: ");
		for (Entry<String, ArrayList<String>> entry : this.cityPersonMapping.entrySet()) {
		    System.out.println("City: "+entry.getKey() + " Count: " + entry.getValue().size());
		}
		System.out.println("By State: ");
		for (Entry<String, ArrayList<String>> entry : this.statePersonMapping.entrySet()) {
		    System.out.println("City: "+entry.getKey() + " Count: " + entry.getValue().size());
		}
	}

}
