package com.thiruacademy.service;

import org.springframework.stereotype.Service;

import com.thiruacademy.exception.OrderServiceCustomException;
import com.thiruacademy.model.OrderRequest;
import com.thiruacademy.model.OrderResponse;

@Service
public interface OrderService {
	

	public Long placeOrder(OrderRequest orderRequest);

	public OrderResponse getOrderDetails(long orderId) throws OrderServiceCustomException;
}
