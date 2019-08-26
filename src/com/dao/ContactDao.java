package com.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.beans.Address;
import com.beans.Contact;

public interface ContactDao
{
	public boolean addContact(String fullName, String email, long phone, Address contactAddress, Date dob, char gender,String company, int userId,String picture) throws SQLException;
	public boolean checkEmail(String email) throws SQLException ;
	public boolean checkPhone(long phone) throws SQLException;
	public List<Contact> searchContact(String fullName, String email, long phone) throws SQLException;
	public boolean updateContact(Contact c) throws SQLException;
	public boolean deleteContact(int cId) throws SQLException;
	public boolean updateAddress(int aId, String city, String state,String country, int pincode)throws SQLException;
	
}
