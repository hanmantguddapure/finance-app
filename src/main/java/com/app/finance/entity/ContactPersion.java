package com.app.finance.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contact_persions")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactPersion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contactPersionId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cust_id")
	private CustDetail custId;
	private String fullName;
	private String address;
	private String profession;
	private String email;
	private String phone1;
}
