package com.thiruacademy.service;

import com.thiruacademy.model.PaymentRequest;
import com.thiruacademy.model.PaymentResponse;

public interface PaymentService {

	long doPayment(PaymentRequest paymentRequest);

	PaymentResponse getPaymentDetails(long orderId);

}
