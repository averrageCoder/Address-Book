package com.bridgelabz.addressbook;

import java.io.IOException;

import com.bridgelabz.addressbook.AddressBookImpl.IOService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class MainClass {

	public static void main(String[] args) {
		System.out.println("=== Welcome to address boom program ===");
		
//		addContactToAddressBook();
//		editContactInAddressBook();
//		deleteContactInAddressBook();
//		writeAddressBookToFile();
//		readAddressBookFromFile();
		writeAddressBookToCsv();
//		readAddressBookFromCSV();
//		writeAddressBookToJSON();
//		readAddressBookFromJSON();
//		writeSystemToJson();
//		readSystemFromJSON();
	}

	private static void deleteContactInAddressBook() {
		AddressBookService addressBook = new AddressBookImpl();
		addressBook.setAddressBookName("book1");
		addressBook.setAddressBookType("Family");
		addressBook.addContact(new Contact("jack","jones","city","state","860080","7891234567","j@j.com"));
		addressBook.deleteContact("jack jones");
		System.out.println("Address Book: "+ addressBook);
	}

	private static void addContactToAddressBook() {
		AddressBookService addressBook = new AddressBookImpl();
		addressBook.setAddressBookName("book1");
		addressBook.setAddressBookType("Family");
		addressBook.addContact(new Contact("jack","jones","city","state","860080","7891234567","j@j.com"));
		System.out.println("Address Book: "+ addressBook);
	}
	
	public static void editContactInAddressBook() {
		AddressBookService addressBook = new AddressBookImpl();
		addressBook.setAddressBookName("book1");
		addressBook.setAddressBookType("Family");
		addressBook.addContact(new Contact("jack","jones","city","state","860080","7891234567","j@j.com"));
		System.out.println("Address Book: "+ addressBook);
		addressBook.editContact("jack jones",new Contact("jack","jones","new_city","new_state","860080","7891234567","j@j.com"));
		System.out.println("\nEdited Address Book: "+ addressBook);
	}
	
	public static void writeAddressBookToFile() {
		AddressBookService addressBook = new AddressBookImpl();
		addressBook.setAddressBookName("book1");
		addressBook.setAddressBookType("Family");
		addressBook.addContact(new Contact("jack","jones","city","state","860080","7891234567","j@j.com"));
		addressBook.addContact(new Contact("mad","man","city","state","860080","7891234567","j@j.com"));
		try {
			addressBook.writeAddressBookData(IOService.FILE_IO);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	private static void readAddressBookFromFile() {
		AddressBookService addressBook = new AddressBookImpl("book1","Family");
		addressBook.readAddressBookData(IOService.FILE_IO);
		System.out.println("Address Book: "+ addressBook);
	}
	
	private static void writeAddressBookToCsv() {
		AddressBookService addressBook = new AddressBookImpl();
		addressBook.setAddressBookName("book1");
		addressBook.setAddressBookType("Family");
		addressBook.addContact(new Contact("jack","jones","city","state","860080","7891234567","j@j.com"));
		addressBook.addContact(new Contact("mad","man","city","state","860080","7891234567","j@j.com"));
		try {
			addressBook.writeAddressBookData(IOService.CSV_IO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void readAddressBookFromCSV() {
		AddressBookService addressBook = new AddressBookImpl("book1","Family");
		addressBook.readAddressBookData(IOService.CSV_IO);
		System.out.println("Address Book: "+ addressBook);
	}
	
	private static void writeAddressBookToJSON() {
		AddressBookService addressBook = new AddressBookImpl();
		addressBook.setAddressBookName("book1");
		addressBook.setAddressBookType("Family");
		addressBook.addContact(new Contact("jack","jones","city","state","860080","7891234567","j@j.com"));
		addressBook.addContact(new Contact("mad","man","city","state","860080","7891234567","j@j.com"));
		try {
			addressBook.writeAddressBookData(IOService.JSON_IO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void readAddressBookFromJSON() {
		AddressBookService addressBook = new AddressBookImpl("book1","Family");
		addressBook.readAddressBookData(IOService.JSON_IO);
		System.out.println("Address Book: "+ addressBook);
	}
	private static void writeSystemToJson() {
		AddressBookImpl addressBook = new AddressBookImpl();
		addressBook.setAddressBookName("book1");
		addressBook.setAddressBookType("Family");
		addressBook.addContact(new Contact("jack","jones","city","state","860080","7891234567","j@j.com"));
		addressBook.addContact(new Contact("mad","man","city","state","860080","7891234567","j@j.com"));
		AddressBookImpl addressBook2 = new AddressBookImpl();
		addressBook.setAddressBookName("book2");
		addressBook.setAddressBookType("Friends");
		addressBook.addContact(new Contact("jack","jones","city","state","860080","7891234567","j@j.com"));
		addressBook.addContact(new Contact("mad","man","city","state","860080","7891234567","j@j.com"));
		AddressBookManagerIF systemClass = new AddressBookManagerImpl();
		systemClass.addAddressBook(addressBook);
		systemClass.addAddressBook(addressBook2);
		
		try {
			systemClass.writeSystem(IOService.JSON_IO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void readSystemFromJSON() {
		AddressBookManagerIF systemClass = new AddressBookManagerImpl();
		systemClass.readSystem(IOService.JSON_IO);
	}
}
