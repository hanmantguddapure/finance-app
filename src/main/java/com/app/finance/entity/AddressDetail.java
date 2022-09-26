package com.app.finance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "address_details")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long addressId;
	private Long addressRefId;
	@ManyToOne
	@JoinColumn(name = "lkp_value_id")
	private LkpValue addressType;
	private String nativePlace;
	private String address;
	/*
	 * @ManyToOne
	 * 
	 * @PrimaryKeyJoinColumn private LkpValue city;
	 * 
	 * @ManyToOne
	 * 
	 * @PrimaryKeyJoinColumn private LkpValue district;
	 * 
	 * @ManyToOne
	 * 
	 * @PrimaryKeyJoinColumn private LkpValue state;
	 * 
	 * @ManyToOne
	 * 
	 * @PrimaryKeyJoinColumn private LkpValue country;
	 */
	private Integer zipCode;
	private String email;
	private String stdCode;
	private String phoneNo1;
	private String phoneNo2;
}
