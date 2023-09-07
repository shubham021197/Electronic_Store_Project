package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.repository.AddressRepository;


@Service
public class UserAddressImpl implements IUserAddress {
	@Autowired
	private AddressRepository addressRepo;
}
