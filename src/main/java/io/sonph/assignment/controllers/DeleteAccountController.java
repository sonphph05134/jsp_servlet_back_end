/*
 *== File Name: DeleteAccountController.java
 *== Project: assignment-backend
 *== Package: io.sonph.assignment.util
 */
package io.sonph.assignment.controllers;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import io.sonph.assignment.common.CommonConst;
import io.sonph.assignment.dao.AccountDao;
import io.sonph.assignment.dao.CategoryDao;
import io.sonph.assignment.model.Account;
import io.sonph.assignment.model.Category;

/**
 * Assignment Demo -> DeleteAccountController
 *
 * @author sonph
 */
public class DeleteAccountController extends HttpServlet {

	/** Serial Version UID */
	private static final long serialVersionUID = -2773462257772627255L;

	/** Acount Data Accessing Object */
	private AccountDao accountDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteAccountController() {
		super();
	}

	/**
	 * @see GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		// Instance new DAO
		this.accountDao = new AccountDao();
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
		Account accountchecklogin = (Account) session.getAttribute(CommonConst.SESSION_ATTRIBUTE_ACCOUNT);

		// Check logged in account
		if (accountchecklogin == null) {
			// Return to login page
			response.sendRedirect(request.getContextPath() + CommonConst.AUTHETICATE_LOGIN_PATH);
			return;
		}

		// Get parameters
		String id = request.getParameter("id");

		// Validate parameter
		if (StringUtils.isBlank(id) || !StringUtils.isNumeric(id)) {

			// Set exception
			request.setAttribute("exception", new Exception("Invalid input parameter: ID"));

			// Redirect to error page
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/common/error.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// Find category
		Account account = this.accountDao.getAccount(Integer.parseInt(id));

		// Set category into request
		request.setAttribute("account", account);

		// Redirect to list categories JSP
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/account/delete.jsp");
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
		Account accountchecklogin = (Account) session.getAttribute(CommonConst.SESSION_ATTRIBUTE_ACCOUNT);

		// Check logged in account
		if (accountchecklogin == null) {
			// Return to login page
			response.sendRedirect(request.getContextPath() + CommonConst.AUTHETICATE_LOGIN_PATH);
			return;
		}

		// Get parameters
		String id = request.getParameter("id");

		// Validate parameter
		if (StringUtils.isBlank(id) || !StringUtils.isNumeric(id)) {

			// Set exception
			request.setAttribute("exception", new Exception("Invalid input parameter: ID"));

			// Redirect to error page
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/common/error.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// Delete account
		this.accountDao.deleteAccount(Integer.parseInt(id));

		// Return to list categories screen
		response.sendRedirect(request.getContextPath() + "/list-accounts");
	}
}
