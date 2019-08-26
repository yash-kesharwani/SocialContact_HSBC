package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.beans.User;
import com.exceptions.UserNotFoundException;

public interface UserDao {

	public boolean userLogin(String userName,String password) throws SQLException;
	public User getUser(int userId) throws UserNotFoundException, SQLException;
	public User getUser(String value) throws UserNotFoundException, SQLException;
	public User getUser(long phone) throws UserNotFoundException, SQLException;
	
	public boolean checkBirthday(int userId) throws SQLException;
	public List<User> showRequests(int userId) throws SQLException, UserNotFoundException;
	public void addUser(String fullName, String email, long phone, char gender, java.sql.Date dob, String city,
			String state, String country, int pincode, String company, String picture, String userName,
			String password) throws UserNotFoundException, SQLException;
}
