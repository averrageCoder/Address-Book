package com.bridgelabz.addressbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	public void givenAddressBookInDB_whenRetreivedShouldMatchEmployeeCount() {
		try {
			SystemClassIF systemClass = new SystemClassImpl();
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
			SystemClassIF systemClass = new SystemClassImpl();
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
			SystemClassIF systemClass = new SystemClassImpl();
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
			SystemClassIF systemClass = new SystemClassImpl();
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
			SystemClassIF systemClass = new SystemClassImpl();
			systemClass.insertToAddressBookData("Mark","Wade","7894561230","m@m.com","City2","State2",560020);
			boolean result = systemClass.checkInSyncWithDB("Mark");
			assertTrue(result);
		} catch (AddressBookExceptions e) {
			e.printStackTrace();
			assertEquals(e.type, ExceptionType.SQL_ERROR);
		}
	}
}
