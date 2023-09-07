package com.app.dto;

import javax.validation.constraints.NotBlank;

import com.app.entities.UserEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WishListDTO {
	
	private long userId;
	private long productId;
	
	
}
