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
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

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
public class DiscountDetails extends BaseEntity {
	
	//id	coupon_code	status 	valid_date	created_at	percentage					
							

	@NotBlank(message = "Coupon code is required")
	@Column(nullable = false)
	private String couponCode;
	
	@NotBlank(message = "Status is required")
	@Column(nullable = false)
	private String status;
	
	@Column(nullable = false)
	private Date validDate;
	
	@Column(nullable = false)
	private Double percentage;
	
	
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdAt;
	
	
}
