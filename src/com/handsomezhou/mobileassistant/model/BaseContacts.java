package com.handsomezhou.mobileassistant.model;

public class BaseContacts {
	private String mId;
	private String mName;
	private String mPhoneNumber;

	public BaseContacts() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return mId;
	}
	
	public void setId(String id) {
		mId = id;
	}
	
	public String getName() {
		return mName;
	}
	
	public void setName(String name) {
		mName = name;
	}
	
	public String getPhoneNumber() {
		return mPhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		mPhoneNumber = phoneNumber;
	}
}
