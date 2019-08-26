package com.servicesImpl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.beans.User;
import com.dao.UserDao;
import com.daoImpl.UserDaoImpl;
import com.exceptions.UserNotFoundException;
import com.services.UserService;

public class UserServiceImpl implements UserService 
{
	UserDao ud=null;
	public UserServiceImpl() {
		super();
		ud=new UserDaoImpl();
	}

	@Override
	public boolean userLogin(String userName, String password) throws SQLException {
		
		return ud.userLogin(userName, password);
	}

	@Override
	public User getUser(int userId) throws UserNotFoundException, SQLException {
		
		return ud.getUser(userId);
	}
	

	@Override
	public boolean chechBirthday(int userId) throws SQLException {
		
		return ud.checkBirthday(userId);
	}

	@Override
	public List<User> showRequests(int userId)throws SQLException, UserNotFoundException {
		
		return ud.showRequests(userId);
	}

}
