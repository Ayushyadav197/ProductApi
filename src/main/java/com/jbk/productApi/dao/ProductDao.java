package com.jbk.productApi.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jbk.productApi.entity.Product;

public interface ProductDao {
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
public int excelToDb(List<Product> list);
}
