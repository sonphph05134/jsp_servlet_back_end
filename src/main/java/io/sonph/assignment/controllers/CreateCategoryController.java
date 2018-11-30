/*
 *== File Name: CreateCategoryController.java
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

import org.apache.commons.lang3.StringUtils;

import io.sonph.assignment.common.CommonConst;
import io.sonph.assignment.dao.CategoryDao;
import io.sonph.assignment.model.Account;
import io.sonph.assignment.model.Category;



/**
 * Assignment Demo -> CreateCategoryController
 *
 * @author sonph
 */
public class CreateCategoryController extends HttpServlet {

	/** Serial Version UID */
	private static final long serialVersionUID = 5221983504065505483L;

	/** Category Data Accessing Object */
	private CategoryDao categoryDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateCategoryController() {
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

		// Set encoding
		request.setCharacterEncoding(CommonConst.REQEUST_CHARACTER_ENCODING_UTF8);

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
														.getRequestDispatcher("/views/category/create.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Set encoding
		request.setCharacterEncoding(CommonConst.REQEUST_CHARACTER_ENCODING_UTF8);

		// Get logged in account from session
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute(CommonConst.SESSION_ATTRIBUTE_ACCOUNT);

		// Check logged in account
		if (account == null) {
			// Return to login page
			response.sendRedirect(request.getContextPath() + CommonConst.AUTHETICATE_LOGIN_PATH);
			return;
		}

		// Get parameters
		String name = request.getParameter("name");
		String fatherId = request.getParameter("fatherId");

		// Validate parameters
		boolean hasInvalid = false;
		if (StringUtils.isEmpty(name)) { // Validate name
			request.setAttribute("errName", "Name is reuqired field.");
			hasInvalid = true;
		}
		if (StringUtils.isNotBlank(fatherId) && !StringUtils.isNumeric(fatherId)) { // Validate name
			request.setAttribute("errFatherId", "FatherID must be a number.");
			hasInvalid = true;
		}
		if (hasInvalid) { // Check invalid flag

			// Set invalid flash
			request.setAttribute("hasInvalid", true);

			// Get data from database
			List<Category> listCategories = this.categoryDao.getListCategories();

			// Set data into request scope
			request.setAttribute("listCategories", listCategories);

			// Forward to login page with invalid message
			RequestDispatcher dispatcher = request.getServletContext()
															.getRequestDispatcher("/views/category/create.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// Create new category mode
		Category category = new Category();
		category.setName(name);
		category.setFatherId(StringUtils.isEmpty(fatherId) ? null: Integer.parseInt(fatherId));

		// Save category
		this.categoryDao.createCategory(category);

		// Return to list categories screen
		response.sendRedirect(request.getContextPath() + "/list-categories");
	}
}
