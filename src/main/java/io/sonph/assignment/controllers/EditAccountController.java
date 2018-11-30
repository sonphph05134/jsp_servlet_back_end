/*
 *== File Name: EditAccountController.java
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
import javax.servlet.jsp.JspException;

import org.apache.commons.lang3.StringUtils;

import io.sonph.assignment.common.CommonConst;
import io.sonph.assignment.dao.AccountDao;
import io.sonph.assignment.model.Account;
import io.sonph.assignment.model.Category;


/**
 * Assignment Demo -> EditAccountController
 *
 * @author sonph
 */
public class EditAccountController extends HttpServlet {

	/** Serial Version UID */
	private static final long serialVersionUID = 7665821590421676631L;

	/** Product Data Accessing Object */
	private AccountDao accountDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditAccountController() {
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
			request.setAttribute("exception", new JspException("Invalid input parameter: ID"));

			// Redirect to error page
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/common/error.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// Find account
		Account account = this.accountDao.getAccount(Integer.parseInt(id));
		
		// Set category into request
		request.setAttribute("account", account);

		// Get data from database
		List<Account> listAccounts = this.accountDao.getListAccounts();

		// Set category into request
		request.setAttribute("listAccounts", listAccounts);

		// Redirect to list categories JSP
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/account/edit.jsp");
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


		// Get hidden parameters
		String id = request.getParameter("id");

		// Validate hidden parameter
		if (StringUtils.isBlank(id) || !StringUtils.isNumeric(id)) {

			// Set exception
			request.setAttribute("exception", new Exception("Invalid input parameter: ID"));

			// Redirect to error page
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/common/error.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// Get parameters
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String address = request.getParameter("address");

		// Validate parameters
		boolean hasInvalid = false;
		if (StringUtils.isEmpty(id)) { // Validate username
			request.setAttribute("errId", "ID is reuqired field.");
			hasInvalid = true;
		}
		if (StringUtils.isEmpty(username)) { // Validate username
			request.setAttribute("errUsername", "Username is reuqired field.");
			hasInvalid = true;
		}
		if (StringUtils.isEmpty(password)) { // Validate password
			request.setAttribute("errPassword", "Password is reuqired field.");
			hasInvalid = true;
		}
		if (StringUtils.isEmpty(type)) { // Validate type
			request.setAttribute("errType", "Type is reuqired field.");
			hasInvalid = true;
		}
		if (StringUtils.isEmpty(fullName)) { // Validate fullName
			request.setAttribute("errFullName", "FullName is reuqired field.");
			hasInvalid = true;
		}
		if (StringUtils.isEmpty(email)) { // Validate email
			request.setAttribute("errEmail", "Email is reuqired field.");
			hasInvalid = true;
		}
		if (StringUtils.isEmpty(address)) { // Validate address
			request.setAttribute("errAddress", "Address is reuqired field.");
			hasInvalid = true;
		}

		if (hasInvalid) { // Check invalid flag

			// Set invalid flash
			request.setAttribute("hasInvalid", true);

			// Set account into request
			request.setAttribute("account", account);

			// Get data from database
			List<Account> listAccounts = this.accountDao.getListAccounts();

			// Set data into request scope
			request.setAttribute("listAccounts", listAccounts);

			// Forward to login page with invalid message
			RequestDispatcher dispatcher = request.getServletContext()
															.getRequestDispatcher("/views/account/edit.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// Create new account mode
		Account accountss = new Account();
		accountss.setId(Integer.parseInt(id));
		accountss.setUsername(username);
		accountss.setPassword(password);
		accountss.setType(type);
		accountss.setFullName(fullName);
		accountss.setEmail(email);
		accountss.setAddress(address);

		// Save account
		this.accountDao.updateAccount(accountss);

		// Return to list accounts screen
		response.sendRedirect(request.getContextPath() + "/list-accounts");
		
	}
}
