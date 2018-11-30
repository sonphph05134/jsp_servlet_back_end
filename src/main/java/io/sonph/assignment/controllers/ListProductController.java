/*
 *== File Name: ListProductController.java
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
import io.sonph.assignment.dao.ProductDao;
import io.sonph.assignment.model.Account;
import io.sonph.assignment.model.Product;



/**
 * Assignment Demo -> ListProductController
 *
 * @author sonph
 */
public class ListProductController extends HttpServlet {

	/** Serial Version UID */
	private static final long serialVersionUID = 3587190937630338533L;

	/** Product Data Accessing Object */
	private ProductDao productDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListProductController() {
		super();
	}

	/**
	 * @see GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {

		// Instance new DAO
		this.productDao		= new ProductDao();
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
		List<Product> listProducts = this.productDao.getListProduct();

		// Set data into request scope
		request.setAttribute("listProducts", listProducts);

		// Redirect to list categories JSP
		RequestDispatcher dispatcher = request.getServletContext()
														.getRequestDispatcher("/views/product/list.jsp");
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

		// Get data from database
		List<Product> listProducts = this.productDao.getListProduct();

		// Set data into request scope
		request.setAttribute("listProducts", listProducts);

		// Redirect to list categories JSP
		RequestDispatcher dispatcher = request.getServletContext()
														.getRequestDispatcher("/views/product/list.jsp");
		dispatcher.forward(request, response);
	}
}
