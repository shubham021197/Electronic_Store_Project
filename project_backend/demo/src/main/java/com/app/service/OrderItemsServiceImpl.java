package com.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.OrderItemsDTO;
import com.app.dto.UserDTO;

import com.app.entities.ProductDetails;
import com.app.entities.ProductInventory;
import com.app.entities.OrderDetails;
import com.app.entities.OrderItems;
import com.app.entities.UserEntity;
import com.app.repository.ProductRepository;
import com.app.repository.OrderDetailsRepository;
import com.app.repository.OrderItemsRepository;
import com.app.repository.RoleRepository;
import com.app.repository.UserRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

@Service
@Transactional
public class OrderItemsServiceImpl implements IOrderItemsService {

	@Autowired
	private OrderItemsRepository orderItemsRepo;
	
	@Autowired
	private OrderDetailsRepository orderDetailsRepo;
	
	
	@Override
	public List<OrderItemsDTO> getOrderItems(long orderDetailsId) {
		// TODO Auto-generated method stub
		OrderDetails od = orderDetailsRepo.findById(orderDetailsId).orElseThrow(() -> new RuntimeException("Order details not found !"));
		List<OrderItems> orderItemsList = orderItemsRepo.findByOrder(od);
		List<OrderItemsDTO> result = new ArrayList<>();
		orderItemsList.forEach( item -> result.add(new OrderItemsDTO(item.getProduct(), item.getQuantity())));
		return 	result;
		
	}
}
