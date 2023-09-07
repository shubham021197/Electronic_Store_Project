package com.app.dto;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

import com.app.entities.UserEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SalesDTO {
	
	private Date startDate;
	private Date endDate;

}
