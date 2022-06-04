package com.app.finance.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "userCredential")
public class UserCredential {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private Long userId;
	@Column(name = "userName", length = 20, unique = true)
	private String userName;
	@Column(name = "password")
	private String password;
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private UserProfile profileId;
	@Column(name = "token")
	private Integer token;
	private LocalTime tokenTime;
	private LocalDateTime lastLoginTime;
	private String loggedInUserIpAddress;

	public Long getUserId() {
		return userId;
	}

	public LocalTime getTokenTime() {
		return tokenTime;
	}

	public void setTokenTime(LocalTime tokenTime) {
		this.tokenTime = tokenTime;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Integer getToken() {
		return token;
	}

	public void setToken(Integer token) {
		this.token = token;
	}

	public LocalDateTime getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(LocalDateTime lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLoggedInUserIpAddress() {
		return loggedInUserIpAddress;
	}

	public void setLoggedInUserIpAddress(String loggedInUserIpAddress) {
		this.loggedInUserIpAddress = loggedInUserIpAddress;
	}

	public UserProfile getProfileId() {
		return profileId;
	}

	public void setProfileId(UserProfile profileId) {
		this.profileId = profileId;
	}

}