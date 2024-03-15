package com.thiruacademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thiruacademy.entity.PaymentDetails;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentDetails, Long>{
	public PaymentDetails findByOrderId(long orderId);
}
