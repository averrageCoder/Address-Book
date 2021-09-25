package com.bridgelabz.addressbook;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.bridgelabz.addressbook.AddressBookImpl.IOService;

public class AddressBookTest {
	
	public void givenEmployeePayrollInDB_whenRetreivedShouldMatchEmployeeCount() {
		SystemClassIF systemClass = new SystemClassImpl();
		int size = systemClass.readAddressBookData(IOService.DB_IO);
		assertEquals(3, 2);
	}
	
}
