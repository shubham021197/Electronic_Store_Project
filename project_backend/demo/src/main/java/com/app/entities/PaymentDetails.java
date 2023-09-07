package com.app.entities;


import java.sql.*;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetails extends BaseEntity
{

	//id (pk)	order_id (fk)	status	amount
	
	
	@OneToOne
	private OrderDetails order;//one to one
	
	@Column(length = 20, nullable = false)
	private String status;
	
	@Column(length = 20, nullable = false)
	private double amount;
	
}
