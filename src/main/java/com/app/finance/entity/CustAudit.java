package com.app.finance.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customer_audit")
public class CustAudit implements Serializable {

	private static final long serialVersionUID = -1373778567616323514L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long custAuditId;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cust_id")
	@JsonIgnore
	private CustDetail customer;
	private String loggedInUserId;
	private String userName;
	private String userDesignation;
	private LocalDateTime createdOnDateTime;
	private String lastUpdatedUserId;
	private String lastUpdatedUserName;
	private String lastUpdatedUserDesignation;
	private LocalDateTime lastUpdatedDatetime;
	private String action;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public LocalDateTime getLastUpdatedDatetime() {
		return lastUpdatedDatetime;
	}

	public void setLastUpdatedDatetime(LocalDateTime lastUpdatedDatetime) {
		this.lastUpdatedDatetime = lastUpdatedDatetime;
	}

	public String getLastUpdatedUserId() {
		return lastUpdatedUserId;
	}

	public String getLastUpdatedUserName() {
		return lastUpdatedUserName;
	}

	public String getLastUpdatedUserDesignation() {
		return lastUpdatedUserDesignation;
	}

	public void setLastUpdatedUserId(String lastUpdatedUserId) {
		this.lastUpdatedUserId = lastUpdatedUserId;
	}

	public void setLastUpdatedUserName(String lastUpdatedUserName) {
		this.lastUpdatedUserName = lastUpdatedUserName;
	}

	public void setLastUpdatedUserDesignation(String lastUpdatedUserDesignation) {
		this.lastUpdatedUserDesignation = lastUpdatedUserDesignation;
	}

	public Long getCustAuditId() {
		return custAuditId;
	}

	public void setCustAuditId(Long custAuditId) {
		this.custAuditId = custAuditId;
	}

	public CustDetail getCustomer() {
		return customer;
	}

	public void setCustomer(CustDetail customer) {
		this.customer = customer;
	}

	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserDesignation() {
		return userDesignation;
	}

	public void setUserDesignation(String userDesignation) {
		this.userDesignation = userDesignation;
	}

	public LocalDateTime getCreatedOnDateTime() {
		return createdOnDateTime;
	}

	public void setCreatedOnDateTime(LocalDateTime createdOnDateTime) {
		this.createdOnDateTime = createdOnDateTime;
	}

}
