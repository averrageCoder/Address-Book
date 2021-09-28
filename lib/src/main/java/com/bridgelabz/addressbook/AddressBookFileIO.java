package com.bridgelabz.addressbook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AddressBookFileIO {

	public void writeData(List<Contact> addressBook, String filename) {
		
		StringBuffer addressBookBuffer = new StringBuffer();
		addressBook.forEach(contact -> {
			String addressBookString = contact.toString().concat("\n");
			addressBookBuffer.append(addressBookString);
		});
		
		try {
			Files.write(Paths.get(filename), addressBookBuffer.toString().getBytes());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public List<Contact> readAddressBookData(String filename) {
		List<Contact> addressBookData = new ArrayList<Contact>();
		try {
			Files.lines(new File(filename).toPath())
						.map(line -> line.trim())
						.forEach(line -> {
							//firstName=z, lastName=x, address=c, city=v, state=b, zipCode=n, phoneNumber=m, email=
							Contact tempAddressBook = new Contact();
							tempAddressBook.setFirstName(line.split(",")[0].split("=")[1]);
							tempAddressBook.setLastName(line.split(",")[1].split("=")[1]);
							//tempAddressBook.setAddress(line.split(",")[2].split("=")[1]);
							tempAddressBook.setCity(line.split(",")[3].split("=")[1]);
							tempAddressBook.setState(line.split(",")[4].split("=")[1]);
							tempAddressBook.setZipCode(line.split(",")[5].split("=")[1]);
							tempAddressBook.setPhoneNumber(line.split(",")[6].split("=")[1]);
							tempAddressBook.setEmail(line.split(",",8)[7].split("=")[1]);
							 //Integer.parseInt(line.split(",")[0].split("=")[1]), line.split(",")[1].split("=")[1], Double.parseDouble(line.split(",")[2].split("=")[1])
							addressBookData.add(tempAddressBook);
							System.out.println(tempAddressBook);
						});
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return addressBookData;
	}

}
