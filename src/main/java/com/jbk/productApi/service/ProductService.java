package com.jbk.productApi.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.productApi.entity.Product;

public interface ProductService {
	
public boolean saveProduct(Product product);
public Product getProductById(int id);
public List<Product> getProductByName(String name);
public List<Product> getAllProduct();
public boolean updateProduct(Product product);
public boolean deleteProductById(int id);
public List<Product> getProductAscOrder();
public List<Product> getProductDesOrder();
public List<Product> getMaxPriceProduct();
public List<Product> getMinPriceProduct();
public Long getCountOfProject();
public int uploadSheet(MultipartFile file,HttpSession session);
}
