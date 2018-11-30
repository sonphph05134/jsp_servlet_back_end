/*
 *== File Name: LIstCategoryController.java
 *== Project: assignment-backend
 *== Package: io.sonph.assignment.util
 */
package io.sonph.assignment.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.sonph.assignment.common.CommonConst;
import io.sonph.assignment.dao.CategoryDao;
import io.sonph.assignment.model.Account;
import io.sonph.assignment.model.Category;



/**
 * Assignment Demo -> ListCategoryController
 *
 * @author duongnguyen
 */
public class ListCategoryController extends HttpServlet {

	/** Serial Version UID */
	private static final long serialVersionUID = -9133720054267138506L;

	/** Category Data Accessing Object */
	private CategoryDao categoryDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListCategoryController() {
		super();
	}

	/**
	 * @see GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {

		// Instance new DAO
		this.categoryDao = new CategoryDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get logged in account from session
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute(CommonConst.SESSION_ATTRIBUTE_ACCOUNT);

		// Check logged in account
		if (account == null) {
			// Return to login page
			response.sendRedirect(request.getContextPath() + CommonConst.AUTHETICATE_LOGIN_PATH);
			return;
		}

		// Get data from database
		List<Category> listCategories = this.categoryDao.getListCategories();

		// Set data into request scope
		request.setAttribute("listCategories", listCategories);

		// Redirect to list categories JSP
		RequestDispatcher dispatcher = request.getServletContext()
														.getRequestDispatcher("/views/category/list.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO

		// Redirect to list categories JSP
		RequestDispatcher dispatcher = request.getServletContext()
														.getRequestDispatcher("/views/category/list.jsp");
		dispatcher.forward(request, response);
	}
}
