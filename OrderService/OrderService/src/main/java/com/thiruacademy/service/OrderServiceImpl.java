package com.thiruacademy.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiruacademy.client.PaymentService;
import com.thiruacademy.client.ProductService;
import com.thiruacademy.entity.Order;
import com.thiruacademy.exception.OrderServiceCustomException;
import com.thiruacademy.model.OrderRequest;
import com.thiruacademy.model.OrderResponse;
import com.thiruacademy.model.PaymentDetails;
import com.thiruacademy.model.ProductDetails;
import com.thiruacademy.repository.OrderRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private PaymentService paymentService;

	@Override
	public Long placeOrder(OrderRequest orderRequest) {
		log.info("before palcing order");
		productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
		log.info("after checking product availability for product " + orderRequest.getProductId());
		Order order = Order.builder().productId(orderRequest.getProductId()).price(orderRequest.getAmount())
				.orderDate(Instant.now()).orderStatus("CREATED").quantity(orderRequest.getQuantity()).build();
		log.info("Order placed");
		order = orderRepository.save(order);
		log.info("product saved successfully after placing order");
		return order.getOrderId();
	}

	@Override
	public OrderResponse getOrderDetails(long orderId) throws OrderServiceCustomException {
		log.info("Get order details for Order Id :: " + orderId);

		Order order = orderRepository.findById(orderId).orElseThrow(
				() -> new OrderServiceCustomException("Order not found for the order Id:" + orderId, "ORDER_NOT_FOUND"));

		OrderResponse orderResponse = OrderResponse.builder()
				.orderId(order.getOrderId())
                .orderStatus(order.getOrderStatus())
                .amount(order.getPrice())
                .orderDate(order.getOrderDate())
				.build();
		log.info("Calling Product service to fetch the product for id:: ", order.getProductId());
		
		ProductDetails productDetails = productService.getProduct(order.getProductId()).getBody();
		orderResponse.setProductDetails(productDetails);
		log.info("Getting payment information form the payment Service");
		
		PaymentDetails paymentDetails = paymentService.getPaymentDetails(order.getOrderId()).getBody();
		orderResponse.setPaymentDetails(paymentDetails);
		
		return orderResponse;
	}

}
