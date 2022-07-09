package com.app.finance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
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
	@OneToMany
	@PrimaryKeyJoinColumn
	private LkpValue addressType;
	private String nativePlace;
	private String address;
	@OneToMany
	@PrimaryKeyJoinColumn
	private Long city;
	@OneToMany
	@PrimaryKeyJoinColumn
	private Long district;
	@OneToMany
	@PrimaryKeyJoinColumn
	private Long state;
	@OneToMany
	@PrimaryKeyJoinColumn
	private Long country;
	private Integer zipCode;
	private String email;
	private String stdCode;
	private String phoneNo1;
	private String phoneNo2;
}
