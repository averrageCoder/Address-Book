package com.bridgelabz.addressbook;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class AddressBookJSONIO {

	public void writeData(List<Contact> addressBook, String filename) {
		Gson gson = new Gson();
		String json = gson.toJson(addressBook);
		FileWriter writer;
		try {
			writer = new FileWriter(filename);
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public List<Contact> readAddressBookData(String filename) {
		Gson gson = new Gson();
		List<Contact> addressBooks = new ArrayList<Contact>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
			Contact[] addressBookData = gson.fromJson(br, Contact[].class);
			addressBooks = Arrays.asList(addressBookData);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return addressBooks;
	}

}
