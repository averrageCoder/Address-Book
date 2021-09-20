package com.bridgelabz.addressbook;

import java.io.IOException;

import com.bridgelabz.addressbook.AddressBookImpl.IOService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public interface AddressBookService {
	
	public String getAddressBookName();
	public void setAddressBookName(String addressBookName);
	public void deleteContact();
	public void editContact();
	public void addContact();
	public Contact createNewContact();
	public int searchPersonCity(String person, String city);
	public void viewPersonByCityAndState(String city, String state);
	public void getCityAndStateCount(String city, String state);
	public void sortAddressBookByName();
	public void sortAddressBookByCity();
	public void sortAddressBookByState();
	public void sortAddressBookByZip();
	void writeAddressBookData(IOService ioservice) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException;
	public long readAddressBookData(IOService ioservice);

}
