package com.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.repository.DiscountRepository;

@Service
//@Transactional
public class DiscountServiceImpl implements IDiscountService {

	@Autowired
	private DiscountRepository discountRepo;
	
}
