package com.bridgelabz.addressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class SystemDBService {
	
	private Connection getConnection() throws SQLException {
		String jdbcUrl = "jdbc:mysql://localhost:3306/addressBook_system?useSSL=false&characterEncoding=utf8";
		String userName = "root";
		String password ="";
		Connection connection;
		System.out.println("Connecting to database: "+jdbcUrl);
		connection = DriverManager.getConnection(jdbcUrl, userName, password);
		System.out.println("Connection is successful!!!"+connection);
		return connection;
	}

	public List<AddressBookImpl> readAddressBookData() {
		String sql0 = "select * from address_book;";
		String sql1="select * from contact c inner join contact_address ca on ca.contact_id=c.id";
		List<AddressBookImpl> addressBookData = new ArrayList<AddressBookImpl>();
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql0);
			while(resultSet.next()) {
				String name = resultSet.getString("name");
				addressBookData.add(new AddressBookImpl(name));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addressBookData;
	}
}
