package com.bridgelabz.addressbook;

import java.io.IOException;

import com.bridgelabz.addressbook.AddressBookImpl.IOService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public interface SystemClassIF {

	public void getCityAndStateCount(String city, String state);
	public void viewPersonCityState(String city, String state);
	public void searchPersonCity(String city, String person);
	public void editAddressBook(String addressBookName);
	public AddressBookImpl createNewAddressBook(String addressBookName);
	public void displayAddressBook();
	public void deleteAddressBook(String addressBookName);
	public void addAddressBook(String addressBookName);
	public int readAddressBookData(IOService ioservice);
	
	public void addContactToAddressBook(String addressBookName, Contact contact);
	public void editContactToAddressBook(String addressBookName, String person, Contact contact);
	public void deleteContactToAddressBook(String addressBookName, String person);
	public void searchPersonAndCityInAddressBook(String addressBookName, String person, String city);
	public void viewPersonByCityAndStateInAddressBook(String addressBookName, String city, String state);
	public void getCityAndStateCountInAddressBook(String addressBookName, String city, String state);
	public void writeAddressBookDataInAddressBook(String addressBookName, IOService ioservice) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException;
	public long readAddressBookDataInAddressBook(String addressBookName, IOService ioservice) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException;
	public boolean updateAddressBookData(String first_name, String phone_number);
}
