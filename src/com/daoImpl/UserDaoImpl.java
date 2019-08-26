package com.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Date;

import com.beans.Address;
import com.beans.User;
import com.dao.UserDao;
import com.exceptions.UserNotFoundException;

public class UserDaoImpl implements UserDao {

	private final static String conURL = "jdbc:derby:C:\\Users\\Fiona\\Documents\\workspace-sts-3.9.9.RELEASE\\derby\\mydb;create = true";
	private static Connection connection = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement=null;
	private static PreparedStatement preparedStatement_2=null;

	private static ResultSet resultSet = null;
	private static ResultSet resultSet_2 = null;	
    
	public UserDaoImpl() {
		try {
			connection = DriverManager.getConnection(conURL);
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			System.out.println("++DB Driver Loaded++");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	

	@Override
	public boolean userLogin(String userName, String password) throws SQLException {
		preparedStatement = connection.prepareStatement("SELECT * FROM USER_CREDENTIALS WHERE USERNAME = ? AND PASSWORD = ?");
		preparedStatement.setString(1, userName);
		preparedStatement.setString(2, password);
		resultSet = preparedStatement.executeQuery();
		if(resultSet.next())
			return true;
		return false;
		
	}

	@Override
	public User getUser(int userId) throws UserNotFoundException, SQLException {
		preparedStatement = connection.prepareStatement("SELECT * FROM USERS WHERE USER_ID = ?");
		preparedStatement.setInt(1, userId);
		resultSet_2 = preparedStatement.executeQuery();
		if(resultSet_2.next())
		{
			return new User(resultSet_2.getInt("USER_ID"),
					resultSet_2.getString("FULL_NAME"), 
					resultSet_2.getString("EMAIL"), 
					resultSet_2.getLong("PHONE_NUMBER"),
					(resultSet_2.getString("GENDER").charAt(0)),
					resultSet_2.getDate("DOB"),
					getAdress(resultSet_2.getInt("USER_ID")), 
					resultSet_2.getString("COMPANY"), 
					resultSet_2.getString("PROFILE_PICTURE"),
					resultSet_2.getInt("STATUS"),
					resultSet_2.getInt("BLOCK_COUNT"),
					resultSet_2.getDate("LAST_ACTIVE"));
		}
		return null;
		
	}
	
	private Address getAdress(int userId) throws SQLException
	{
		String sql="SELECT * FROM USERS_ADDRESS WHERE USER_ID = ?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1,userId);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next())
		{
			return new Address(resultSet.getInt("AID"), 
					resultSet.getString("CITY"),
					resultSet.getString("STATE"),
					resultSet.getString("COUNTRY"),
					resultSet.getInt("PINCODE"), 
					userId);
		}
		return null;
	}

	@Override
	public boolean checkBirthday(int userId) throws SQLException {
		Calendar todaysDate = Calendar.getInstance();
		Calendar bdayDate = Calendar.getInstance();
		String sql="SELECT DOB FROM USERS WHERE USER_ID = ?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1,userId);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next())
		{
			java.sql.Date currentDate  = new java.sql.Date(System.currentTimeMillis());
			todaysDate.setTime(currentDate);
			bdayDate.setTime(resultSet.getDate("DOB"));
			if((todaysDate.get(Calendar.MONTH) == bdayDate.get(Calendar.MONTH)) && ((todaysDate.get(Calendar.DAY_OF_MONTH)==bdayDate.get(Calendar.DAY_OF_MONTH))))
				return true;
		}
		return false;
	}

	@Override
	public List<User> showRequests(int userId) throws SQLException, UserNotFoundException {
		preparedStatement = connection.prepareStatement("SELECT * FROM FRIENDSHIP WHERE USER_ID = ? AND STATUS = ?");
		preparedStatement.setInt(1, userId);
		preparedStatement.setInt(2, 0);
		ArrayList<Integer> friendIds = new ArrayList<Integer>();
		List<User> friendList = new ArrayList<User>();
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			friendIds.add(resultSet.getInt("FRIEND_ID"));
		}
		preparedStatement = connection.prepareStatement("SELECT * FROM FRIENDSHIP WHERE FRIEND_ID = ? AND STATUS = ?");
		preparedStatement.setInt(1, userId);
		preparedStatement.setInt(2, 0);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			friendIds.add(resultSet.getInt("USER_ID"));
		}
		System.out.println(friendIds);
		preparedStatement_2 = connection.prepareStatement("SELECT * FROM USERS WHERE USER_ID = ?");
		for(Integer id: friendIds) {
			friendList.add(getUser(id));
		}
		
		return friendList;
	}




