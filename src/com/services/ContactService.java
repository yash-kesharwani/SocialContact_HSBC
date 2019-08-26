package com.services;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.beans.Address;
import com.beans.Contact;
import com.exceptions.ContactAlreadyExistsException;
import com.exceptions.ContactNotFoundException;

public interface ContactService
{
	public boolean addContact(String fullName,String email,long phone, Address contactAddress, Date dob, char gender, String company,int userId, String picture )throws SQLException;
	boolean checkEmail(String email)throws SQLException ;
	boolean checkPhone(long phone) throws ContactAlreadyExistsException,SQLException;
	public List<Contact> searchContact(String fullName, String email, long phone)throws ContactNotFoundException,SQLException;
	public boolean updateContact(Contact c)throws SQLException;
	public boolean deleteContact(int cId)throws SQLException;

}
