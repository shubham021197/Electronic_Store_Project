package com.app.dto;

import java.sql.Date;

import javax.validation.constraints.NotBlank;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDetailsDto {

	//@NotBlank(message = "Order status cannot be blank or null")
	private Long id;
	private Date createdAt;
	private String status;
	private Date statusUpdatedAt;
	
}
