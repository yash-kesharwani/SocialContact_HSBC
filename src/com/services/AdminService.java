package com.services;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.beans.Admin;
import com.beans.User;
import com.exceptions.UserNotFoundException;

public interface AdminService
{
	public boolean adminLogin(String username, String password);
	public int[] getSystemInfo() throws SQLException;
	public Map<Integer, String> getToDisable()throws UserNotFoundException, SQLException;
	public Map<Integer, String> getToDelete()throws UserNotFoundException,SQLException;
	public boolean disableUser(int userId, int status, int blockCount)throws UserNotFoundException, SQLException;
	public boolean deleteUser(int userId)throws UserNotFoundException,SQLException;
	public Admin getAdminInfo();
}
