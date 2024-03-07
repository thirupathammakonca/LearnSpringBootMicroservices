package com.thiruacademy.service;

import com.thiruacademy.exception.ProductServiceException;
import com.thiruacademy.model.ProductRequest;
import com.thiruacademy.model.ProductResponse;

public interface ProductService {

	Long addProduct(ProductRequest productRequest);

	ProductResponse getProductById(Long productId) throws ProductServiceException;

	void reduceQuantity(Long productId, int quantity) throws ProductServiceException;

}
