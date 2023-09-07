package com.app.dto;

import javax.validation.constraints.NotBlank;
import com.app.entities.UserEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartDTO {
	
	private int quantity;
	
	//@NotBlank(message = "user is required")
	private long userId;
	
	//@NotBlank(message = "product is required")
	private long productId;
	
	
}
