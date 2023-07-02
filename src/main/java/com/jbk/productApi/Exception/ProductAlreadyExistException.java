package com.jbk.productApi.Exception;

public class ProductAlreadyExistException extends RuntimeException {

	public ProductAlreadyExistException(String msg) {
		super(msg);
	}
}
