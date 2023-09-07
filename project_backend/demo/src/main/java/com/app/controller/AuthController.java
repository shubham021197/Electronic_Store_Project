package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AuthRequest;
import com.app.dto.AuthResp;
import com.app.dto.UserDTO;
import com.app.repository.UserRepository;
import com.app.service.IUserService;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("/signup")
	public ResponseEntity<?> userRegistration(@RequestBody @Valid UserDTO user)
	{
		System.out.println("in reg user : user "+user+" roles ");//{....."roles" : [ROLE_USER,...]}
		//invoke service layer method , for saving : user info + associated roles info
		
		if(userRepo.findByEmail(user.getEmail()).isPresent())
		{
			return new ResponseEntity<>(new AuthResp("exists"),HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(user));		
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> validateUserCreateToken(@RequestBody @Valid AuthRequest request)
	{
		return ResponseEntity.ok(new AuthResp("success",null, userService.getUserDetails(request.getEmail())));
	}
}
