package com.app.finance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LkpType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long lkpTypeId;
	private String lkpTypeName;
}
