package com.thiruacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiruacademy.exception.OrderServiceCustomException;
import com.thiruacademy.model.OrderRequest;
import com.thiruacademy.model.OrderResponse;
import com.thiruacademy.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@PostMapping("/placeOrder")
	public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest) {
		Long orderId = orderService.placeOrder(orderRequest);
		return new ResponseEntity<Long>(orderId, HttpStatus.OK);
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable long orderId) throws OrderServiceCustomException {
		OrderResponse orderResponse = orderService.getOrderDetails(orderId);

		return new ResponseEntity<>(orderResponse, HttpStatus.OK);
	}
}
