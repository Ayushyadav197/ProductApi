package com.jbk.productApi.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.buf.Ascii;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.jbk.productApi.entity.Product;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@Repository
public class ProductDaoImpl implements ProductDao {
	@Autowired
	SessionFactory factory;

	public boolean saveProduct(Product product) {
		Session openSession = factory.openSession();
		boolean isadded = false;
		try {
			Product product2 = openSession.get(Product.class, product.getProductId());
//			if (product.equals(product2)) {
//				isadded = false;
//			} else {
//				openSession.save(product);
//				beginTransaction.commit();
//				isadded = true;
//			}
			if (product2 == null) {
				Transaction beginTransaction = openSession.beginTransaction();
				openSession.save(product);
				beginTransaction.commit();
				isadded = true;
			} else {
				isadded = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (openSession != null)
				openSession.close();
		}
		return isadded;
	}

	public Product getProductById(int id) {
		Session openSession = factory.openSession();
		Product product = null;
		try {
			Transaction beginTransaction = openSession.beginTransaction();
			product = openSession.get(Product.class, id);
			beginTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (openSession != null)
				openSession.close();
		}
		return product;
	}

	public List<Product> getProductByName(String name) {
		Session openSession = factory.openSession();
		List<Product> list = null;
		try {
			Transaction beginTransaction = openSession.beginTransaction();
			Criteria createCriteria = openSession.createCriteria(Product.class);
			createCriteria.add(Restrictions.eq("productName", name));
			list = createCriteria.list();
			beginTransaction.commit();
			// openSession.createCriteria(Restrictions.eq("productName", name));
			// product = openSession.get(Product.class, name);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (openSession != null)
				openSession.close();
		}
		return list;
	}

	public List<Product> getAllProduct() {
		Session openSession = factory.openSession();
		List list = null;
		try {
			Criteria createCriteria = openSession.createCriteria(Product.class);
			list = createCriteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (openSession != null)
				openSession.close();
		}
		return list;
	}

	public boolean updateProduct(Product product) {
		Session openSession1 = factory.openSession();
		Session openSession2 = factory.openSession();
		boolean isUpdate = false;
		try {
			Transaction beginTransaction = openSession1.beginTransaction();
			Product product1 = openSession2.get(Product.class, product.getProductId());
			if (product.getProductId() != product1.getProductId()) {

				isUpdate = false;
			} else {
				openSession1.update(product);
				System.out.println(product);
				isUpdate = true;
			}


			beginTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (openSession1 != null)
				openSession1.close();
		}
		return isUpdate;
	}

	public boolean deleteProductById(int id) {
		Session openSession = factory.openSession();
		boolean isDeleted = false;
		try {
			Transaction beginTransaction = openSession.beginTransaction();
			Product product = openSession.load(Product.class, id);
			if (product != null) {
				openSession.delete(product);
				isDeleted = true;
				beginTransaction.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (openSession != null)
				openSession.close();
		}
		return isDeleted;
	}

	public List<Product> getProductAscOrder() {
		Session openSession = factory.openSession();
		List list = null;
		try {
			Criteria createCriteria = openSession.createCriteria(Product.class);
			createCriteria.addOrder(Order.asc("productId"));
			list = createCriteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (openSession != null)
				openSession.close();
		}
		return list;
	}

	public List<Product> getProductDesOrder() {
		Session openSession = factory.openSession();
		List list = null;
		try {
			Criteria createCriteria = openSession.createCriteria(Product.class);
			createCriteria.addOrder(Order.desc("productId"));
			list = createCriteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (openSession != null)
				openSession.close();
		}
		return list;
	}

	public List<Product> getMaxPriceProduct() {
		Session openSession = factory.openSession();
		List listproduct = null;
		try {
			Transaction beginTransaction = openSession.beginTransaction();
			Criteria createCriteria = openSession.createCriteria(Product.class);
			Criteria createCriteria1 = openSession.createCriteria(Product.class);
			createCriteria.setProjection(Projections.max("productPrice"));
			List listmaxprice = createCriteria.list();
			int id = (int) listmaxprice.get(0);
			createCriteria1.add(Restrictions.eq("productPrice", id));
			listproduct = createCriteria1.list();
			beginTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (openSession != null)
				openSession.close();
		}
		return listproduct;
	}

	public List<Product> getMinPriceProduct() {
		Session openSession = factory.openSession();
		List listproduct = null;
		try {
			Transaction beginTransaction = openSession.beginTransaction();
			Criteria createCriteria = openSession.createCriteria(Product.class);
			Criteria createCriteria1 = openSession.createCriteria(Product.class);
			createCriteria.setProjection(Projections.min("productPrice"));
			List listminprice = createCriteria.list();
			int id = (int) listminprice.get(0);
			createCriteria1.add(Restrictions.eq("productPrice", id));
			listproduct = createCriteria1.list();
			beginTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (openSession != null)
				openSession.close();
		}
		return listproduct;
	}

	public Long getCountOfProject() {
		Session openSession = factory.openSession();
		Long countofEnteries = null;
		try {
			Transaction beginTransaction = openSession.beginTransaction();
			Criteria createCriteria = openSession.createCriteria(Product.class);
			createCriteria.setProjection(Projections.rowCount());
			List list = createCriteria.list();
			countofEnteries = (Long) list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (openSession != null)
				openSession.close();
		}
		return countofEnteries;
	}

	public int excelToDb(List<Product> list) {
		int countAdded = 0;
		boolean isAdded;
		for (Product product : list) {
			isAdded = saveProduct(product);
			if (isAdded) {
				countAdded = countAdded + 1;
			}
		}
		return countAdded;
	}

}
