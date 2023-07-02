package com.jbk.productApi.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.productApi.dao.ProductDao;
import com.jbk.productApi.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao dao;

	public boolean saveProduct(Product product) {
		boolean saveProduct = dao.saveProduct(product);
		if (saveProduct == true) {
			System.out.println("Entry save successfully");
		} else {
			System.out.println("Do not save this entry it is already present");
		}
		return saveProduct;
	}

	public Product getProductById(int id) {
		Product product = dao.getProductById(id);
		return product;
	}

	public List<Product> getProductByName(String name) {
		List<Product> productByName = dao.getProductByName(name);
		return productByName;
	}

	public List<Product> getAllProduct() {
		List<Product> allProduct = dao.getAllProduct();
		return allProduct;
	}

	public boolean updateProduct(Product product) {
		boolean updateProduct = dao.updateProduct(product);
		return updateProduct;
	}

	public boolean deleteProductById(int id) {
		boolean deleteProductById = dao.deleteProductById(id);
		return deleteProductById;
	}

	public List<Product> getProductAscOrder() {
		List<Product> productAscOrder = dao.getProductAscOrder();
		return productAscOrder;
	}

	public List<Product> getProductDesOrder() {
		List<Product> productDesOrder = dao.getProductDesOrder();
		return productDesOrder;
	}

	public List<Product> getMaxPriceProduct() {
		List<Product> maxPriceProduct = dao.getMaxPriceProduct();
		return maxPriceProduct;
	}

	public List<Product> getMinPriceProduct() {
		List<Product> minPriceProduct = dao.getMinPriceProduct();
		return minPriceProduct;
	}

	public Long getCountOfProject() {
		Long countOfProject = dao.getCountOfProject();
		return countOfProject;
	}

	public List<Product> readExcel(String path) {
		Product product = null;
		List<Product> list = null;
		try {
			FileInputStream fis = new FileInputStream(new File(path));
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();
			list = new ArrayList<>();
			int cnt = 0;
			while (rows.hasNext()) {
				Row row = rows.next();
				product = new Product();
				if ((cnt == 0)) {
					cnt++;
					continue;
				}
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					int columnIndex = cell.getColumnIndex();
					switch (columnIndex) {
					case 0:
						product.setProductId((int) cell.getNumericCellValue());
						break;
					case 1:
						product.setProductName(cell.getStringCellValue());
						break;
					case 2:
						product.setProductCompany(cell.getStringCellValue());
						break;
					case 3:
						product.setProductPrice((int) cell.getNumericCellValue());
						break;

					}
				}
				list.add(product);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int uploadSheet(MultipartFile file, HttpSession session) {
		int countAdded = 0;
		String realPath = session.getServletContext().getRealPath("/upload");
		String originalFilename = file.getOriginalFilename();
		List<Product> list = null;
		try {
			byte[] bytes = file.getBytes();
			FileOutputStream fos = new FileOutputStream(new File(realPath + "/" + originalFilename));
			fos.write(bytes);
			list = readExcel(realPath + File.separator + originalFilename);
			countAdded = dao.excelToDb(list);
			for (Product product : list) {
				System.out.println(product);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return countAdded;
	}

}
