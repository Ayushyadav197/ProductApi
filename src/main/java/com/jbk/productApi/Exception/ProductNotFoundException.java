package com.jbk.productApi.Exception;
import java.lang.String;


public class ProductNotFoundException extends RuntimeException {

	public ProductNotFoundException(String message) {
		super(message);
	}
}
