package com.bridgelabz.addressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.addressbook.AddressBookExceptions.ExceptionType;


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
	
	public List<Contact> getContactListFromResultSet(ResultSet resultSet) throws SQLException {
		List<Contact> contactList = new ArrayList<Contact>();
		while(resultSet.next()) {
			String first_name_ = resultSet.getString("first_name");
			String last_name = resultSet.getString("last_name");
			String phone_number = resultSet.getString("phone_number");
			String email = resultSet.getString("email");
			String city = resultSet.getString("city");
			String state = resultSet.getString("state");
			String zip = resultSet.getString("zip");
			contactList.add(new Contact(first_name_, last_name, city, state, zip, phone_number, email));
		}
		return contactList;
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
					String type = resultSet.getString("type");
					AddressBookImpl tempAddressBook = new AddressBookImpl(name, type);

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
						List<Contact> contactList = getContactListFromResultSet(resultSet3);
						for(Contact contact: contactList) {
							tempAddressBook.addContact(contact);
						}
					}
					addressBookData.add(tempAddressBook);
				}
				
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
				throw new AddressBookExceptions(ExceptionType.SQL_ERROR, "SQL ERROR!");
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
					String type = resultSet.getString("type");
					AddressBookImpl tempAddressBook = new AddressBookImpl(name, type);
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
						List<Contact> contactList = getContactListFromResultSet(resultSet3);
						for(Contact contact: contactList) {
							tempAddressBook.addContact(contact);
						}
					}
					addressBookData.add(tempAddressBook);
				}
				
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
				throw new AddressBookExceptions(ExceptionType.SQL_ERROR, "SQL ERROR!");
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
				ResultSet resultSet = statement.executeQuery(sql2);
				contactList = getContactListFromResultSet(resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
				connection.rollback();
				throw new AddressBookExceptions(ExceptionType.SQL_ERROR, "SQL ERROR!");
			}
			finally {
				if(connection!=null) {
					connection.close();
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new AddressBookExceptions(ExceptionType.SQL_ERROR, "SQL ERROR!");
		}
		return contactList;
	}

	public int insertContact(String first_name, String last_name, String phone, String email, String city, String state, int zipCode) {
		int contactID=-1;
		String sql0 = String.format("INSERT INTO `contact`\n"
				+ "(`first_name`,`last_name`,`phone_number`,`email`)\n"
				+ "VALUES ('%s','%s','%s','%s');",first_name,last_name,phone,email);
		List<AddressBookImpl> addressBookData = new ArrayList<AddressBookImpl>();
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			int rowAffected = statement.executeUpdate(sql0, statement.RETURN_GENERATED_KEYS);
			if(rowAffected==1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if(resultSet.next()) contactID = resultSet.getInt(1);
				String sql = String.format("INSERT INTO `addressBook_system`.`contact_address`\n"
						+ "(`contact_id`,`city`,`state`,`zip`)\n"
						+ "VALUES (%s,'%s','%s',%s)", contactID, city, state, zipCode);
				statement = connection.createStatement();
				return statement.executeUpdate(sql);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new AddressBookExceptions(ExceptionType.SQL_ERROR, "SQL ERROR!");
		}
		return 0;
	}

	public List<Contact> readAllContactsFromDB() {
		List<Contact> contactList = new ArrayList<Contact>();
		Connection connection;
		try {
			connection = this.getConnection();
			try {
				connection.setAutoCommit(false);
				Statement statement = connection.createStatement();
				String sql="select c.*, ca.* "
						+ "from contact c inner join contact_address ca on ca.contact_id=c.id ";
				ResultSet resultSet = statement.executeQuery(sql);
				contactList = getContactListFromResultSet(resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
				connection.rollback();
				throw new AddressBookExceptions(ExceptionType.SQL_ERROR, "SQL ERROR!");
			}
			finally {
				if(connection!=null) {
					connection.close();
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new AddressBookExceptions(ExceptionType.SQL_ERROR, "SQL ERROR!");
		}
		return contactList;
	}

	public Contact readContactBasedOnName(String first_name) {
		List<Contact> contactList = null;
		Connection connection;
		try {
			connection = this.getConnection();
			try {
				connection.setAutoCommit(false);
				Statement statement = connection.createStatement();
				String sql=String.format("select c.*, ca.* "
						+ "from contact c inner join contact_address ca on ca.contact_id=c.id "
						+ "where c.first_name='%s';",first_name);
				ResultSet resultSet = statement.executeQuery(sql);
				contactList = getContactListFromResultSet(resultSet);
				
			} catch (SQLException e) {
				e.printStackTrace();
				connection.rollback();
				throw new AddressBookExceptions(ExceptionType.SQL_ERROR, "SQL ERROR!");
			}
			finally {
				if(connection!=null) {
					connection.close();
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new AddressBookExceptions(ExceptionType.SQL_ERROR, "SQL ERROR!");
		}
		return contactList.get(0);
	}
}
