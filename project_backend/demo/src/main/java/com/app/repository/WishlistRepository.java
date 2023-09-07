package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.CartItems;
import com.app.entities.UserEntity;
import com.app.entities.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

	Wishlist findByUser(UserEntity user);
}
