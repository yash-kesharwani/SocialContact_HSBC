package com.services;

import java.sql.Date;
import java.sql.SQLException;


import com.exceptions.UserAlreadyExistsException;
import com.exceptions.UserNotFoundException;

public interface UserRegisterService
{
	// 0= added, 1=already present, 2=minor 
	public int userRegister(String fullName, String email, long phone, char gender,Date dob,String city,String state,String country,int pincode,String company,String picture, String userName, String password  ) throws UserNotFoundException, SQLException;
	boolean validateAge(Date dob);
	boolean checkUnique(String email, String userName, long phone )throws UserAlreadyExistsException, UserNotFoundException, SQLException;
	boolean checkDisable(int status);
}
