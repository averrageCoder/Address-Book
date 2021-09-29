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
		List<AddressBookImpl> addressBookData = new ArrayList<AddressBookImpl>();
		try (Connection connection = this.getConnection()) {
			try {
				connection.setAutoCommit(false);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql0);
				while(resultSet.next()) {
					String name = resultSet.getString("name");
					AddressBookImpl tempAddressBook = new AddressBookImpl();
					tempAddressBook.setAddressBookName(name);
					String sql1=String.format("SELECT abc.contact_id "
							+ "FROM address_book_contact_mapping abc, address_book ab "
							+ "where ab.id=abc.address_book_id and ab.name='%s';", name);
					statement = connection.createStatement();
					ResultSet resultSet2 = statement.executeQuery(sql1);
					List<Integer> contact_ids= new ArrayList<Integer>();
					while(resultSet2.next()) {
						contact_ids.add(resultSet2.getInt("contact_id"));
					}
					for(int contact_id: contact_ids) {
						String sql2=String.format("select c.*, ca.* "
								+ "from contact c inner join contact_address ca on ca.contact_id=c.id "
								+ "where c.id=%s;", contact_id);
						statement = connection.createStatement();
						ResultSet resultSet3 = statement.executeQuery(sql2);
						while(resultSet3.next()) {
							String first_name = resultSet3.getString("first_name");
							String last_name = resultSet3.getString("last_name");
							String phone_number = resultSet3.getString("phone_number");
							String email = resultSet3.getString("email");
							String city = resultSet3.getString("city");
							String state = resultSet3.getString("state");
							String zip = resultSet3.getString("zip");
							tempAddressBook.addContact(new Contact(first_name, last_name, city, state, zip, phone_number, email));
						}
					}
					addressBookData.add(tempAddressBook);
				}
				
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			}
			finally {
				if(connection!=null) {
					connection.close();
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return addressBookData;
	}

	public int updateEmployeeUsingPreparedStatement(String first_name, String phone_number) {
		String sql0 = String.format("update contact set phone_number='%s' where first_name='%s'",phone_number,first_name);
		List<AddressBookImpl> addressBookData = new ArrayList<AddressBookImpl>();
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(sql0);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<AddressBookImpl> readAddressBookDataBAsedOnDate(String date) {
		String sql0 = "select * from address_book;";
		List<AddressBookImpl> addressBookData = new ArrayList<AddressBookImpl>();
		try (Connection connection = this.getConnection()) {
			try {
				connection.setAutoCommit(false);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql0);
				while(resultSet.next()) {
					String name = resultSet.getString("name");
					AddressBookImpl tempAddressBook = new AddressBookImpl();
					tempAddressBook.setAddressBookName(name);
					String sql1=String.format("SELECT abc.contact_id "
							+ "FROM address_book_contact_mapping abc, address_book ab "
							+ "where ab.id=abc.address_book_id and ab.name='%s';", name);
					statement = connection.createStatement();
					ResultSet resultSet2 = statement.executeQuery(sql1);
					List<Integer> contact_ids= new ArrayList<Integer>();
					while(resultSet2.next()) {
						contact_ids.add(resultSet2.getInt("contact_id"));
					}
					for(int contact_id: contact_ids) {
						String sql2=String.format("select c.*, ca.* "
								+ "from contact c inner join contact_address ca on ca.contact_id=c.id "
								+ "where c.id=%s and c.date_added between CAST('%s' as date) and date(now());", contact_id, date);
						statement = connection.createStatement();
						ResultSet resultSet3 = statement.executeQuery(sql2);
						while(resultSet3.next()) {
							String first_name = resultSet3.getString("first_name");
							String last_name = resultSet3.getString("last_name");
							String phone_number = resultSet3.getString("phone_number");
							String email = resultSet3.getString("email");
							String city = resultSet3.getString("city");
							String state = resultSet3.getString("state");
							String zip = resultSet3.getString("zip");
							tempAddressBook.addContact(new Contact(first_name, last_name, city, state, zip, phone_number, email));
						}
					}
					addressBookData.add(tempAddressBook);
				}
				
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			}
			finally {
				connection.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return addressBookData;
	}

	public List<Contact> readAddressBookDataBAsedOnCityOrState(String City, String State) {
		String sql0 = "select * from address_book;";
		List<Contact> contactList = new ArrayList<Contact>();
		Connection connection;
		try {
			connection = this.getConnection();
			try {
				connection.setAutoCommit(false);
				Statement statement = connection.createStatement();
				String sql2=String.format("select c.*, ca.* "
						+ "from contact c inner join contact_address ca on ca.contact_id=c.id "
						+ "where ca.city='%s' or ca.state='%s'", City, State);
				ResultSet resultSet3 = statement.executeQuery(sql2);
				while(resultSet3.next()) {
					String first_name = resultSet3.getString("first_name");
					String last_name = resultSet3.getString("last_name");
					String phone_number = resultSet3.getString("phone_number");
					String email = resultSet3.getString("email");
					String city = resultSet3.getString("city");
					String state = resultSet3.getString("state");
					String zip = resultSet3.getString("zip");
					contactList.add(new Contact(first_name, last_name, city, state, zip, phone_number, email));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				connection.rollback();
			}
			finally {
				if(connection!=null) {
					connection.close();
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return contactList;
	}

	public int insertContact(String first_name, String last_name, String phone, String email) {
		String sql0 = String.format("INSERT INTO `contact`\n"
				+ "(`first_name`,`last_name`,`phone_number`,`email`)\n"
				+ "VALUES ('%s','%s','%s','%s');",first_name,last_name,phone,email);
		List<AddressBookImpl> addressBookData = new ArrayList<AddressBookImpl>();
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(sql0);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
