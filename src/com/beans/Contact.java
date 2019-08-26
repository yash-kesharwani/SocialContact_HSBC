package com.beans;

import java.sql.Date;

public class Contact 
{
	private int cId;
	private String fullName;
	private String email;
	private long phone;
	private char gender;
	private Date dob;
	private Address contactAddress;
	private String company;
	private String picture;
	private int userId;
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Contact(int cId, String fullName, String email, long phone, char gender, Date dob,Address contactAddress, String company,
			String picture, int userId) {
		super();
		this.cId = cId;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.dob = dob;
		this.contactAddress=contactAddress;
		this.company = company;
		this.picture = picture;
		this.userId = userId;
	}
	
	public Address getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(Address contactAddress) {
		this.contactAddress = contactAddress;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Contact [cId=" + cId + ", fullName=" + fullName + ", email=" + email + ", phone=" + phone + ", gender="
				+ gender + ", dob=" + dob + ", company=" + company + ", picture=" + picture + ", userId=" + userId + "address=" +contactAddress+
				 "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cId;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((contactAddress == null) ? 0 : contactAddress.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + gender;
		result = prime * result + (int) (phone ^ (phone >>> 32));
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + userId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (cId != other.cId)
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (contactAddress == null) {
			if (other.contactAddress != null)
				return false;
		} else if (!contactAddress.equals(other.contactAddress))
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (gender != other.gender)
			return false;
		if (phone != other.phone)
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
	
}
