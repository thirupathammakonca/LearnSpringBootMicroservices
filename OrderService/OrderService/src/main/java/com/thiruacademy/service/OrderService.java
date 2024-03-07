package com.thiruacademy.service;

import org.springframework.stereotype.Service;

import com.thiruacademy.model.OrderRequest;

@Service
public interface OrderService {
	

	public Long placeOrder(OrderRequest orderRequest);
}
