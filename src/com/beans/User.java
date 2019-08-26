package com.beans;

import java.sql.Date;
import java.util.List;

public class User
{
	private int userId;
	private String fullName;
	private String email;
	private long phone;
	private char gender;
	private Date dob;
	private Address userAddress;
	private String company;
	private String picture;
	private int status;
	private int blockCount;
	private Date lastActive;
	private List<Friend> friendList;
	private List<Contact> contactList;
	private Credential credential;
		
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int userId, String fullName, String email, long phone, char gender, Date dob, Address userAddress,
			String company, String picture, int status, int blockCount, Date lastActive, List<Friend> friendList,
			List<Contact> contactList, Credential credential) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.dob = dob;
		this.userAddress = userAddress;
		this.company = company;
		this.picture = picture;
		this.status = status;
		this.blockCount = blockCount;
		this.lastActive = lastActive;
		this.friendList = friendList;
		this.contactList = contactList;
		this.credential=credential;
	}

	public User(int userId, String fullName, String email, long phone, char gender, Date dob, Address userAddress,
			String company, String picture, int status, int blockCount, Date lastActive) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.dob = dob;
		this.userAddress = userAddress;
		this.company = company;
		this.picture = picture;
		this.status = status;
		this.blockCount = blockCount;
		this.lastActive = lastActive;
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Address getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(Address userAddress) {
		this.userAddress = userAddress;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getBlockCount() {
		return blockCount;
	}

	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}

	public Date getLastActive() {
		return lastActive;
	}

	public void setLastActive(Date lastActive) {
		this.lastActive = lastActive;
	}

	public List<Friend> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<Friend> friendList) {
		this.friendList = friendList;
	}

	public List<Contact> getContactList() {
		return contactList;
	}

	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", fullName=" + fullName + ", email=" + email + ", phone=" + phone
				+ ", gender=" + gender + ", dob=" + dob + ", userAddress=" + userAddress + ", company=" + company
				+ ", picture=" + picture + ", status=" + status + ", blockCount=" + blockCount + ", lastActive="
				+ lastActive + ", friendList=" + friendList + ", contactList=" + contactList + ", credential="
				+ credential + "]";
	}
	
	
	
	
	
}
