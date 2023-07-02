package com.jbk.productApi.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.productApi.Exception.NullPointerExceptionoccurs;
import com.jbk.productApi.Exception.ProductAlreadyExistException;
import com.jbk.productApi.Exception.ProductNotFoundException;
import com.jbk.productApi.entity.Product;
import com.jbk.productApi.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
	@Autowired
	private ProductService service;

	@PostMapping(value = "/saveProduct")
	public ResponseEntity<Boolean> saveProduct(@RequestBody Product product) {
		boolean saveProduct = service.saveProduct(product);
		if (saveProduct) {
			return new ResponseEntity<Boolean>(saveProduct, HttpStatus.CREATED);
		} else
			throw new ProductAlreadyExistException("Already Exist productId -> " + product.getProductId());
	}

	@GetMapping("getproductbyid/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) {
		Product productById = service.getProductById(id);
		if (productById != null) {
			return new ResponseEntity<Product>(productById, HttpStatus.FOUND);
		} else {
			throw new ProductNotFoundException("Product not Present for this id -> " + id);
		}
	}

	@GetMapping("getdatanameofproduct/{name}")
	public ResponseEntity<List<Product>> getProductByName(@PathVariable String name) {
		List<Product> list = service.getProductByName(name);
		if (!list.isEmpty()) {
			return new ResponseEntity<List<Product>>(list, HttpStatus.FOUND);
		} else {
			throw new NullPointerExceptionoccurs("Product not Present for this name -> " + name);
		}
	}

	@GetMapping("/getallproduct")
	public ResponseEntity<List<Product>> getAllProduct() {
		List<Product> allProduct = service.getAllProduct();
		if (!allProduct.isEmpty()) {
			return new ResponseEntity<List<Product>>(allProduct, HttpStatus.OK);
		} else {
			throw new NullPointerExceptionoccurs("Product not Present  ");
		}
	}

	@PutMapping("/updateproduct/product")
	public ResponseEntity<Boolean> updateProduct(@RequestBody Product product) {
		boolean updateProduct = service.updateProduct(product);
		if (updateProduct) {
			return new ResponseEntity<Boolean>(updateProduct, HttpStatus.OK);
		} else {
			throw new ProductNotFoundException("Enter valid id  this id product is not found id -> "+ product);
		}
	}

	@DeleteMapping(value = "/deleteproductbyid/{id}")
	public ResponseEntity<Boolean> deleteProductById(@PathVariable int id) {
		boolean deleteProductById = service.deleteProductById(id);
		if (deleteProductById) {
			return new ResponseEntity<Boolean>(deleteProductById, HttpStatus.OK);
		} else
			throw new ProductNotFoundException("Enter valid id  this id product is not found id -> " + id);
	}

	@GetMapping("/getproductascorder")
	public ResponseEntity<List<Product>> getProductAscOrder() {
		List<Product> productAscOrder = service.getProductAscOrder();
		if (!productAscOrder.isEmpty()) {
			return new ResponseEntity<List<Product>>(productAscOrder, HttpStatus.OK);
		} else {
			throw new NullPointerExceptionoccurs("Product not Present  ");
		}
	}

	@GetMapping("/getproductdesorder")
	public ResponseEntity<List<Product>> getProductDesOrder() {
		List<Product> productDesOrder = service.getProductDesOrder();
		if (!productDesOrder.isEmpty()) {
			return new ResponseEntity<List<Product>>(productDesOrder, HttpStatus.OK);
		} else {
			throw new NullPointerExceptionoccurs("Product not Present  ");
		}
	}

	@GetMapping("/getmaxpriceproduct")
	public ResponseEntity<List<Product>> getMaxPriceProduct() {
		List<Product> maxPriceProduct = service.getMaxPriceProduct();
		if (!maxPriceProduct.isEmpty()) {
			return new ResponseEntity<List<Product>>(maxPriceProduct, HttpStatus.OK);
		} else {
			throw new NullPointerExceptionoccurs("Product not Present  ");
		}
	}

	@GetMapping("/getminpriceproduct")
	public ResponseEntity<List<Product>> getMinPriceProduct() {
		List<Product> minPriceProduct = service.getMinPriceProduct();
		if (!minPriceProduct.isEmpty()) {
			return new ResponseEntity<List<Product>>(minPriceProduct, HttpStatus.OK);
		} else {
			throw new NullPointerExceptionoccurs("Product not Present  ");
		}
	}

	@GetMapping("/getcountofproduct")
	public ResponseEntity<Long> getCountOfProduct() {
		Long countOfProject = service.getCountOfProject();
		if (countOfProject > 0) {
			return new ResponseEntity<Long>(countOfProject, HttpStatus.OK);
		} else {
			throw new NullPointerExceptionoccurs("Product not Present  ");
		}
	}

	@PostMapping(value = "/uploadsheet")
	public ResponseEntity<String> uploadSheet(@RequestParam MultipartFile file, HttpSession session) {
		int msg = service.uploadSheet(file, session);
		return new ResponseEntity<String>(msg + " enteries addes successfully", HttpStatus.OK);
	}

}