	@Override
	public void addUser(String fullName, String email, long phone, char gender, Date dob, String city, String state,
			String country, int pincode, String company, String picture, String userName, String password) throws UserNotFoundException, SQLException
	{
		if(getUser(email)==null) {
			preparedStatement = connection.prepareStatement("INSERT INTO USERS(FULL_NAME, EMAIL, PHONE_NUMBER, GENDER, DOB, COMPANY, PROFILE_PICTURE, STATUS, BLOCK_COUNT, LAST_ACTIVE) VALUES(?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, fullName);
			preparedStatement.setString(2, email);
			preparedStatement.setLong(3, phone);
			if(gender=='m'||gender=='M')
				preparedStatement.setString(4, "M");
			else
				preparedStatement.setString(4, "F");
			preparedStatement.setDate(5, dob);
			preparedStatement.setString(6, company);
			preparedStatement.setString(7, picture);
			preparedStatement.setInt(8, 1);
			preparedStatement.setInt(9, 5);
			preparedStatement.setDate(10, new Date(System.currentTimeMillis()));
			if(preparedStatement.executeUpdate()==1) {
				preparedStatement_2 = connection.prepareStatement("SELECT USER_ID FROM USERS WHERE EMAIL = ?");
				preparedStatement_2.setString(1, email);
				resultSet = preparedStatement_2.executeQuery();
				if(resultSet.next()) {
					System.out.println(resultSet.getInt("USER_ID"));
					preparedStatement = connection.prepareStatement("INSERT INTO USER_CREDENTIALS VALUES(?,?,?)");
					preparedStatement.setInt(1, resultSet.getInt("USER_ID"));
					preparedStatement.setString(2, userName);
					preparedStatement.setString(3, password);
					preparedStatement.execute();
					preparedStatement = connection.prepareStatement("INSERT INTO USERS_ADDRESS(CITY, STATE, COUNTRY, PINCODE, USER_ID) VALUES(?,?,?,?,?)");
					preparedStatement.setString(1, city);
					preparedStatement.setString(2, state);
					preparedStatement.setString(3, country);
					preparedStatement.setLong(4, pincode);
					preparedStatement.setInt(5, resultSet.getInt("USER_ID"));
					preparedStatement.execute();
				}
			}
		}
		
	}




	@Override
	public User getUser(String value) throws UserNotFoundException, SQLException {
		if(value.contains("@") && value.contains(".")) 
			preparedStatement = connection.prepareStatement("SELECT * FROM USERS WHERE EMAIL = ?");
		else
			preparedStatement = connection.prepareStatement("SELECT * FROM USERS WHERE FULL_NAME = ?");
		preparedStatement.setString(1, value);
		resultSet_2 = preparedStatement.executeQuery();
		if(resultSet_2.next())
		{
			return new User(resultSet_2.getInt("USER_ID"),
					resultSet_2.getString("FULL_NAME"), 
					resultSet_2.getString("EMAIL"), 
					resultSet_2.getLong("PHONE_NUMBER"),
					(resultSet_2.getString("GENDER").charAt(0)),
					resultSet_2.getDate("DOB"),
					getAdress(resultSet_2.getInt("USER_ID")), 
					resultSet_2.getString("COMPANY"), 
					resultSet_2.getString("PROFILE_PICTURE"),
					resultSet_2.getInt("STATUS"),
					resultSet_2.getInt("BLOCK_COUNT"),
					resultSet_2.getDate("LAST_ACTIVE"));
		}
		return null;
	}




	@Override
	public User getUser(long phone) throws UserNotFoundException, SQLException {
		preparedStatement = connection.prepareStatement("SELECT * FROM USERS WHERE PHONE_NUMBER = ?");
		preparedStatement.setLong(1, phone);
		resultSet_2 = preparedStatement.executeQuery();
		if(resultSet_2.next())
		{
			return new User(resultSet_2.getInt("USER_ID"),
					resultSet_2.getString("FULL_NAME"), 
					resultSet_2.getString("EMAIL"), 
					resultSet_2.getLong("PHONE_NUMBER"),
					(resultSet_2.getString("GENDER").charAt(0)),
					resultSet_2.getDate("DOB"),
					getAdress(resultSet_2.getInt("USER_ID")), 
					resultSet_2.getString("COMPANY"), 
					resultSet_2.getString("PROFILE_PICTURE"),
					resultSet_2.getInt("STATUS"),
					resultSet_2.getInt("BLOCK_COUNT"),
					resultSet_2.getDate("LAST_ACTIVE"));
		}
		return null;
	}




}
