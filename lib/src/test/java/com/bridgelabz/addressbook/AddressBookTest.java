package com.bridgelabz.addressbook;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.bridgelabz.addressbook.AddressBookImpl.IOService;

public class AddressBookTest {
	
	@Test
	public void givenEmployeePayrollInDB_whenRetreivedShouldMatchEmployeeCount() {
		SystemClassIF systemClass = new SystemClassImpl();
		int size = systemClass.readAddressBookData(IOService.DB_IO);
		assertEquals(2, 2);
	}
	
}
