package com.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.beans.Address;
import com.beans.Friend;
import com.beans.User;
import com.dao.FriendDao;
import com.exceptions.FriendNotFoundException;
import com.exceptions.UserNotFoundException;
import com.services.UserService;

public class FriendDaoImpl implements FriendDao {

	private final static String conURL = "jdbc:derby:C:\\Users\\Fiona\\Documents\\workspace-sts-3.9.9.RELEASE\\derby\\mydb;create = true";
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private PreparedStatement preparedStatement_2 = null;

	private ResultSet resultSet = null;
	private ResultSet resultSet_2 = null;

	public FriendDaoImpl() {
		
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
	public List<User> getFriendList(int userId) throws SQLException{
		preparedStatement = connection.prepareStatement("SELECT FRIEND_ID FROM FRIENDSHIP WHERE USER_ID = ? AND STATUS = ?");
		preparedStatement_2 = connection.prepareStatement("SELECT * FROM USERS WHERE USER_ID = ?");
		
		ArrayList<Integer> friendIds = new ArrayList<Integer>();
		List<User> friendList = new ArrayList<User>();
		preparedStatement.setInt(1, userId);
		preparedStatement.setInt(2, 1);
		
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			friendIds.add(resultSet.getInt("FRIEND_ID"));
		}
		
		preparedStatement = connection.prepareStatement("SELECT USER_ID FROM FRIENDSHIP WHERE FRIEND_ID = ? AND STATUS = ?");
		preparedStatement.setInt(1, userId);
		preparedStatement.setInt(2, 1);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			friendIds.add(resultSet.getInt("USER_ID"));
		}
		
		
		for(Integer id: friendIds) {
			preparedStatement_2.setInt(1, id);
			resultSet_2 = preparedStatement_2.executeQuery();
			if(resultSet_2.next()) 
				friendList.add(new User(resultSet_2.getInt("USER_ID"),resultSet_2.getString("FULL_NAME"), resultSet_2.getString("EMAIL"),resultSet_2.getLong("PHONE_NUMBER"),(resultSet_2.getString("GENDER").charAt(0)),resultSet_2.getDate("DOB"),getAdress(id), resultSet_2.getString("COMPANY"), resultSet_2.getString("PROFILE_PICTURE"),resultSet_2.getInt("STATUS"),resultSet_2.getInt("BLOCK_COUNT"), resultSet_2.getDate("LAST_ACTIVE")));
		}
		
