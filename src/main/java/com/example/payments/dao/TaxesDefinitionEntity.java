package com.example.payments.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TaxesDefinitionEntity {

	@Id
	@GeneratedValue
	private Long id;
	@Column
	private BigDecimal percentage;


}
