/*
 *== File Name: CategoryDao.java
 *== Project: assignment-backend
 *== Package: io.sonph.assignment.util
 */
package io.sonph.assignment.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import io.sonph.assignment.model.Category;
import io.sonph.assignment.util.HibernateUtil;

/**
 * Assignment Demo -> CategoryDao
 *
 * @author sonph
 */
public class CategoryDao {

	/** Hiberate DB Session */
	private Session session;

	/**
	 * Get list categories
	 *
	 * @return List Category
	 */
	public List<Category> getListCategories() {

		// Declare result variable
		List<Category> categoryList = null;

		// Handler error with try - catch - finally
		try {

			// Open Hibernate session
			this.openSession();

			// Create Hibernate Query
			Query<Category> query = this.session.createQuery(
									"from Category where fatherId is null order by sortNo" , Category.class);

			// Execute Hibernate query and get list result
			categoryList = query.list();

		} finally {
			// Close Hibernate session
			this.closeSession();
		}

		// Return result
		return categoryList;
	}

	/**
	 * Get a category by id
	 *
	 * @param id Category id
	 *
	 * @return	Category
	 */
	public Category getCategory(Integer id) {

		// Declare result variable
		Category category = null;

		// Handler error with try - catch - finally
		try {

			// Open Hibernate session
			this.openSession();

			// Find category
			category = this.session.find(Category.class, id);

		} finally {
			// Close Hibernate session
			this.closeSession();
		}

		// Return result
		return category;
	}

	/**
	 * Create new category
	 *
	 * @param category Category DTO with param
	 */
	public void createCategory(Category category) {

		// Handler error with try - catch - finally
		try {

			// Open Hibernate session
			this.openSession();

			// Save category
			this.session.beginTransaction();
			this.session.save(category);
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
	 * Update category
	 *
	 * @param category  Category DTO with param
	 */
	public void updateCategory(Category category) {

		// Handler error with try - catch - finally
		try {

			// Open Hibernate session
			this.openSession();

			// Find old category
			Category currCategory = this.session.find(Category.class, category.getId());

			// Set new data
			currCategory.setName(category.getName());
			currCategory.setFatherId(category.getFatherId());

			// Save category
			this.session.beginTransaction();
			this.session.update(currCategory);
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
	 * Delete a category
	 *
	 * @param id Category ID
	 */
	public void deleteCategory(Integer id) {

		// Handler error with try - catch - finally
		try {

			// Open Hibernate session
			this.openSession();

			// Find category
			Category category = this.session.find(Category.class, id);

			// Save category
			this.session.beginTransaction();
			this.session.delete(category);
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
