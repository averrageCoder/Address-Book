package com.bridgelabz.addressbook;

import java.io.IOException;
import java.util.Scanner;

import com.bridgelabz.addressbook.AddressBookImpl.IOService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class SystemClassImpl implements SystemClassIF {
	
	static AddressBookService[] addressBooks = new AddressBookImpl[10];
	static int total_addressBooks=0;
	
	public void getCityAndStateCount(String city, String state) {	
		if(total_addressBooks < 1) {
			System.out.println("No contacts in addressbook!");
			return;
		}
		AddressBookService tempAddressBook;
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

	public void viewPersonCityState(String city, String state) {
		if(total_addressBooks < 1) {
			System.out.println("No contacts in addressbook!");
			return;
		}
		AddressBookService tempAddressBook;
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

	public void searchPersonCity(String city, String person) {
		if(total_addressBooks < 1) {
			System.out.println("No contacts in addressbook!");
			return;
		}
		AddressBookService tempAddressBook;
		System.out.println("ADDRESSBOOK\tFOUND_COUNT");
		for (int i = 0; i < total_addressBooks; i++) {
			tempAddressBook=addressBooks[i];
			System.out.println(tempAddressBook.getAddressBookName()+":\t"+tempAddressBook.searchPersonCity(person, city));
		}
	}

	public void editAddressBook(String addressBookName) {
		if(total_addressBooks < 1) {
			System.out.println("No contacts in addressbook!");
			return;
		}
		int flag=-1;
		AddressBookService tempAddressBook;
		
		for (int i = 0; i < total_addressBooks; i++) {
			tempAddressBook=addressBooks[i];
			if(addressBookName.equals(tempAddressBook.getAddressBookName())) {
				System.out.println("AddressBook found! Enter new details: ");
				AddressBookImpl addressBook = createNewAddressBook(addressBookName);
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

	public AddressBookImpl createNewAddressBook(String addressBookName) {
		AddressBookImpl addressBook =new AddressBookImpl();
		addressBook.setAddressBookName(addressBookName);
		System.out.println(addressBook);
		return addressBook;
	}

	public void displayAddressBook() {
		if(total_addressBooks < 1) {
			System.out.println("No addressbook in system!");
			return;
		}		
		AddressBookService tempAddressBook;
		for (int i = 0; i < total_addressBooks; i++) {
			tempAddressBook=addressBooks[i];
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
		for (int i = 0; i < total_addressBooks; i++) {
			tempAddressBook=addressBooks[i];
			if(addressBookName.equals(tempAddressBook.getAddressBookName())) {
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
	
	public void addAddressBook(String addressBookName) {
		AddressBookImpl addressBook = createNewAddressBook(addressBookName);
		addressBooks[total_addressBooks] = addressBook;
		total_addressBooks++;
	}
	
	public void addContactToAddressBook(String addressBookName, Contact contact){
		if(total_addressBooks < 1) {
			System.out.println("No addressbook in system!");
			return;
		}
		int flag=-1;
		AddressBookService tempAddressBook;
		for (int i = 0; i < total_addressBooks; i++) {
			tempAddressBook=addressBooks[i];
			if(addressBookName.equals(tempAddressBook.getAddressBookName())) {
				System.out.println("Name found!");
				tempAddressBook.addContact(contact);
				flag=0;
				break;
			}
			addressBooks[i]=tempAddressBook;
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
		AddressBookService tempAddressBook;
		for (int i = 0; i < total_addressBooks; i++) {
			tempAddressBook=addressBooks[i];
			if(addressBookName.equals(tempAddressBook.getAddressBookName())) {
				System.out.println("Name found!");
				tempAddressBook.editContact(person, contact);
				flag=0;
				break;
			}
			addressBooks[i]=tempAddressBook;
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
		AddressBookService tempAddressBook;
		for (int i = 0; i < total_addressBooks; i++) {
			tempAddressBook=addressBooks[i];
			if(addressBookName.equals(tempAddressBook.getAddressBookName())) {
				System.out.println("Name found!");
				tempAddressBook.deleteContact(person);
				flag=0;
				break;
			}
			addressBooks[i]=tempAddressBook;
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
		AddressBookService tempAddressBook;
		for (int i = 0; i < total_addressBooks; i++) {
			tempAddressBook=addressBooks[i];
			if(addressBookName.equals(tempAddressBook.getAddressBookName())) {
				System.out.println("Name found!");
				tempAddressBook.searchPersonCity(person, city);
				flag=0;
				break;
			}
			addressBooks[i]=tempAddressBook;
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
		AddressBookService tempAddressBook;
		for (int i = 0; i < total_addressBooks; i++) {
			tempAddressBook=addressBooks[i];
			if(addressBookName.equals(tempAddressBook.getAddressBookName())) {
				System.out.println("Name found!");
				tempAddressBook.viewPersonByCityAndState(city, state);
				flag=0;
				break;
			}
			addressBooks[i]=tempAddressBook;
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
		AddressBookService tempAddressBook;
		for (int i = 0; i < total_addressBooks; i++) {
			tempAddressBook=addressBooks[i];
			if(addressBookName.equals(tempAddressBook.getAddressBookName())) {
				System.out.println("Name found!");
				tempAddressBook.getCityAndStateCount(city, state);
				flag=0;
				break;
			}
			addressBooks[i]=tempAddressBook;
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
		AddressBookService tempAddressBook;
		for (int i = 0; i < total_addressBooks; i++) {
			tempAddressBook=addressBooks[i];
			if(addressBookName.equals(tempAddressBook.getAddressBookName())) {
				System.out.println("Name found!");
				tempAddressBook.writeAddressBookData(ioservice);
				flag=0;
				break;
			}
			addressBooks[i]=tempAddressBook;
		}
		if(flag!=0)
			System.out.println("AddressBook not found!");
		System.out.flush();
	}
	
	public void readAddressBookDataInAddressBook(String addressBookName, IOService ioservice) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException{
		if(total_addressBooks < 1) {
			System.out.println("No addressbook in system!");
			return;
		}
		int flag=-1;
		AddressBookService tempAddressBook;
		for (int i = 0; i < total_addressBooks; i++) {
			tempAddressBook=addressBooks[i];
			if(addressBookName.equals(tempAddressBook.getAddressBookName())) {
				System.out.println("Name found!");
				tempAddressBook.readAddressBookData(ioservice);
				flag=0;
				break;
			}
			addressBooks[i]=tempAddressBook;
		}
		if(flag!=0)
			System.out.println("AddressBook not found!");
		System.out.flush();
	}
}
