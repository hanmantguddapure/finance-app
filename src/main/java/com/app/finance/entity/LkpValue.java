package com.app.finance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
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
public class LkpValue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long lkpValueId;
	@OneToMany
	@PrimaryKeyJoinColumn
	private LkpType lkpTypeName;
	private String lkpValue;
}
