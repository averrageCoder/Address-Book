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

public class AddressBookManagerJSONService {
	
	public void writeData(List<AddressBookImpl> system, String filename) {
		Gson gson = new Gson();
		String json = gson.toJson(system);
		FileWriter writer;
		try {
			writer = new FileWriter(filename);
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public List<AddressBookImpl> readAddressBookData(String filename) {
		Gson gson = new Gson();
		List<AddressBookImpl> addressBooks = new ArrayList<AddressBookImpl>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
			AddressBookImpl[] addressBookData = gson.fromJson(br, AddressBookImpl[].class);
			addressBooks = Arrays.asList(addressBookData);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return addressBooks;
	}
	
}