		return friendList;
		
	}
	
	private Address getAdress(int userId) throws SQLException
	{
		String sql="SELECT * FROM USERS_ADDRESS WHERE USER_ID = ?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1,userId);
		resultSet=preparedStatement.executeQuery();
		
		if(resultSet.next())
		{
			return new Address(resultSet.getInt("AID"), resultSet.getString("CITY"),resultSet.getString("STATE"),resultSet.getString("COUNTRY"),resultSet.getInt("PINCODE"), userId);
		}
		return null;
	}

	@Override
	public User getFriendInfo(int userId, int friendId) throws FriendNotFoundException,SQLException {
		// TODO Auto-generated method stub
		preparedStatement = connection.prepareStatement("SELECT * FROM FRIENDSHIP WHERE USER_ID = ? AND FRIEND_ID = ?");
		preparedStatement_2 = connection.prepareStatement("SELECT * FROM USERS WHERE USER_ID = ?");
		preparedStatement.setInt(1, userId);
		preparedStatement.setInt(2, friendId);
		resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
			preparedStatement_2.setInt(1, resultSet.getInt("FRIEND_ID"));
			resultSet_2 = preparedStatement_2.executeQuery();
			if(resultSet_2.next())
				return new User(resultSet_2.getInt("USER_ID"),resultSet_2.getString("FULL_NAME"), resultSet_2.getString("EMAIL"), 
					resultSet_2.getLong("PHONE_NUMBER"),(resultSet_2.getString("GENDER").charAt(0)),
					resultSet_2.getDate("DOB"),getAdress(resultSet.getInt("FRIEND_ID")), resultSet_2.getString("COMPANY"), resultSet_2.getString("PROFILE_PICTURE"),resultSet_2.getInt("STATUS"),
					resultSet_2.getInt("BLOCK_COUNT"), resultSet_2.getDate("LAST_ACTIVE"));
		}
		
		preparedStatement = connection.prepareStatement("SELECT * FROM FRIENDSHIP WHERE FRIEND_ID = ? AND USER_ID = ?");
		preparedStatement.setInt(1, userId);
		preparedStatement.setInt(2, friendId);
		resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
			preparedStatement_2.setInt(1, resultSet.getInt("FRIEND_ID"));
			resultSet_2 = preparedStatement_2.executeQuery();
			if(resultSet_2.next())
				return new User(resultSet_2.getInt("USER_ID"),resultSet_2.getString("FULL_NAME"), resultSet_2.getString("EMAIL"), 
					resultSet_2.getLong("PHONE_NUMBER"),(resultSet_2.getString("GENDER").charAt(0)),
					resultSet_2.getDate("DOB"),getAdress(resultSet.getInt("FRIEND_ID")), resultSet_2.getString("COMPANY"), resultSet_2.getString("PROFILE_PICTURE"),resultSet_2.getInt("STATUS"),
					resultSet_2.getInt("BLOCK_COUNT"), resultSet_2.getDate("LAST_ACTIVE"));
		}
		
		
		
		return null;
	}

	@Override
	public User searchUserByName(String fullName) throws UserNotFoundException, SQLException {
		// TODO Auto-generated method stub
		preparedStatement = connection.prepareStatement("SELECT * FROM USERS WHERE FULL_NAME = ?");
		preparedStatement.setString(1, fullName);
		resultSet_2 = preparedStatement.executeQuery();
		if(resultSet_2.next()) 
		{
			return new User(resultSet_2.getInt("USER_ID"),resultSet_2.getString("FULL_NAME"), resultSet_2.getString("EMAIL"),resultSet_2.getLong("PHONE_NUMBER"),(resultSet_2.getString("GENDER").charAt(0)),resultSet_2.getDate("DOB"),getAdress(resultSet_2.getInt("USER_ID")), resultSet_2.getString(7), resultSet_2.getString("PROFILE_PICTURE"),resultSet_2.getInt("STATUS"),resultSet_2.getInt("BLOCK_COUNT"), resultSet_2.getDate("LAST_ACTIVE"));
	
		}
		return null;
	}

	@Override
	public boolean sendFriendRequest(int userId,int friendId)throws SQLException{
		// TODO Auto-generated method stub
		preparedStatement=connection.prepareStatement("INSERT INTO FRIENDSHIP VALUES(?,?,?)");
		preparedStatement.setInt(1, userId);
		preparedStatement.setInt(2, friendId);
		preparedStatement.setInt(3, 0);
		int status=preparedStatement.executeUpdate();
		if(status==1)
			return true;
				
		return false;
	}

	@Override
	public boolean acceptFriendRequest(int userId,int friendId)throws SQLException {
		if(checkRequested(userId, friendId)) {
			preparedStatement=connection.prepareStatement("UPDATE FRIENDSHIP SET STATUS=? WHERE USER_ID=? AND FRIEND_ID=?");
			preparedStatement.setInt(1, 1);
			preparedStatement.setInt(2, userId);
			preparedStatement.setInt(3, friendId);
			int status=preparedStatement.executeUpdate();
			if(status==1)
				return true;
		}
		else if(checkRequested(friendId, userId)) {
			preparedStatement=connection.prepareStatement("UPDATE FRIENDSHIP SET STATUS=? WHERE FRIEND_ID=? AND USER_ID=?");
			preparedStatement.setInt(1, 1);
			preparedStatement.setInt(2, userId);
			preparedStatement.setInt(3, friendId);
			int status=preparedStatement.executeUpdate();
			if(status==1)
				return true;
		}
		return false;
	}

	@Override
	public boolean blockFriend(int userId,int friendId)throws SQLException {
		if(checkUnBlocked(userId, friendId)) {
			System.out.println("Inside if");
			preparedStatement=connection.prepareStatement("UPDATE FRIENDSHIP SET STATUS=? WHERE USER_ID=? AND FRIEND_ID=?");
			preparedStatement.setInt(1, 2);
			preparedStatement.setInt(2, userId);
			preparedStatement.setInt(3, friendId);
			int status=preparedStatement.executeUpdate();
			if(status==1)
			{
				preparedStatement_2 = connection.prepareStatement("SELECT BLOCK_COUNT FROM USERS WHERE USER_ID = ?");
				preparedStatement_2.setInt(1, friendId);
				resultSet = preparedStatement_2.executeQuery();
				if(resultSet.next()) {
					int block_count = resultSet.getInt("BLOCK_COUNT");
					preparedStatement = connection.prepareStatement("UPDATE USERS SET BLOCK_COUNT = ? WHERE USER_ID = ?");
					preparedStatement.setInt(1, block_count+1);
					preparedStatement.setInt(2, friendId);
					if(preparedStatement.executeUpdate() == 1)
						return true;
					return false;
				}
				return false;
			}
		}
		else if(checkUnBlocked(friendId, userId)) {
			System.out.println("Inside else");
			preparedStatement=connection.prepareStatement("UPDATE FRIENDSHIP SET STATUS=? WHERE USER_ID=? AND FRIEND_ID=?");
			preparedStatement.setInt(1, 2);
			preparedStatement.setInt(2, friendId);
			preparedStatement.setInt(3, userId);
			int status=preparedStatement.executeUpdate();
			if(status==1)
			{
				
				preparedStatement_2 = connection.prepareStatement("SELECT BLOCK_COUNT FROM USERS WHERE USER_ID = ?");
				preparedStatement_2.setInt(1, userId);
				resultSet = preparedStatement_2.executeQuery();
				if(resultSet.next()) {
					int block_count = resultSet.getInt("BLOCK_COUNT");
					preparedStatement = connection.prepareStatement("UPDATE USERS SET BLOCK_COUNT = ? WHERE USER_ID = ?");
					preparedStatement.setInt(1, block_count+1);
					preparedStatement.setInt(2, userId);
					if(preparedStatement.executeUpdate() == 1)
						return true;
					return false;
				}
				return false;
				
			}
		}
		return false;
	}

	@Override
	public boolean unblockFriend(int userId,int friendId)throws SQLException {
		if(checkBlocked(userId, friendId)) {
			preparedStatement=connection.prepareStatement("UPDATE FRIENDSHIP SET STATUS=? WHERE USER_ID=? AND FRIEND_ID=?");
			preparedStatement.setInt(1, 1);
			preparedStatement.setInt(2, userId);
			preparedStatement.setInt(3, friendId);
			int status=preparedStatement.executeUpdate();
			if(status==1)
				return true;
		}else if(checkBlocked(friendId, userId)) {
			preparedStatement=connection.prepareStatement("UPDATE FRIENDSHIP SET STATUS=? WHERE USER_ID=? AND FRIEND_ID=?");
			preparedStatement.setInt(1, 1);
			preparedStatement.setInt(2, friendId);
			preparedStatement.setInt(3, userId);
			int status=preparedStatement.executeUpdate();
			if(status==1)
				return true;
		}
		return false;
	}

	@Override
	public boolean isFriend(int userId, int friendId) throws SQLException{
		preparedStatement=connection.prepareStatement("SELECT STATUS FROM FRIENDSHIP WHERE USER_ID=? AND FRIEND_ID=?");
		preparedStatement.setInt(1, userId);
		preparedStatement.setInt(2, friendId);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next()) {
			if(resultSet.getInt("STATUS")==1)
				return true;
		}
		return false;
	}
	@Override
	public boolean deleteFriend(int userId,int friendId)throws SQLException {
		if(isFriend(userId, friendId)) {
			preparedStatement=connection.prepareStatement("DELETE FROM FRIENDSHIP WHERE USER_ID=? AND FRIEND_ID=?");
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, friendId);
			int status=preparedStatement.executeUpdate();
			if(status==1)
				return true;
		}else if(isFriend(friendId, userId)) {
			preparedStatement=connection.prepareStatement("DELETE FROM FRIENDSHIP WHERE USER_ID=? AND FRIEND_ID=?");
			preparedStatement.setInt(1, friendId);
			preparedStatement.setInt(2, userId);
			int status=preparedStatement.executeUpdate();
			if(status==1)
				return true;
		}
		return false;
	}

	@Override
	public List<User> showBlockedFriends(int userId)throws SQLException{
		preparedStatement=connection.prepareStatement("SELECT FRIEND_ID FROM FRIENDSHIP WHERE USER_ID=? AND STATUS=?");
		preparedStatement.setInt(1, userId);
		preparedStatement.setInt(2, 2);
		preparedStatement_2 = connection.prepareStatement("SELECT * FROM USERS WHERE USER_ID = ?");
		ArrayList<Integer> friendIds = new ArrayList<Integer>();
		List<User> friendList = new ArrayList<User>();
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			friendIds.add(resultSet.getInt("FRIEND_ID"));
		}
		preparedStatement = connection.prepareStatement("SELECT USER_ID FROM FRIENDSHIP WHERE FRIEND_ID=? AND STATUS = ?");
		preparedStatement.setInt(1, userId);
		preparedStatement.setInt(2, 2);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			friendIds.add(resultSet.getInt("USER_ID"));
		}
		for(Integer id: friendIds) {
			preparedStatement_2.setInt(1, id);
			resultSet_2 = preparedStatement_2.executeQuery();
			if(resultSet_2.next())
				friendList.add(new User(resultSet_2.getInt("USER_ID"),resultSet_2.getString("FULL_NAME"), resultSet_2.getString("EMAIL"),resultSet_2.getLong("PHONE_NUMBER"),(resultSet_2.getString("GENDER").charAt(0)),resultSet_2.getDate("DOB"),getAdress(id), resultSet_2.getString("COMPANY"), resultSet_2.getString("PROFILE_PICTURE"),resultSet_2.getInt("STATUS"),resultSet_2.getInt("BLOCK_COUNT"), resultSet_2.getDate("LAST_ACTIVE")));
		}
		
		return friendList;
		
	}

	@Override
	public boolean checkBlocked(int userId,int friendId)throws SQLException {
		preparedStatement=connection.prepareStatement("SELECT STATUS FROM FRIENDSHIP WHERE USER_ID=? AND FRIEND_ID=?");
		preparedStatement.setInt(1, userId);
		preparedStatement.setInt(2, friendId);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next()) {
			if(resultSet.getInt("STATUS")==2)
				return true;
		}
		
		return false;
	}
	
	@Override
	public boolean checkUnBlocked(int userId,int friendId)throws SQLException {
		preparedStatement=connection.prepareStatement("SELECT STATUS FROM FRIENDSHIP WHERE USER_ID=? AND FRIEND_ID=?");
		preparedStatement.setInt(1, userId);
		preparedStatement.setInt(2, friendId);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next()) {
			if(resultSet.getInt("STATUS")!=2)
				return true;
		}
		
		return false;
	}


	@Override
	public boolean checkRequested(int userId,int friendId)throws SQLException {
		preparedStatement=connection.prepareStatement("SELECT STATUS FROM FRIENDSHIP WHERE USER_ID=? AND FRIEND_ID=?");
		preparedStatement.setInt(1, userId);
		preparedStatement.setInt(2, friendId);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next())
			if(resultSet.getInt("STATUS")==0)
				return true;
		return false;
	}

}
