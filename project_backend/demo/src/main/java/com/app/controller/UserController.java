package com.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.CartDTO;
import com.app.dto.CheckoutDto;
import com.app.dto.DataResponse;
import com.app.dto.EditResponse;
import com.app.dto.ErrorResponse;
import com.app.dto.OrderDetailsDto;
import com.app.dto.UserAddressDTO;
import com.app.service.ICartService;
import com.app.service.IOrderDetailsService;
import com.app.service.IOrderItemsService;
import com.app.service.IUserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private ICartService cartService;
	
	@Autowired
	private IOrderItemsService orderItemsService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IOrderDetailsService orderDetailsService;

	@PostMapping("/addToCart")
	public ResponseEntity<?> addToCart(@RequestBody CartDTO cart) throws IOException {
		// System.out.println("Inside addToCart : "+cart.getQuantity());
		try {
			return ResponseEntity.status(HttpStatus.CREATED).
					body(cartService.addProductToCart(cart));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while adding product to cart", e.toString()));
		}

	}
	
	@GetMapping("/viewCart/{userId}")
	public ResponseEntity<?> viewCart(@PathVariable long userId) throws IOException {

		try {
			System.out.println("in view cart ");
			return ResponseEntity.status(HttpStatus.OK).
					body(new DataResponse("success", cartService.viewProductsFromCart(userId)));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while adding product to cart", e.toString()));
		}
	}
	
	@PutMapping("/cart/incrementProductQuantity/{cartId}/{quantity}")
	public ResponseEntity<?> incrementProductQuantity(@PathVariable long cartId, @PathVariable int quantity) throws IOException {
		try {
			return ResponseEntity.status(HttpStatus.OK).
					body(cartService.incrementProductQuantityInCart(cartId, quantity));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while increasing product quantity", e.toString()));
		}

	}
	
	@GetMapping("/getOrderDetails/{orderDetailsId}")
	public ResponseEntity<?> getOrderDetails(@PathVariable long orderDetailsId) throws IOException {
		try {
			return ResponseEntity.status(HttpStatus.OK).
					body(new DataResponse("success",orderItemsService.getOrderItems(orderDetailsId)));

		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while fetching order details", e.toString()));
		}

	}
	
	@PostMapping("/addNewAddress")
	public ResponseEntity<?> addNewAddress(@RequestBody UserAddressDTO address) {
		System.out.println("add new address" + address);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.insertAddress(address));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while getting address", e.toString()));
		}
		
	}
	
	@GetMapping("/getAddress/{userId}")
	public ResponseEntity<?> getAddress(@PathVariable Long userId){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(new DataResponse("success", userService.getUserAddress(userId)));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while getting address", e.toString()));
		}
	}
	
	@PostMapping("/checkout")
	public ResponseEntity<?> checkout(@RequestBody CheckoutDto checkout) throws IOException {
				System.out.println("inside checkout");
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(new EditResponse("success", orderDetailsService.placeOrder(checkout)));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while adding book to wishlist", e.toString()));
		}

	}
	
	@GetMapping("/viewOrderHistory/{userId}")
	public ResponseEntity<?> viewOrderHistory(@PathVariable long userId) throws IOException {

		try {
			System.out.println("in view all orders " + userId);
			return ResponseEntity.status(HttpStatus.OK).
					body(new DataResponse("success", orderDetailsService.getOrdersByUser(userId)));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while fetching order history", e.toString()));
		}
	}
	
	@PutMapping("/orders/updateStatus")
	public ResponseEntity<?> cancelOrder(@RequestBody OrderDetailsDto orderDto) {

		try {
			return ResponseEntity.status(HttpStatus.OK).
					body(new EditResponse("success",orderDetailsService.updateOrderStatus(orderDto.getId(), "CANCELLED")));

			// o.s.http.ResponseEntity(T body,HttpStatus stsCode)
//			return new ResponseEntity<>(orderDetailsService.updateOrderStatus(orderDto.getId(), "CANCELLED"),
//					HttpStatus.OK);
		} catch (RuntimeException e) {
			System.out.println("err in user controller " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
		}

	}

}
