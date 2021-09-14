package com.bridgelabz.addressbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;	
//import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.sun.tools.javac.util.List;

public class AddressBook {
	
	private String addressBookName;
	//private Contact addressBook[] = new Contact[10];
	private ArrayList<Contact> addressBook = new ArrayList<Contact>();
	private Set<String> nameSet = new LinkedHashSet<>();
	private HashMap<String,ArrayList<Contact>> cityPersonMapping=new HashMap<>();
	private HashMap<String,ArrayList<Contact>> statePersonMapping=new HashMap<>();
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

		for(Contact tempContact : addressBook) {
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
		System.out.println("Enter the name of the person to delete: ");
		name=scan.next();
		Contact tempContact;
		
		for (int i = 0; i < addressBook.size(); i++) {
			tempContact=addressBook.get(i);
			if(name.equals(tempContact.getName())) {
				System.out.println("Name found! DELETING...");
				addressBook.remove(i);
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
		System.out.println("Enter the name of the person to edit: ");
		name=scan.next();
		Contact tempContact;
		
		for (int i = 0; i < addressBook.size(); i++) {
			tempContact=addressBook.get(i);
			if(name.equals(tempContact.getName())) {
				System.out.println("Name found! Enter new details: ");
				Contact contact = createNewContact();
				if(contact==null) {
					System.out.println("Name already exists!");
				}
				else {
					addressBook.set(i, contact);
					nameSet.remove(tempContact.getName());
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
				this.addressBook.add(contact);
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
			ArrayList<Contact> tempArray = this.cityPersonMapping.get(contact.getCity());
			tempArray.add(contact);
			this.cityPersonMapping.replace(contact.getCity(), tempArray);
		}
		else {
			ArrayList<Contact> tempArray = new ArrayList<>();
			tempArray.add(contact);
			this.cityPersonMapping.put(contact.getCity(), tempArray);
		}
		
		if(this.statePersonMapping.containsKey(contact.getState())) {
			ArrayList<Contact> tempArray = this.statePersonMapping.get(contact.getState());
			tempArray.add(contact);
			this.statePersonMapping.replace(contact.getState(), tempArray);
		}
		else {
			ArrayList<Contact> tempArray = new ArrayList<>();
			tempArray.add(contact);
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
		ArrayList<Contact> tempArray = new ArrayList<>();
		addressBook.stream().forEach(contact -> {
			if(contact.getName().equals(person) && contact.getCity().equals(city)) {
				tempArray.add(contact);
			}
		});
		return tempArray.size();
	}

	public void viewPersonByCityAndState(String city, String state) {
		System.out.println("By City: ");
		this.cityPersonMapping.entrySet()
                .stream()
                .filter(e -> e.getKey()==city)
                .peek(e -> System.out.println("City: "+e.getKey()+" Person: "+e.getValue()));
		
		
		System.out.println("By State: ");
		this.statePersonMapping.entrySet()
        .stream()
        .filter(e -> e.getKey()==state)
        .peek(e -> System.out.println("State: "+e.getKey()+" Person: "+e.getValue()));
	}
	
	public void getCityAndStateCount(String city, String state) {
		System.out.println("By City: ");
	this.cityPersonMapping.entrySet()
        .stream()
        .filter(e -> e.getKey()==city)
        .peek(e -> System.out.println("City: "+e.getKey()+" Count: "+e.getValue().stream().count()));
		
		System.out.println("By State: ");
		this.statePersonMapping.entrySet()
        .stream()
        .filter(e -> e.getKey()==state)
        .peek(e -> System.out.println("State: "+e.getKey()+" Count: "+e.getValue().stream().count()));
	}

}
