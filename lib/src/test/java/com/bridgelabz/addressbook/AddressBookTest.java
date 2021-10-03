package com.bridgelabz.addressbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.bridgelabz.addressbook.AddressBookExceptions.ExceptionType;
import com.bridgelabz.addressbook.AddressBookImpl.IOService;

public class AddressBookTest {
	
	@Test
	public void givenContact_ShouldBeAddedToAddressBook() {
		AddressBookService addressBook = new AddressBookImpl();
		addressBook.setAddressBookName("book1");
		addressBook.setAddressBookType("Family");
		addressBook.addContact(new Contact("jack","jones","city","state","860080","7891234567","j@j.com"));
		assertEquals(1, addressBook.getTotalContacts());
	}
	
	@Test
	public void givenContact_WhenEditedShouldMatchAddressBook() {
		AddressBookService addressBook = new AddressBookImpl();
		addressBook.setAddressBookName("book1");
		addressBook.setAddressBookType("Family");
		addressBook.addContact(new Contact("jack","jones","city","state","860080","7891234567","j@j.com"));
		addressBook.editContact("jack jones",new Contact("jack","jones","new_city","new_state","860080","7891234567","j@j.com"));
		List<Contact> contacts= addressBook.getContacts();
		assertEquals(contacts.get(0), new Contact("jack","jones","new_city","new_state","860080","7891234567","j@j.com"));
	}
	
	@Test
	public void givenContact_WhenDeletedShouldBeRemovedFromAddressBook() {
		AddressBookService addressBook = new AddressBookImpl();
		addressBook.setAddressBookName("book1");
		addressBook.setAddressBookType("Family");
		addressBook.addContact(new Contact("jack","jones","city","state","860080","7891234567","j@j.com"));
		assertEquals(1, addressBook.getTotalContacts());
		addressBook.deleteContact("jack jones");
		assertEquals(0, addressBook.getTotalContacts());
	}
	
	@Test
	public void givenContact_ShouldwriteAddressBookToFile() {
		AddressBookService addressBook = new AddressBookImpl();
		addressBook.setAddressBookName("book1");
		addressBook.setAddressBookType("Family");
		addressBook.addContact(new Contact("jack","jones","city","state","860080","7891234567","j@j.com"));
		addressBook.addContact(new Contact("mad","man","city","state","860080","7891234567","j@j.com"));
		try {
			addressBook.writeAddressBookData(IOService.FILE_IO);
			File f = new File("book1.txt");
			assertTrue(f.exists() && !f.isDirectory());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	@Test
	public void givenContact_ShouldreadAddressBookFromFile() {
		AddressBookService addressBook = new AddressBookImpl("book1","Family");
		addressBook.readAddressBookData(IOService.FILE_IO);
		assertEquals(addressBook.getTotalContacts(), 2);
	}
	
	@Test
	public void givenContact_ShouldWriteAddressBookToCsv() {
		AddressBookService addressBook = new AddressBookImpl();
		addressBook.setAddressBookName("book1");
		addressBook.setAddressBookType("Family");
		addressBook.addContact(new Contact("jack","jones","city","state","860080","7891234567","j@j.com"));
		addressBook.addContact(new Contact("mad","man","city","state","860080","7891234567","j@j.com"));
		try {
			addressBook.writeAddressBookData(IOService.CSV_IO);
			File f = new File("book1.csv");
			assertTrue(f.exists() && !f.isDirectory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenContact_ShouldReadAddressBookFromCSV() {
		AddressBookService addressBook = new AddressBookImpl("book1","Family");
		addressBook.readAddressBookData(IOService.CSV_IO);
		assertEquals(addressBook.getTotalContacts(), 2);
	}
	
	@Test
	public void givenContact_ShouldWriteAddressBookToJSON() {
		AddressBookService addressBook = new AddressBookImpl();
		addressBook.setAddressBookName("book1");
		addressBook.setAddressBookType("Family");
		addressBook.addContact(new Contact("jack","jones","city","state","860080","7891234567","j@j.com"));
		addressBook.addContact(new Contact("mad","man","city","state","860080","7891234567","j@j.com"));
		try {
			addressBook.writeAddressBookData(IOService.JSON_IO);
			File f = new File("book1.json");
			assertTrue(f.exists() && !f.isDirectory());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void givenContact_ShouldReadAddressBookFromJSON() {
		AddressBookService addressBook = new AddressBookImpl("book1","Family");
		addressBook.readAddressBookData(IOService.JSON_IO);
		assertEquals(addressBook.getTotalContacts(), 2);
	}
	
	@Test
	public void givenContact_ShouldWriteSystemToJson() {
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
			addressBook.writeAddressBookData(IOService.JSON_IO);
			File f = new File("system.json");
			assertTrue(f.exists() && !f.isDirectory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenContact_ShouldReadSystemFromJSON() {
		AddressBookManagerIF systemClass = new AddressBookManagerImpl();
		systemClass.readSystem(IOService.JSON_IO);
		List<AddressBookImpl> addressBooks = systemClass.getAllAddressBooks();
		assertEquals(2, addressBooks.size());
		
	}
	
	@Test
	public void givenAddressBookInDB_whenRetreivedShouldMatchEmployeeCount() {
		try {
			AddressBookManagerIF systemClass = new AddressBookManagerImpl();
			int size = systemClass.readAddressBookData(IOService.DB_IO);
			assertEquals(2, size);
		} catch (AddressBookExceptions e) {
			e.printStackTrace();
			assertEquals(e.type, ExceptionType.SQL_ERROR);
		}
	}
	
	@Test
	public void givenAddressBookInDB_whenUpdatedShouldMatchDB() {
		try {
			AddressBookManagerIF systemClass = new AddressBookManagerImpl();
			systemClass.updateAddressBookData("James","7894561230");
			boolean result = systemClass.checkInSyncWithDB("James");
			assertTrue(result);
		} catch (AddressBookExceptions e) {
			e.printStackTrace();
			assertEquals(e.type, ExceptionType.SQL_ERROR);
		}
	}
	
	@Test
	public void givenAddressBookInDB_whenRetreivedShouldMatchDB() {
		try {
			AddressBookManagerIF systemClass = new AddressBookManagerImpl();
			int size = systemClass.readAddressBookDataBasedOnDate("2019-01-01");
			assertEquals(2, size);
		} catch (AddressBookExceptions e) {
			e.printStackTrace();
			assertEquals(e.type, ExceptionType.SQL_ERROR);
		}
	}
	
	@Test
	public void givenAddressBookInDB_whenRetreivedNumberOfContactsShouldMatchDB() {
		try {
			AddressBookManagerIF systemClass = new AddressBookManagerImpl();
			int size = systemClass.readAddressBookDataBasedOnCityOrState("London","Delhi");
			assertEquals(2, size);
		} catch (AddressBookExceptions e) {
			e.printStackTrace();
			assertEquals(e.type, ExceptionType.SQL_ERROR);
		}
	}
	
	@Test
	public void givenAddressBookInDB_whenAddedShouldMatchDB() {
		try {
			AddressBookManagerIF systemClass = new AddressBookManagerImpl();
			systemClass.insertToAddressBookData(new Contact("Mark","Wade","City2","State2","560020","7894561230","m@m.com"));
			boolean result = systemClass.checkInSyncWithDB("Mark");
			assertTrue(result);
		} catch (AddressBookExceptions e) {
			e.printStackTrace();
			assertEquals(e.type, ExceptionType.SQL_ERROR);
		}
	}
}
