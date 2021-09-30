package com.bridgelabz.addressbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
//import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;


public class AddressBookImpl implements AddressBookService{
	
	public enum IOService { FILE_IO, CSV_IO, JSON_IO, REST_I0, CONSOLE_IO, DB_IO};
	
	private String addressBookName;
	private List<Contact> addressBook = new ArrayList<Contact>(Arrays.asList());
	private Set<String> nameSet = new LinkedHashSet<>();
	private HashMap<String,ArrayList<Contact>> cityPersonMapping=new HashMap<>();
	private HashMap<String,ArrayList<Contact>> statePersonMapping=new HashMap<>();
	int total_contacts=0;
	private String addressBookType;
	
	public AddressBookImpl(String name, String type) {
		this.addressBookName = name;
		this.addressBookType = type;
	}

	public AddressBookImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Contact> getContacts() {
		return this.addressBook;
	}
	
	public void setAddressBookName(String addressBookName) {
		this.addressBookName = addressBookName;
	}
	
	public void setAddressBookType(String addressBookType) {
		this.addressBookType = addressBookType;
	}
	
	public String getAddressBookName() {
		return this.addressBookName;
	}
	
	public String getAddressBookType() {
		return this.addressBookType;
	}
	
	public int getTotalContacts() {
		return this.addressBook.size();
	}

	public void deleteContact(String name) {;
		if(total_contacts < 1) {
			System.out.println("No contacts in addressbook!");
			return;
		}
		int flag=-1;
		Contact tempContact;
		LinkedList<Contact> tempAddressBook = addressBook.stream().collect(Collectors.toCollection(LinkedList::new));
		for (int i = 0; i < addressBook.size(); i++) {
			tempContact=addressBook.get(i);
			if(name.equals(tempContact.getName())) {
				System.out.println("Name found! DELETING...");
				tempAddressBook.remove(i);
				total_contacts--;
				flag=0;
				sortAddressBookByName();
				break;
			}
		}
		this.addressBook = tempAddressBook.stream().collect(Collectors.toCollection(ArrayList::new));
		if(flag!=0)
			System.out.println("Name not found!");
	}

	public void editContact(String name, Contact contact) {
		if(total_contacts < 1) {
			System.out.println("No contacts in addressbook!");
			return;
		}
		int flag=-1;
		Contact tempContact;
		for (int i = 0; i < addressBook.size(); i++) {
			tempContact=addressBook.get(i);
			if(name.equals(tempContact.getName())) {
				flag=0;
				System.out.println("Name found! Enter new details: ");
				nameSet.remove(tempContact.getName());
				contact = checkNewContact(contact);
				if(contact==null) {
					nameSet.add(tempContact.getName());
					System.out.println("Name already exists!");
				}
				else {
					addressBook.set(i, contact);
					sortAddressBookByName();
					System.out.println("Contact added successfully!");
					break;
				}
			}
		}
		if(flag!=0)
			System.out.println("Name not found!");
	}

	public void addContact(Contact contact) {
			contact = checkNewContact(contact);
			if(contact==null) {
				System.out.println("Name already exists!");
			}
			else {
				this.addressBook.add(contact);
				this.total_contacts++;
				sortAddressBookByName();
			}
		}

	public Contact checkNewContact(Contact contact) {
		if(!nameSet.add(contact.getName()))
			return null;
		
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
		if(this.addressBook.size() > 0) {
			StringBuilder contactInfo = new StringBuilder();
			for(Contact contact: addressBook) {
				contactInfo=contactInfo.append(contact.toString()).append("\n");
			}
			
			return "\nAddressBook Details: "+"\n"
			+"Name: "+this.getAddressBookName()+"\n"
			+"Type: "+this.getAddressBookType()+"\n"
			+"Total Contacts: "+this.total_contacts+"\n"
			+"Contacts: "+contactInfo;
		}
		else {
			return "\nAddressBook Details: "+"\n"
					+"Name: "+this.getAddressBookName()+"\n"
					+"Type: "+this.getAddressBookType()+"\n"
					+"Total Contacts: "+this.total_contacts+"\n";
		}
	}

	public int searchPersonCity(String person, String city) {
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
	
	public void sortAddressBookByName() {
		ArrayList<Contact> sortedAddressBook = this.addressBook.stream()
				.sorted(Comparator.comparing(Contact::getName))
				.collect(Collectors.toCollection(ArrayList::new));
		this.addressBook = sortedAddressBook;
	}
	
	public void sortAddressBookByCity() {
		ArrayList<Contact> sortedAddressBook = this.addressBook.stream()
				.sorted(Comparator.comparing(Contact::getCity))
				.collect(Collectors.toCollection(ArrayList::new));
		this.addressBook = sortedAddressBook;
	}
	
	public void sortAddressBookByState() {
		ArrayList<Contact> sortedAddressBook = this.addressBook.stream()
				.sorted(Comparator.comparing(Contact::getState))
				.collect(Collectors.toCollection(ArrayList::new));
		this.addressBook = sortedAddressBook;
	}
	
	public void sortAddressBookByZip() {
		ArrayList<Contact> sortedAddressBook = this.addressBook.stream()
				.sorted(Comparator.comparing(Contact::getZipCode))
				.collect(Collectors.toCollection(ArrayList::new));
		this.addressBook = sortedAddressBook;
	}
	
	public void writeAddressBookData(IOService ioservice) {
		if(ioservice.equals(IOService.FILE_IO)) {
			String filename = this.addressBookName+".txt";
			new AddressBookFileIO().writeData(addressBook, filename);
		}
		else if(ioservice.equals(IOService.CSV_IO)) {
			String filename = this.addressBookName+".csv";
			new AddressBookCSVIO().writeData(addressBook, filename);
		}
		else if(ioservice.equals(IOService.JSON_IO)) {
			String filename = this.addressBookName+".json";
			new AddressBookJSONIO().writeData(addressBook, filename);
		}
		else if(ioservice.equals(IOService.CONSOLE_IO)) {
			if(total_contacts < 1) {
				System.out.println("No contacts in addressbook!");
				return;
			}
			for(Contact tempContact : addressBook) {
				System.out.println("\n"+tempContact);
			}
			System.out.flush();
		}
	}
	
	public long readAddressBookData(IOService ioservice) {
		if(ioservice.equals(IOService.FILE_IO)) {
			String filename = this.addressBookName+".txt";
			this.addressBook=new AddressBookFileIO().readAddressBookData(filename);
			this.total_contacts = this.addressBook.size();
			return this.addressBook.size();
		}
		else if(ioservice.equals(IOService.CSV_IO)) {
			String filename = this.addressBookName+".csv";
			this.addressBook=new AddressBookCSVIO().readAddressBookData(filename);
			this.total_contacts = this.addressBook.size();
			return this.addressBook.size();
		}
		else if(ioservice.equals(IOService.JSON_IO)) {
			String filename = this.addressBookName+".json";
			this.addressBook=new AddressBookJSONIO().readAddressBookData(filename);
			this.total_contacts = this.addressBook.size();
			return this.addressBook.size();
		}
		return 0;
	}
}
