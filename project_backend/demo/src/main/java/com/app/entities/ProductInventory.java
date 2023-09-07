package com.app.entities;

import java.sql.*;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
public class ProductInventory extends BaseEntity {
	
	//product_id (fk)	quantity									
	@OneToOne
	ProductDetails product;
	
	//@NotBlank(message = "Quantity is required")
	@Column(nullable = false)
	private double quantity;
	
	
}
