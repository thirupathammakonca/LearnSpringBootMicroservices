package com.thiruacademy.service;


import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiruacademy.entity.PaymentDetails;
import com.thiruacademy.model.PaymentMode;
import com.thiruacademy.model.PaymentRequest;
import com.thiruacademy.model.PaymentResponse;
import com.thiruacademy.repository.PaymentRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public long doPayment(PaymentRequest paymentRequest) {
		log.info("paymentRequest in PaymentServiceImpl ::"+paymentRequest);
		PaymentDetails paymentDetails = PaymentDetails.builder()
				.amount(paymentRequest.getAmount())
				.paymentDate(Instant.now())
				.paymentMode(paymentRequest.getPaymentMode().name())
				.paymentStatus("SUCCESS")
				.orderId(paymentRequest.getOrderId())
				.build();
		log.info("paymentDetails in doPayment "+paymentDetails);
		paymentRepository.save(paymentDetails);
		return paymentDetails.getPaymentId();
	}

	@Override
	public PaymentResponse getPaymentDetails(long orderId) {
		PaymentDetails paymentDetails = paymentRepository.findByOrderId(orderId);
		PaymentResponse paymentResponse = PaymentResponse.builder()
				.amount(paymentDetails.getAmount())
				.orderId(paymentDetails.getOrderId())
				.paymentDate(paymentDetails.getPaymentDate())
				.paymentId(paymentDetails.getPaymentId())
				.status(paymentDetails.getPaymentStatus())
				.paymentMode(PaymentMode.valueOf(paymentDetails.getPaymentMode()))
				.build();
		return paymentResponse;
	}

}
