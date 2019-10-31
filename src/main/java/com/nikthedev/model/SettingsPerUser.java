package com.nikthedev.model;

import org.bson.types.ObjectId;

public class SettingsPerUser {
	
	private ObjectId id;
	
	private String hexId;
	
	private int sessionTimeOut;
	
	private int pagination;
	
	private int userId;

	public int getSessionTimeOut() {
		return sessionTimeOut;
	}

	public void setSessionTimeOut(int sessionTimeOut) {
		this.sessionTimeOut = sessionTimeOut;
	}

	public int getPagination() {
		return pagination;
	}

	public void setPagination(int pagination) {
		this.pagination = pagination;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getHexId() {
		if (this.id != null) {
			this.hexId =  this.id.toHexString();			
		}
		return this.hexId;
	}
	
	public void setHexId(String hexId) {
		this.hexId = hexId;
	}
}
