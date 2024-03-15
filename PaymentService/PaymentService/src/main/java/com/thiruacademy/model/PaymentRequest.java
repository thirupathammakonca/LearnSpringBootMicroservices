package com.thiruacademy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {
	//private long paymentId;
	private long orderId;
	private PaymentMode paymentMode;
	private double amount;
}
