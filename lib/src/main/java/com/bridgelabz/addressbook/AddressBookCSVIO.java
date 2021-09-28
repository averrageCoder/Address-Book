package com.bridgelabz.addressbook;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddressBookCSVIO {

	@SuppressWarnings("unchecked")
	public void writeData(List<Contact> addressBook, String filename){
		try (
			Writer writer = Files.newBufferedWriter(Paths.get(filename));
		) {
			StatefulBeanToCsv<Contact> beanToCsv = new StatefulBeanToCsvBuilder(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.build();
			
			beanToCsv.write(addressBook);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvDataTypeMismatchException e) {
			e.printStackTrace();
		} catch (CsvRequiredFieldEmptyException e) {
			e.printStackTrace();
		}
		
	}

	public List<Contact> readAddressBookData2(String filename) {
		List<Contact> addressBookData = new ArrayList<Contact>();
		try (
				Reader reader = Files.newBufferedReader(Paths.get(filename));
				CSVReader csvReader = new CSVReader(reader);
			) {
				List<String[]> records = csvReader.readAll();
				for (String[] record: records) {
					System.out.println("NAme: "+record[0]);
					Contact tempContact	= new Contact();
					tempContact.setFirstName( record[0]);
					tempContact.setLastName(record[1]);
					//tempContact.setAddress(record[2]);
					tempContact.setCity(record[3]);
					tempContact.setState(record[4]);
					tempContact.setZipCode(record[5]);
					tempContact.setPhoneNumber(record[6]);
					tempContact.setEmail(record[7]);
					addressBookData.add(tempContact);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return addressBookData;
	}
	
	@SuppressWarnings("unchecked")
	public List<Contact> readAddressBookData(String filename) {
		List<Contact> addressBookData = new ArrayList<Contact>();
		try (
				Reader reader = Files.newBufferedReader(Paths.get(filename));
		) {
			CsvToBean<Contact> csvToBean = new CsvToBeanBuilder(reader)
					.withType(Contact.class)
					.withIgnoreLeadingWhiteSpace(true)
					.build();
			
			Iterator<Contact> contactIterator = csvToBean.iterator();
			
			while(contactIterator.hasNext()) {
				Contact tempContact	= contactIterator.next();
				System.out.println(tempContact);
				addressBookData.add(tempContact);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return addressBookData;
	}

}
