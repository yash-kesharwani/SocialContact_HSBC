package com.services;

import java.sql.SQLException;
import java.util.List;

import com.beans.Address;
import com.beans.User;
import com.exceptions.FriendNotFoundException;
import com.exceptions.UserNotFoundException;

public interface FriendService 
{
	public User getFriendInfo(int friendId, int userId) throws FriendNotFoundException,SQLException;
	public User searchUser(String fullName)throws UserNotFoundException,SQLException;
	public boolean sendFriendRequest(int userId,int friendId)throws SQLException;
	public boolean acceptFriendRequest(int userId,int friendId)throws SQLException;
	public boolean blockFriend(int userId,int friendId)throws SQLException;
	public boolean unblockFriend(int userId,int friendId)throws SQLException;
	public boolean deleteFriend(int userId,int friendId)throws SQLException;
	public List<User> showBlockedFriends(int userId)throws SQLException;
	boolean checkBlocked(int userId,int friendId)throws SQLException;
	boolean checkRequested(int userId,int friendId)throws SQLException;
	
	
}
