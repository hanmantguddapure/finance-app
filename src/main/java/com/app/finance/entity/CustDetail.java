package com.app.finance.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Customer_Detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long custId;
	@Column(unique = true)
	private String fullName;
	private String shortName;
	private String profession;
	private String panNo;
	private String adharNo;
	private LocalDate lastUpdate;
	@Builder.Default
	private Boolean isDeleted = false;
}
