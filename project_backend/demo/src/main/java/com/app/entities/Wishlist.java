package com.app.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString

public class Wishlist extends BaseEntity {
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private UserEntity user; 
	

	// Wishlist 1----->* Products
//	@ElementCollection 
//	@CollectionTable(name = "wishlist_products", joinColumns = @JoinColumn(name = "wishlist_id")) 																			// the coll table + FK col name
//	@OneToMany(fetch = FetchType.EAGER)
	@ManyToMany
	@Column
	private Set<ProductDetails> products = new HashSet<>();

	public Wishlist()
	{
		
	}

	public void addProduct(ProductDetails product) {
		products.add(product);
	}

	public void removeProduct(ProductDetails product) {
		products.remove(product);
	}
	
	public Set<ProductDetails> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductDetails> products) {
		this.products = products;
	}


}
