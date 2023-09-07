package com.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.DataResponse;
import com.app.service.ICategoryService;
import com.app.service.IProductService;
import com.app.service.ImageHandlingService;

@RestController
@RequestMapping("/home")
@CrossOrigin
public class HomeController {

	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private ImageHandlingService imageService;
	
//	@GetMapping("/getProduct/rating")
//	public ResponseEntity<?> getProductByRating(){
//		return ResponseEntity
//				.ok(productService.getProductByRating());
//	}
	
	@GetMapping("/getCategories")
	public ResponseEntity<?> getCategories(){
		return ResponseEntity.
				ok(new DataResponse("success", categoryService.getCategories()));
	}
	
	@GetMapping("/view/{category}")
	public ResponseEntity<?> getProductByCategory(@PathVariable String category) {
//		System.out.println(category.getName());
		return ResponseEntity.status(HttpStatus.OK).
				body(new DataResponse("success", productService.getProductByCategory(category)));
	}
	
	@GetMapping(value="/{productId}/images",produces = 
		{MediaType.IMAGE_GIF_VALUE,MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})
	public ResponseEntity<?> downloadImage(@PathVariable long productId) throws IOException {
		System.out.println("inside image api");
		return ResponseEntity.ok(imageService.restoreImage(productId));
	}
	


}
