package com.app.entities;

import java.sql.*;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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


public class OrderItems extends BaseEntity
{
	//id	order_id (fk)	book_id (fk)	quantity

	
	@ManyToOne
	private OrderDetails order;//many to one
	
	@OneToOne
	@JoinColumn(name="product_id")
	private ProductDetails product;//one to many
	
	
	@Column(length = 20, nullable = false)
	private int quantity;
	
	
	
}
