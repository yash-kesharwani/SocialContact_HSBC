package com.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.dao.AdminDao;

public class AdminDaoImpl implements AdminDao
{
	private final static String conURL = "jdbc:derby:C:\\Users\\Fiona\\Documents\\workspace-sts-3.9.9.RELEASE\\derby\\mydb;create = true";
	private Connection connection=null;
	private PreparedStatement preparedStatement=null;
	private ResultSet resultSet=null;
	 void connect() throws SQLException
	 {
		 connection=DriverManager.getConnection("");
		 ((AdminDaoImpl) connection).connect();
	 }
	 public AdminDaoImpl() {
		
			
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
	 public boolean deleteUser(int userId)throws SQLException
	 {
		 //String sql=;
		 preparedStatement = connection.prepareStatement("DELETE FROM USERS WHERE USER_ID = ?");
		 preparedStatement.setInt(1, userId);
		 if(preparedStatement.executeUpdate()==1) {
			 return true;
		 }
		 return false;
	 }
	 @Override
	 public boolean disableUser(int userId)throws SQLException
	 {
		 String sql="update users set status=0 where user_id=?";
		 preparedStatement = connection.prepareStatement(sql);
		 preparedStatement.setInt(1, userId);
		 int a=preparedStatement.executeUpdate();
		 if(a==1)
			 return true;
		 return false;
	 }
	 @Override
	 public Map<Integer,String> getToDisable() throws SQLException
	 {
		 
		 String sql="select user_id,full_name from users where status=0 ";
		 preparedStatement = connection.prepareStatement(sql);
		 resultSet=preparedStatement.executeQuery();
		 Map<Integer,String> toDisable = new HashMap<Integer, String>();
		 while(resultSet.next())
		 {
			 toDisable.put(resultSet.getInt("user_id"), resultSet.getString("full_name"));
		 }
		 
		 return toDisable;
	 }
	 @Override
	 public Date getLastActive(int userId) throws SQLException
	 {
		 String sql="select last_active from users where user_id=?";
		 preparedStatement = connection.prepareStatement(sql);
		 preparedStatement.setInt(1,userId);
		 resultSet=preparedStatement.executeQuery();
		 if(resultSet.next()) 
			 return resultSet.getDate("LAST_ACTIVE");
		 return null;
	 }
	 @Override
	 public Map<Integer,String> getToDelete() throws SQLException
	 {
		 String sql="select user_id,full_name from users where block_count>3";
		 preparedStatement = connection.prepareStatement(sql);
		 resultSet=preparedStatement.executeQuery();
		 Map<Integer,String> toDelete = new HashMap<Integer, String>();
		 while(resultSet.next())
		 {
			 toDelete.put(resultSet.getInt("user_id"), resultSet.getString("full_name"));
		 }
		 return toDelete;
	 }
	 @Override
	 public int[] getSystemInfo() throws SQLException
	 {
		 int totalUsers=0,activeUsers=0;
		 int arr[]=new int[2];
		 String sql="SELECT COUNT(*) AS ACTIVE_USERS FROM USERS WHERE STATUS = 1 GROUP BY STATUS";
		 preparedStatement = connection.prepareStatement(sql);
		 resultSet=preparedStatement.executeQuery();
		 if(resultSet.next()) {
			 activeUsers = resultSet.getInt("ACTIVE_USERS");
		 }
		 sql="SELECT COUNT(*) AS TOTAL_USERS FROM USERS";
		 preparedStatement = connection.prepareStatement(sql);
		 resultSet=preparedStatement.executeQuery();
		 if(resultSet.next()) {
			 totalUsers = resultSet.getInt("TOTAL_USERS");
		 }
		 arr[0]=totalUsers;arr[1]=activeUsers;
		 return arr;
	 }

	
}
