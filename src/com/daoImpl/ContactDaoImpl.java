package com.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import com.beans.Address;
import com.beans.Contact;
import com.dao.ContactDao;

public class ContactDaoImpl implements ContactDao {

	private final static String conURL = "jdbc:derby:C:\\Users\\Fiona\\Documents\\workspace-sts-3.9.9.RELEASE\\derby\\mydb;create = true;";
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private PreparedStatement preparedStatement_2 = null;

	private ResultSet resultSet = null;
	private ResultSet resultSet_2 = null;

	public ContactDaoImpl() {
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
	public boolean addContact(String fullName, String email, long phone, Address contactAddress, Date dob, char gender,String company, int userId,String picture) throws SQLException
	{
		preparedStatement=connection.prepareStatement("INSERT INTO CONTACTS(FULL_NAME, EMAIL, PHONE_NUMBER, GENDER, DOB, COMPANY, PROFILE_PICTURE, USER_ID) VALUES(?,?,?,?,?,?,?,?)");
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
		preparedStatement.setInt(8, userId);
		if(preparedStatement.executeUpdate() == 1) {
			preparedStatement_2 = connection.prepareStatement("SELECT C_ID FROM CONTACTS WHERE EMAIL = ?");
			preparedStatement_2.setString(1, email);
			resultSet = preparedStatement_2.executeQuery();
			if(resultSet.next()) {
				System.out.println(resultSet.getInt("C_ID"));
				
				preparedStatement = connection.prepareStatement("INSERT INTO CONTACTS_ADDRESS(CITY, STATE, COUNTRY, PINCODE, C_ID) VALUES(?,?,?,?,?)");
				preparedStatement.setString(1, contactAddress.getCity());
				preparedStatement.setString(2, contactAddress.getState());
				preparedStatement.setString(3, contactAddress.getCountry());
				preparedStatement.setLong(4, contactAddress.getPincode());
				preparedStatement.setInt(5, resultSet.getInt("C_ID"));
				preparedStatement.execute();
			}
		}
			
		return false;
	}

	@Override
	public boolean checkEmail(String email) throws SQLException 
	{
		String sql="SELECT EMAIL FROM CONTACTS WHERE EMAIL = ?";
		preparedStatement= connection.prepareStatement(sql);
		preparedStatement.setString(1,email);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next())return false;
		return true;
	}

	@Override
	public boolean checkPhone(long phone) throws SQLException
	{
		String sql="SELECT PHONE_NUMBER FROM CONTACTS WHERE PHONE_NUMBER = ?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setLong(1,phone);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next())return false;
		return true;
	}

	@Override
	public List<Contact> searchContact(String fullName, String email, long phone) throws SQLException
	{
		String sql="select * from contacts where full_name=? or email=? or phone_number=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1,fullName);
		preparedStatement.setString(2,email);
		preparedStatement.setLong(3,phone);
		resultSet_2=preparedStatement.executeQuery();
		
		List<Contact> searched = new ArrayList<Contact>();
		while(resultSet_2.next())
		{
			searched.add(new Contact(resultSet_2.getInt("c_id"), 
					resultSet_2.getString("full_name"),
					resultSet_2.getString("email"), 
					resultSet_2.getLong("phone_number"), 
					(resultSet_2.getString("gender").charAt(0)), 
					resultSet_2.getDate("dob"), 
					getAdress(resultSet_2.getInt("c_id")), 
					resultSet_2.getString("company"), 
					resultSet_2.getString("profile_picture"), 
					resultSet_2.getInt("user_id")));
		}
		return searched;
	}

	private Address getAdress(int c_id) throws SQLException
	{
		String sql="select * from contacts_address where c_id=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1,c_id);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next())
		{
			return new Address(resultSet.getInt("aid"), resultSet.getString("city"),resultSet.getString("state"),resultSet.getString("country"),resultSet.getInt("pincode"), c_id);
		}
		return null;
	}

	@Override
	public boolean updateContact(Contact c) throws SQLException
	{
		String sql="update contacts set full_name =?, email=?, "
				+ "phone_number=?, gender=?, dob=?, company=?, "
				+ "profile_picture=? where c_id=?)";
		preparedStatement=connection.prepareStatement("UPDATE CONTACTS SET FULL_NAME = ?, EMAIL = ?, PHONE_NUMBER = ?, GENDER = ?, DOB = ?, COMPANY = ?, PROFILE_PICTURE = ? WHERE C_ID = ?");
		preparedStatement.setString(1, c.getFullName());
		preparedStatement.setString(2, c.getEmail());
		preparedStatement.setLong(3, c.getPhone());
		if(c.getGender()=='m'||c.getGender()=='M')
			preparedStatement.setString(4, "M");
		else
			preparedStatement.setString(4, "F");
		preparedStatement.setDate(5, (Date) c.getDob());
		preparedStatement.setString(6, c.getCompany());
		preparedStatement.setString(7, c.getPicture());
		preparedStatement.setInt(8, c.getcId());
		int a=preparedStatement.executeUpdate();
		boolean flag=updateAddress(c.getContactAddress().getaId(), c.getContactAddress().getCity(), c.getContactAddress().getState(), c.getContactAddress().getCountry(),c.getContactAddress().getPincode());
		if(a==1 && flag)return true;
		return false;
	}
	
	@Override
	public boolean updateAddress(int aId, String city, String state,String country, int pincode)throws SQLException
	{
		String sql="update contacts_adDress set city=?,state=?,country=?,pincode=? where aid=? ";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, city);
		preparedStatement.setString(2, state);
		preparedStatement.setString(3, country);
		preparedStatement.setInt(4, pincode);
		preparedStatement.setInt(5, aId);
		int a=preparedStatement.executeUpdate();
		if(a==1)return true;
		return false;
	}

	@Override
	public boolean deleteContact(int cId) throws SQLException
	{
		String sql="delete from contacts where c_id=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1, cId);
		int a=preparedStatement.executeUpdate();
		String sql1="delete from contacts_address where c_id=?";
		preparedStatement=connection.prepareStatement(sql1);
		preparedStatement.setInt(1, cId);
		int b=preparedStatement.executeUpdate();
		if(a==1)return true;
		return false;
	}
	
	
}
