package com.app.finance.dto;

import java.io.Serializable;

public class AuthDetailsDto implements Serializable {

	private static final long serialVersionUID = -1014193388339967757L;
	private String userName;
	private String password;
	private String fullName;
	private int token;
	private String status;
	private String jwtToken;
	private Long roleId;
	private Long userId;
	private String loggedInUserIpAddress;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoggedInUserIpAddress() {
		return loggedInUserIpAddress;
	}

	public void setLoggedInUserIpAddress(String loggedInUserIpAddress) {
		this.loggedInUserIpAddress = loggedInUserIpAddress;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
