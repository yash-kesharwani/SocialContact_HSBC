package com.services;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.beans.User;
import com.exceptions.UserNotFoundException;

public interface UserService
{
	public boolean userLogin(String userName,String password) throws SQLException;
	public User getUser(int userId)throws UserNotFoundException,SQLException;
	public boolean chechBirthday(int userId)throws SQLException;
	public List<User> showRequests(int userId)throws SQLException, UserNotFoundException;
}
