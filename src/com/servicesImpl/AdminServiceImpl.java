package com.servicesImpl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import com.beans.Admin;
import com.dao.AdminDao;
import com.daoImpl.AdminDaoImpl;
import com.exceptions.UserNotFoundException;
import com.services.AdminService;

public class AdminServiceImpl implements AdminService
{
	AdminDao ad=new AdminDaoImpl();
	
	Admin admin=new Admin();
	public AdminServiceImpl()
	{
		Properties p=new Properties();
		FileReader reader;
		try {
			reader = new FileReader("admin.properties");
			p.load(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		admin.setAdminName(p.getProperty("adminName"));
		admin.setAdminEmail(p.getProperty("adminEmail"));
		admin.setAdminPhone(Long.parseLong(p.getProperty("adminPhone")));
		admin.setAdminUsername(p.getProperty("adminUsername"));
		admin.setAdminPassword(p.getProperty("adminPassword"));
	}
	@Override
	public boolean adminLogin(String username, String password)
	{
		if(admin.getAdminUsername()==username && admin.getAdminPassword()==password)
			return true;
		return false;
	}

	
	public int[] getSystemInfo() throws SQLException
	{
		return ad.getSystemInfo();

	}

	@Override
	public Map<Integer,String> getToDisable() throws UserNotFoundException, SQLException {
		
		return ad.getToDisable();
	}

	@Override
	public Map<Integer, String> getToDelete() throws UserNotFoundException, SQLException
	{
		
		Map<Integer,String>local=ad.getToDelete();
		Map<Integer,String>toDelete=null;
		LocalDate lastActive=null, now=LocalDate.now();
		int days=0;
		for(Map.Entry<Integer, String> entry: local.entrySet())
		{
			lastActive=ad.getLastActive(entry.getKey()).toLocalDate();
			days= Period.between(lastActive, now).getDays();
            if(days>30)
            	toDelete.put(entry.getKey(), entry.getValue());
		}
		return toDelete;	
	}

	@Override
	public boolean disableUser(int userId, int status, int blockCount) throws UserNotFoundException, SQLException {
		if(blockCount>3)
		{
			return ad.disableUser(userId);
		}
		return false;
	}

	@Override
	public boolean deleteUser(int userId) throws UserNotFoundException, SQLException 
	{
		
		return ad.deleteUser(userId);
	}
	public Admin getAdminInfo()
	{
		return admin;
	}

}
