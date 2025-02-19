package com.example.payments.dao;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CurrencyEntity {

	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String name;
}
