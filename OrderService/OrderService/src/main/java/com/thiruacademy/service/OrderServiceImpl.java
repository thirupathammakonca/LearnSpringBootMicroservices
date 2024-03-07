package com.thiruacademy.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiruacademy.client.ProductService;
import com.thiruacademy.entity.Order;
import com.thiruacademy.model.OrderRequest;
import com.thiruacademy.repository.OredrRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OredrRepository oredrRepository;
	
	@Autowired
	private ProductService productService;
	
	@Override
	public Long placeOrder(OrderRequest orderRequest) {
		log.info("before palcing order");
		productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
		log.info("after checking product availability for product "+ orderRequest.getProductId());
		Order order = Order.builder()
				.productId(orderRequest.getProductId())
				.price(orderRequest.getAmount())
				.orderDate(Instant.now())
				.orderStatus("CREATED")
				.quantity(orderRequest.getQuantity())
				.build();
		log.info("Order placed");
		order = oredrRepository.save(order);
		log.info("product saved successfully after placing order");
		return order.getOrderId();
	}

}
