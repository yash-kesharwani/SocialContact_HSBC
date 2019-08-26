package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.beans.Address;
import com.beans.User;
import com.beans.Friend;
import com.exceptions.FriendNotFoundException;
import com.exceptions.UserNotFoundException;

public interface FriendDao {

	List<User> getFriendList(int userId) throws SQLException;

	User getFriendInfo(int userId, int friendId) throws FriendNotFoundException, SQLException;

	User searchUserByName(String fullName) throws UserNotFoundException, SQLException;

	boolean sendFriendRequest(int userId, int friendId) throws SQLException;

	boolean acceptFriendRequest(int userId, int friendId) throws SQLException;

	boolean blockFriend(int userId, int friendId)throws SQLException;

	boolean unblockFriend(int userId, int friendId) throws SQLException;

	boolean deleteFriend(int userId, int friendId) throws SQLException;

	List<User> showBlockedFriends(int userId) throws SQLException;

	boolean checkBlocked(int userId, int friendId) throws SQLException;

	boolean checkRequested(int userId, int friendId) throws SQLException;

	boolean checkUnBlocked(int userId, int friendId) throws SQLException;

	boolean isFriend(int userId, int friendId) throws SQLException;
	
}
