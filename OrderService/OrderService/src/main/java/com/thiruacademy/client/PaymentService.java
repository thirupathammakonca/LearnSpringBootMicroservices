package com.thiruacademy.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.thiruacademy.model.PaymentDetails;

@FeignClient(name="PAYMENTSERVICE/payment")
public interface PaymentService {
	@GetMapping("/{orderId}")
	public ResponseEntity<PaymentDetails> getPaymentDetails(@PathVariable long orderId);
}

