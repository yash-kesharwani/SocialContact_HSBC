package com.servicesImpl;

import java.time.LocalDate;
import java.time.Period;
import java.sql.Date;
import java.sql.SQLException;

import com.dao.UserDao;
import com.daoImpl.UserDaoImpl;
import com.exceptions.UserAlreadyExistsException;
import com.exceptions.UserNotFoundException;
import com.services.UserRegisterService;

public class UserRegisterServiceImpl implements UserRegisterService {

	UserDao ud=new UserDaoImpl();
	@Override
	public int userRegister(String fullName, String email, long phone, char gender,Date dob, String city,
			String state, String country, int pincode, String company, String picture, String userName,
			String password) throws UserNotFoundException, SQLException
	{
		if(validateAge(dob))
		{
			try {
				if(checkUnique(email, userName, phone))
				{	
					ud.addUser(fullName,email,phone,gender,dob,city,state,country,pincode,company,picture,userName,password);
					return 0;
				}
				
			} catch (UserAlreadyExistsException e)
			{
				
				e.printStackTrace();
				return 1;
			}
		}
		else
			return 2;
		return 9999;
		
	}

	@Override
	public boolean validateAge(Date dob) 
	{
		LocalDate now=LocalDate.now();
		LocalDate birthDate=dob.toLocalDate();
		if ((birthDate != null) && (now != null))
		{
            int age= Period.between(birthDate, now).getYears();
            if(age>=18)return true;
        } else {
            return false;
        }
		
		return false;
	}

	@Override
	public boolean checkUnique(String email, String userName, long phone) throws UserAlreadyExistsException, UserNotFoundException, SQLException {
		if(ud.getUser(email)!=null && ud.getUser(userName)!=null && ud.getUser(phone)!=null)
		return false;
		return true;
	}

	@Override
	public boolean checkDisable(int status) {
		if(status==1)
			return true;
		return false;
	}

}
