package com.thiruacademy.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiruacademy.entity.Product;
import com.thiruacademy.exception.ProductServiceException;
import com.thiruacademy.model.ProductRequest;
import com.thiruacademy.model.ProductResponse;
import com.thiruacademy.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productReposity;

	@Override
	public Long addProduct(ProductRequest productRequest) {
		Product product = Product.builder()
				.productName(productRequest.getProductName())
				.price(productRequest.getPrice())
				.quantity(productRequest.getQuantity())
				.build();
		productReposity.save(product);
		return product.getProductId();
	}

	@Override
	public ProductResponse getProductById(Long productId) throws ProductServiceException {
		Product product = productReposity.findById(productId)
				.orElseThrow(()-> new ProductServiceException("Product id not Found", "Product_Not_Found"));
		/*
		 * ProductResponse productResponse = ProductResponse.builder()
		 * .productName(product.getProductName()) .price(product.getPrice())
		 * .quantity(product.getQuantity()) .build();
		 */
		ProductResponse productResponse = new ProductResponse();
		BeanUtils.copyProperties(product, productResponse);
		return productResponse;
	}

	@Override
	public void reduceQuantity(Long productId, int quantity) throws ProductServiceException {
		log.info("reduceing Quantity initiated");
		Product product = productReposity.findById(productId)
				.orElseThrow(()-> new ProductServiceException("Product id not Found", "Product_Not_Found"));
		if(product instanceof Product) {
			log.info("checking Product quantity");
			if(product.getQuantity() < quantity) {
				throw new ProductServiceException("not having enough quantity of products ","NOT_ENOUGH_QUANTITY");
			}
			product.setQuantity(product.getQuantity() - quantity);
			productReposity.save(product);
			log.info("Product saved in DB");
		}
	}

}
