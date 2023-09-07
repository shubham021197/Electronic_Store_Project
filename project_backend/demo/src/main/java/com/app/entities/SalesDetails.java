package com.app.entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = false)
public class SalesDetails extends BaseEntity {
	
//	@OneToOne
//	@JoinColumn(nullable = false)
//	private BookDetails book;	
	
	@OneToOne
	@JoinColumn(nullable = false)
	private ProductDetails product;
	
	@OneToOne
	@JoinColumn(nullable = false) 
	private ProductCategory category ;
	
	//private double totalSale;
	
	private Date soldOn;
	
	private int quantity;
		
}
