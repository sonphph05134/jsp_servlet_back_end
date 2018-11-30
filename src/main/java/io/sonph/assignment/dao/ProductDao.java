/*
 *== File Name: Product.java
 *== Project: assignment-backend
 *== Package: io.sonph.assignment.util
 */
package io.sonph.assignment.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import io.sonph.assignment.model.Product;
import io.sonph.assignment.util.HibernateUtil;

/**
 * Assignment Demo -> ProductDao
 *
 * @author sonph
 */
public class ProductDao {

	/** Hiberate DB Session */
	private Session session;

	/**
	 * Get list product
	 *
	 * @return List Product
	 */
	public List<Product> getListProduct() {

		// Declare result variable
		List<Product> listProduct = null;

		// Handler error with try - catch - finally
		try {

			// Open Hibernate session
			this.openSession();

			// Query data
			Query<Product> query = this.session.createQuery("from Product", Product.class);
			listProduct = query.getResultList();

		} finally {
			// Close Hibernate session
			this.closeSession();
		}

		// Return result
		return listProduct;
	}

	/**
	 * Get product by ID
	 *
	 * @param id Product ID
	 *
	 * @return Product
	 */
	public Product getProduct(Integer id) {

		// Declare result variable
		Product product = null;

		// Handler error with try - catch - finally
		try {

			// Open Hibernate session
			this.openSession();

			// Find product
			product = this.session.find(Product.class, id);

		} finally {
			// Close Hibernate session
			this.closeSession();
		}

		// Return result
		return product;
	}

	/**
	 * Get product by code
	 *
	 * @param code Product code
	 *
	 * @return Product
	 */
	public Product getProduct(String code) {

		// Declare result variable
		Product product = null;

		// Handler error with try - catch - finally
		try {

			// Open Hibernate session
			this.openSession();

			// Find product
			Query<Product> query = this.session.createQuery("from Product where code = :code", Product.class);
			query.setParameter("code", code);
			product = query.uniqueResult();

		} finally {
			// Close Hibernate session
			this.closeSession();
		}

		// Return result
		return product;
	}

	/**
	 * Create product
	 *
	 * @param product Product
	 */
	public void createProduct(Product product) {

		// Handler error with try - catch - finally
		try {

			// Open Hibernate session
			this.openSession();

			// Save product
			this.session.beginTransaction();
			this.session.save(product);
			this.session.flush();
			this.session.getTransaction().commit();

		} catch (Exception ex) {
			this.session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			// Close Hibernate session
			this.closeSession();
		}
	}

	/**
	 * Update product
	 *
	 * @param product Product
	 */
	public void updateProduct(Product product) {

		// Handler error with try - catch - finally
		try {

			// Open Hibernate session
			this.openSession();
			
			// Find old product
			Product currentproduct = this.session.find(Product.class, product.getId());
			
			// Set new data
			currentproduct.setName(product.getName());
			currentproduct.setCategoryId(product.getCategoryId());
			currentproduct.setCode(product.getCode());
			currentproduct.setUnitPrice(product.getUnitPrice());
			currentproduct.setImage(product.getImage());

			// Update product
			this.session.beginTransaction();
			this.session.update(currentproduct);
			this.session.flush();
			this.session.getTransaction().commit();

		} catch (Exception ex) {
			this.session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			// Close Hibernate session
			this.closeSession();
		}
	}

	/**
	 * Delete product
	 *
	 * @param id Product Id
	 */
	public void deleteProduct(Integer id) {

		// Handler error with try - catch - finally
		try {

			// Open Hibernate session
			this.openSession();

			// Find product
			Product product = this.session.find(Product.class, id);
			
			// Delete product
			this.session.beginTransaction();
			this.session.delete(product);
			this.session.flush();
			this.session.getTransaction().commit();

		} catch (Exception ex) {
			this.session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			// Close Hibernate session
			this.closeSession();
		}
	}

	/**
	 * Open Hibernate session
	 */
	private void openSession() {

		this.session = HibernateUtil.getSessionFactory().openSession();
	}

	/**
	 * Close Hibernate session
	 */
	private void closeSession() {

		if (this.session != null && this.session.isOpen()) {
			this.session.close();
		}
	}
}
