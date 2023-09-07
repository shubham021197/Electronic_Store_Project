package com.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.DataResponse;
import com.app.dto.EditResponse;
import com.app.dto.ErrorResponse;
import com.app.dto.OrderDetailsDto;
import com.app.dto.ProductDto;
import com.app.entities.DiscountDetails;
import com.app.entities.ProductCategory;
import com.app.service.IAdminService;
import com.app.service.IOrderDetailsService;
import com.app.service.IProductService;
import com.app.service.ImageHandlingService;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ImageHandlingService imageService;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private IOrderDetailsService orderDetailsService;
	
	@PostMapping("/addProduct")
	public ResponseEntity<?> addProductDetails(@ModelAttribute ProductDto product) throws IOException {
		System.out.println("Inside addProduct");
		System.out.println(product);
		System.out.println(product.getCategory());
		product.setImagePath(imageService.uploadImage(product.getImage()));
		return ResponseEntity.status(HttpStatus.CREATED).
				body(productService.addProductDetails(product));
	}
	
	@PostMapping("/addDiscount")
	public ResponseEntity<?> addDiscount(@RequestBody DiscountDetails discount)

	{
		System.out.println("add new discounts" + discount);

		return ResponseEntity.status(HttpStatus.CREATED).
				body(adminService.insertDiscount(discount));

	}
	
	@DeleteMapping("/deleteProduct/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(productId));
	}
	
	@PutMapping("/removeProduct/{productId}")
	public ResponseEntity<?> removeProduct(@PathVariable long productId) {

		try {
			// o.s.http.ResponseEntity(T body,HttpStatus stsCode)
			return ResponseEntity.status(HttpStatus.OK).
					body(new EditResponse("success",
					productService.removeProduct(productId)));

		} catch (RuntimeException e) {
			System.out.println("err in admin controller " + e);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while updating removing the product ", e.toString()));
		}
   }
	
	@GetMapping("/viewDiscounts")
	public ResponseEntity<?> fetchAllDiscountDetails() {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new DataResponse("success", adminService.getAllDiscounts()));

		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while fetching   ", e.toString()));
		}
	}
	
	@DeleteMapping("/deleteDiscount/{uid}")
	public ResponseEntity<?> deleteDiscountDetails(@PathVariable long uid) {

		return ResponseEntity.status(HttpStatus.OK).
				body(adminService.deleteDiscountDetails(uid));
	}
	
	@GetMapping("/viewAll")
	public ResponseEntity<?> showAllProducts() {
		return ResponseEntity.status(HttpStatus.OK).
				body(new DataResponse("success", productService.getAllProducts()));
	}
	
	@GetMapping("/getOrderDetails/{status}")
	public ResponseEntity<?> getOrderDetailsByStatus(@PathVariable String status) {

		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new DataResponse("success", orderDetailsService.getOrdersByStatus(status)));

		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong. Unable to fetch order details ", e.toString()));
		}
	}
	
	@PutMapping("/orders/updateStatus")
	public ResponseEntity<?> updateOrderStatus(@RequestBody OrderDetailsDto orderDto) {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(new EditResponse("success",
					orderDetailsService.updateOrderStatus(orderDto.getId(), orderDto.getStatus())));

			// o.s.http.ResponseEntity(T body,HttpStatus stsCode)
			// return new ResponseEntity<>(),
			// HttpStatus.OK);
		} catch (RuntimeException e) {
			System.out.println("err in admin controller " + e);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while updating quantity", e.toString()));
		}

	}
}
