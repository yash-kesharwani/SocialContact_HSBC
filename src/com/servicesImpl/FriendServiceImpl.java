package com.servicesImpl;

import java.sql.SQLException;
import java.util.List;

import com.beans.Address;
import com.beans.User;
import com.dao.FriendDao;
import com.daoImpl.FriendDaoImpl;
import com.exceptions.FriendNotFoundException;
import com.exceptions.UserNotFoundException;
import com.services.FriendService;

public class FriendServiceImpl implements FriendService
{

	FriendDao fd=null;
	public FriendServiceImpl()
	{
		fd=new FriendDaoImpl();
	}
	@Override
	public User getFriendInfo(int friendId, int userId) throws FriendNotFoundException, SQLException {
		
		return fd.getFriendInfo(userId, friendId);
	}

	@Override
	public User searchUser(String fullName) throws UserNotFoundException, SQLException {
		
		return fd.searchUserByName(fullName);
	}

	@Override
	public boolean sendFriendRequest(int userId,int friendId) throws SQLException
	{
		if(!checkBlocked(userId,friendId))
			if(!checkRequested(userId, friendId))
				return fd.sendFriendRequest(userId, friendId);
		return false;
	}

	@Override
	public boolean acceptFriendRequest(int userId,int friendId) throws SQLException{
		
		return fd.acceptFriendRequest(userId, friendId);
	}

	@Override
	public boolean blockFriend(int userId,int friendId) throws SQLException {
	
		return fd.blockFriend(userId, friendId);
	}

	@Override
	public boolean unblockFriend(int userId,int friendId) throws SQLException {
		
		return fd.unblockFriend(userId, friendId);
	}

	@Override
	public boolean deleteFriend(int userId,int friendId) throws SQLException {
		
		return fd.deleteFriend(userId, friendId);
	}

	@Override
	public List<User> showBlockedFriends(int userId) throws SQLException {
		
		return fd.showBlockedFriends(userId);
	}

	@Override
	public boolean checkBlocked(int userId,int friendId) throws SQLException {
		
		return fd.checkBlocked(userId, friendId);
	}

	@Override
	public boolean checkRequested(int userId,int friendId) throws SQLException {
		
		return fd.checkRequested(userId, friendId);
	}

}
