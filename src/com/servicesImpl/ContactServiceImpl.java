package com.servicesImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import com.beans.Address;
import com.beans.Contact;
import com.dao.ContactDao;
import com.daoImpl.ContactDaoImpl;
import com.exceptions.ContactAlreadyExistsException;
import com.exceptions.ContactNotFoundException;
import com.services.ContactService;

public class ContactServiceImpl implements ContactService
{

	ContactDao cd=new ContactDaoImpl();
	@Override
	public boolean addContact(String fullName, String email, long phone, Address contactAddress, Date dob, char gender,
			String company, int userId, String picture) throws SQLException
	{
		return cd.addContact(fullName, email, phone, contactAddress, dob, gender, company, userId, picture);
	}

	@Override
	public boolean checkEmail(String email) throws SQLException 
	{
		 return cd.checkEmail(email);
	}

	@Override
	public boolean checkPhone(long phone) throws ContactAlreadyExistsException, SQLException 
	{
		return cd.checkPhone(phone);
	}

	@Override
	public List<Contact> searchContact(String fullName, String email, long phone) throws ContactNotFoundException, SQLException {
	
		List<Contact> searched=cd.searchContact(fullName, email, phone);
		if(!searched.isEmpty())return searched;
		else
			throw new ContactNotFoundException();
	}

	@Override
	public boolean updateContact(Contact c) throws SQLException
	{
		return cd.updateContact(c);
		
	}

	@Override
	public boolean deleteContact(int cId) throws SQLException
	{
		return cd.deleteContact(cId);
	}

}
