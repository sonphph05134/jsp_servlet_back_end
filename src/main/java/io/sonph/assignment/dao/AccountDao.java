/*
 *== File Name: AccountDao.java
 *== Project: assignment-backend
 *== Package: io.sonph.assignment.util
 */
package io.sonph.assignment.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import io.sonph.assignment.model.Account;
import io.sonph.assignment.model.Category;
import io.sonph.assignment.util.HibernateUtil;

/**
 * Assignment Demo -> AccountDao
 *
 * @author sonph
 */
public class AccountDao {

	/** Hiberate DB Session */
	private Session session;

	/**
	 * Get list categories
	 *
	 * @return List Category
	 */
	public List<Account> getListAccounts() {

		// Declare result variable
		List<Account> accountList = null;

		// Handler error with try - catch - finally
		try {

			// Open Hibernate session
			this.openSession();

			// Create Hibernate Query
			Query<Account> query = this.session.createQuery("from Account " , Account.class);

			// Execute Hibernate query and get list result
			accountList = query.list();

		} finally {
			// Close Hibernate session
			this.closeSession();
		}

		// Return result
		return accountList;
	}

	/**
	 * Get a accounty by id
	 *
	 * @param id Account id
	 *
	 * @return	Account
	 */
	public Account getAccount(Integer id) {

		// Declare result variable
		Account account = null;

		// Handler error with try - catch - finally
		try {

			// Open Hibernate session
			this.openSession();

			// Find category
			account = this.session.find(Account.class, id);

		} finally {
			// Close Hibernate session
			this.closeSession();
		}

		// Return result
		return account;
	}

	/**
	 * Create new category
	 *
	 * @param category Category DTO with param
	 */
	public void createAccount(Account account) {

		// Handler error with try - catch - finally
		try {

			// Open Hibernate session
			this.openSession();
			// Find old category
			Account currAccount = this.session.find(Account.class, account.getId());

			// Set new data
			currAccount.setId(account.getId());
			
			// Save category
			this.session.beginTransaction();
			this.session.save(currAccount);
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
	 * Update account
	 *
	 * @param account  Account DTO with param
	 */
	public void updateAccount(Account account) {

		// Handler error with try - catch - finally
		try {

			// Open Hibernate session
			this.openSession();

			// Update account
			this.session.beginTransaction();
			this.session.update(account);
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
	public void deleteAccount(Integer id) {

		// Handler error with try - catch - finally
		try {

			// Open Hibernate session
			this.openSession();

			// Find account
			Account account = this.session.find(Account.class, id);

			// Save category
			this.session.beginTransaction();
			this.session.delete(account);
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
