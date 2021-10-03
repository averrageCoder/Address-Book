package com.bridgelabz.addressbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BooleanSupplier;

import com.bridgelabz.addressbook.AddressBookImpl.IOService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddressBookManagerImpl implements AddressBookManagerIF {
	
	static List<AddressBookImpl> addressBooks = new ArrayList<AddressBookImpl>();
	static int total_addressBooks=0;
	
	public List<AddressBookImpl> getAllAddressBooks() {
		return this.addressBooks;
	}
	
	public void getCityAndStateCount(String city, String state) {	
		if(total_addressBooks < 1) {
			System.out.println("No contacts in addressbook!");
			return;
		}
		AddressBookService tempAddressBook;
		for (int i = 0; i < addressBooks.size(); i++) {
			tempAddressBook=addressBooks.get(i);
			if(tempAddressBook.total_contacts > 0) {
				System.out.println("\nAddressbook- "+tempAddressBook.getAddressBookName()+": ");
				tempAddressBook.getCityAndStateCount(city, state);
			}
			else {
				System.out.println("\nAddressbook- "+tempAddressBook.getAddressBookName()+": No contacts");
			}
		}
	}

	public void viewPersonCityState(String city, String state) {
		if(total_addressBooks < 1) {
			System.out.println("No contacts in addressbook!");
			return;
		}
		AddressBookService tempAddressBook;
		for (int i = 0; i < addressBooks.size(); i++) {
			tempAddressBook=addressBooks.get(i);
			if(tempAddressBook.total_contacts > 0) {
				System.out.println("\nAddressbook- "+tempAddressBook.getAddressBookName()+": ");
				tempAddressBook.viewPersonByCityAndState(city, state);
			}
			else {
				System.out.println("\nAddressbook- "+tempAddressBook.getAddressBookName()+": No contacts");
			}
		}
	}

	public void searchPersonCity(String city, String person) {
		if(total_addressBooks < 1) {
			System.out.println("No contacts in addressbook!");
			return;
		}
		AddressBookService tempAddressBook;
		System.out.println("ADDRESSBOOK\tFOUND_COUNT");
		for (int i = 0; i < addressBooks.size(); i++) {
			tempAddressBook=addressBooks.get(i);
			System.out.println(tempAddressBook.getAddressBookName()+":\t"+tempAddressBook.searchPersonCity(person, city));
		}
	}

	public void editAddressBook(String addressBookName, String AddressBookType) {
		if(total_addressBooks < 1) {
			System.out.println("No contacts in addressbook!");
			return;
		}
		int flag=-1;
		AddressBookService tempAddressBook;
		for (int i = 0; i < addressBooks.size(); i++) {
			tempAddressBook=addressBooks.get(i);
			if(addressBookName.equals(tempAddressBook.getAddressBookName())) {
				System.out.println("AddressBook found! Enter new details: ");
				AddressBookImpl addressBook = createNewAddressBook(addressBookName, AddressBookType);
				addressBooks.set(i,addressBook);
				System.out.println("AddressBook edited successfully!");
				flag=0;
				break;
			}
		}
		if(flag!=0)
			System.out.println("AddressBook not found!");
		System.out.flush();
	}

	public AddressBookImpl createNewAddressBook(String addressBookName, String addressBookType) {
		AddressBookImpl addressBook =new AddressBookImpl(addressBookName, addressBookType);
		System.out.println(addressBook);
		return addressBook;
	}

	public void displayAddressBook() {
		if(total_addressBooks < 1) {
			System.out.println("No addressbook in system!");
			return;
		}		
		AddressBookService tempAddressBook;
		for (int i = 0; i < addressBooks.size(); i++) {
			tempAddressBook=addressBooks.get(i);
			System.out.println("\n"+tempAddressBook);
		}
		System.out.flush();
	}

	public void deleteAddressBook(String addressBookName) {
		if(total_addressBooks < 1) {
			System.out.println("No addressbook in system!");
			return;
		}
		int flag=-1;
		AddressBookService tempAddressBook;
		for (int i = 0; i < addressBooks.size(); i++) {
			tempAddressBook=addressBooks.get(i);
			if(addressBookName.equals(tempAddressBook.getAddressBookName())) {
				System.out.println("Name found! DELETING...");
				addressBooks.remove(i);
				total_addressBooks--;
				flag=0;
				break;
			}
		}
		if(flag!=0)
			System.out.println("AddressBook not found!");
		System.out.flush();
	}
	
	public void addAddressBook(AddressBookImpl addressBook) {
		addressBooks.add(addressBook);
		total_addressBooks++;
	}
	
	public int readAddressBookData(IOService ioservice){
		if(ioservice.equals(IOService.DB_IO)) {
			this.addressBooks =new AddressBookManagerDBService().readAddressBookData();
			this.total_addressBooks = this.addressBooks.size();
			System.out.println("PARSED DATA FROM DB: ");
			this.addressBooks.forEach(addressBook -> System.out.println(addressBook));
		}
		return this.total_addressBooks;
	}
	
	public void addContactToAddressBook(String addressBookName, Contact contact){
		if(total_addressBooks < 1) {
			System.out.println("No addressbook in system!");
			return;
		}
		int flag=-1;
		AddressBookImpl tempAddressBook;
		for (int i = 0; i < addressBooks.size(); i++) {
			tempAddressBook=addressBooks.get(i);
			if(addressBookName.equals(tempAddressBook.getAddressBookName())) {
				System.out.println("Name found!");
				tempAddressBook.addContact(contact);
				flag=0;
				break;
			}
			addressBooks.set(i, tempAddressBook);
		}
		if(flag!=0)
			System.out.println("AddressBook not found!");
		System.out.flush();
	}
	
	public void editContactToAddressBook(String addressBookName, String person, Contact contact){
		if(total_addressBooks < 1) {
			System.out.println("No addressbook in system!");
			return;
		}
		int flag=-1;
		AddressBookImpl tempAddressBook;
		for (int i = 0; i < addressBooks.size(); i++) {
			tempAddressBook=addressBooks.get(i);
			if(addressBookName.equals(tempAddressBook.getAddressBookName())) {
				System.out.println("Name found!");
				tempAddressBook.editContact(person, contact);
				flag=0;
				break;
			}
			addressBooks.set(i, tempAddressBook);
		}
		if(flag!=0)
			System.out.println("AddressBook not found!");
		System.out.flush();
	}
	
	public void deleteContactToAddressBook(String addressBookName, String person){
		if(total_addressBooks < 1) {
			System.out.println("No addressbook in system!");
			return;
		}
		int flag=-1;
		AddressBookImpl tempAddressBook;
		for (int i = 0; i < addressBooks.size(); i++) {
			tempAddressBook=addressBooks.get(i);
			if(addressBookName.equals(tempAddressBook.getAddressBookName())) {
				System.out.println("Name found!");
				tempAddressBook.deleteContact(person);
				flag=0;
				break;
			}
			addressBooks.set(i, tempAddressBook);
		}
		if(flag!=0)
			System.out.println("AddressBook not found!");
		System.out.flush();
	}
	
	public void searchPersonAndCityInAddressBook(String addressBookName, String person, String city){
		if(total_addressBooks < 1) {
			System.out.println("No addressbook in system!");
			return;
		}
		int flag=-1;
		AddressBookImpl tempAddressBook;
		for (int i = 0; i < addressBooks.size(); i++) {
			tempAddressBook=addressBooks.get(i);
			if(addressBookName.equals(tempAddressBook.getAddressBookName())) {
				System.out.println("Name found!");
				tempAddressBook.searchPersonCity(person, city);
				flag=0;
				break;
			}
			addressBooks.set(i, tempAddressBook);
		}
		if(flag!=0)
			System.out.println("AddressBook not found!");
		System.out.flush();
	}
	
	public void viewPersonByCityAndStateInAddressBook(String addressBookName, String city, String state){
		if(total_addressBooks < 1) {
			System.out.println("No addressbook in system!");
			return;
		}
		int flag=-1;
		AddressBookImpl tempAddressBook;
		for (int i = 0; i < addressBooks.size(); i++) {
			tempAddressBook=addressBooks.get(i);
			if(addressBookName.equals(tempAddressBook.getAddressBookName())) {
				System.out.println("Name found!");
				tempAddressBook.viewPersonByCityAndState(city, state);
				flag=0;
				break;
			}
			addressBooks.set(i, tempAddressBook);
		}
		if(flag!=0)
			System.out.println("AddressBook not found!");
		System.out.flush();
	}
	
	public void getCityAndStateCountInAddressBook(String addressBookName, String city, String state){
		if(total_addressBooks < 1) {
			System.out.println("No addressbook in system!");
			return;
		}
		int flag=-1;
		AddressBookImpl tempAddressBook;
		for (int i = 0; i < addressBooks.size(); i++) {
			tempAddressBook=addressBooks.get(i);
			if(addressBookName.equals(tempAddressBook.getAddressBookName())) {
				System.out.println("Name found!");
				tempAddressBook.getCityAndStateCount(city, state);
				flag=0;
				break;
			}
			addressBooks.set(i, tempAddressBook);
		}
		if(flag!=0)
			System.out.println("AddressBook not found!");
		System.out.flush();
	}
	
	public void writeAddressBookDataInAddressBook(String addressBookName, IOService ioservice) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException{
		if(total_addressBooks < 1) {
			System.out.println("No addressbook in system!");
			return;
		}
		int flag=-1;
		AddressBookImpl tempAddressBook;
		for (int i = 0; i < addressBooks.size(); i++) {
			tempAddressBook=addressBooks.get(i);
			if(addressBookName.equals(tempAddressBook.getAddressBookName())) {
				System.out.println("Name found!");
				tempAddressBook.writeAddressBookData(ioservice);
				flag=0;
				break;
			}
			addressBooks.set(i, tempAddressBook);
		}
		if(flag!=0)
			System.out.println("AddressBook not found!");
		System.out.flush();
	}
	
	public long readAddressBookDataInAddressBook(String addressBookName, IOService ioservice) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException{
		if(total_addressBooks < 1) {
			System.out.println("No addressbook in system!");
			return 0;
		}
		int flag=-1;
		long returnedSize=0;
		AddressBookImpl tempAddressBook;
		for (int i = 0; i < addressBooks.size(); i++) {
			tempAddressBook=addressBooks.get(i);
			if(addressBookName.equals(tempAddressBook.getAddressBookName())) {
				System.out.println("Name found!");
				returnedSize = tempAddressBook.readAddressBookData(ioservice);
				flag=0;
				break;
			}
			addressBooks.set(i, tempAddressBook);
		}
		if(flag!=0)
			System.out.println("AddressBook not found!");
		return returnedSize;
	}

	@Override
	public boolean updateAddressBookData(String first_name, String phone_number) {
		int result = (new AddressBookManagerDBService()).updateEmployeeUsingPreparedStatement(first_name,phone_number);
		if (result==0) return false;
		return true;
	}

	@Override
	public int readAddressBookDataBasedOnDate(String date) {
		this.addressBooks =new AddressBookManagerDBService().readAddressBookDataBAsedOnDate(date);
		this.total_addressBooks = this.addressBooks.size();
		System.out.println("PARSED DATA FROM DB: ");
		this.addressBooks.forEach(addressBook -> System.out.println(addressBook));
		return this.total_addressBooks;
	}

	@Override
	public int readAddressBookDataBasedOnCityOrState(String city, String state) {
		List<Contact> contactList = new ArrayList<>();
		contactList = new AddressBookManagerDBService().readAddressBookDataBAsedOnCityOrState(city, state);
		return contactList.size();
	}

	@Override
	public boolean insertToAddressBookData(Contact contact) {
		int result = (new AddressBookManagerDBService()).insertContact(contact);
		if (result==0) return false;
		return true;
	}

	@Override
	public boolean checkInSyncWithDB(String first_name) {
		List<Contact> contactList = new ArrayList<>();
		contactList = new AddressBookManagerDBService().readAllContactsFromDB();
		Contact expectedContact = new AddressBookManagerDBService().readContactBasedOnName(first_name);
		return expectedContact.equals(getContactBasedOnName(contactList, first_name));
	}

	private Contact getContactBasedOnName(List<Contact> contactList, String first_name) {
		return contactList.stream()
				.filter(employeePayrollDataItem -> employeePayrollDataItem.getFirstName().equals(first_name))
				.findFirst()
				.orElse(null);
	}

	@Override
	public void writeSystem(IOService ioservice) {
		if(ioservice.equals(IOService.JSON_IO)) {
			String filename = "system.json";
			new AddressBookManagerJSONService().writeData(this.addressBooks, filename);
		}
	}

	@Override
	public void readSystem(IOService ioservice) {
		if(ioservice.equals(IOService.JSON_IO)) {
			String filename = "system.json";
			this.addressBooks = new AddressBookManagerJSONService().readAddressBookData(filename);
			for(AddressBookImpl addressBook: this.addressBooks) {
				System.out.println(addressBook);
			}
		}
	}
}
