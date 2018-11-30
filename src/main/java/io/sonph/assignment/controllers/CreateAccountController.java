/*
 *== File Name: CreateAccountController.java
 *== Project: assignment-backend
 *== Package: io.sonph.assignment.util
 */
package io.sonph.assignment.controllers;

import java.io.IOException;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import io.sonph.assignment.common.CommonConst;
import io.sonph.assignment.dao.AccountDao;
import io.sonph.assignment.model.Account;
import io.sonph.assignment.model.Category;
import io.sonph.assignment.model.Product;

/**
 * Assignment Demo -> CreateAccountController
 *
 * @author sonph
 */
public class CreateAccountController extends HttpServlet {

	/** Serial Version UID */
	private static final long serialVersionUID = 1136619225045520610L;

	/** Category Data Accessing Object */
	private AccountDao accountDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateAccountController() {
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
		Account account = (Account) session.getAttribute(CommonConst.SESSION_ATTRIBUTE_ACCOUNT);

		// Check logged in account
		if (account == null) {
			// Return to login page
			response.sendRedirect(request.getContextPath() + CommonConst.AUTHETICATE_LOGIN_PATH);
			return;
		}

		// Get data from database
		List<Account> listAccounts = this.accountDao.getListAccounts();

		// Set data into request scope
		request.setAttribute("listAccounts", listAccounts);

		// Redirect to list categories JSP
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/account/create.jsp");
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String address = request.getParameter("address");

		// Validate parameters
		boolean hasInvalid = false;
	
		// Validate username
		if (StringUtils.isEmpty(username)||username.length() < 3) {
			request.setAttribute("errUsername", "Username is reuqired field and Username must  be greater than 3 characters.");
			hasInvalid = true;
		}

		// Validate password
		if (StringUtils.isBlank(password) || password.length() < 3) {
			request.setAttribute("errPassword", "Password is reuqired field and Password must be greater than 3 characters.");
			hasInvalid = true;
		} 
		
		// Validate type
		if (StringUtils.isEmpty(type)) {
			request.setAttribute("errType", "Type must not be null.");
			hasInvalid = true;
		}

		// Validate fullName
		if (StringUtils.isEmpty(fullName)) {
			request.setAttribute("errFullName", "FullName must not be null.");
			hasInvalid = true;
		}
		if (StringUtils.isEmpty(email)) {
			request.setAttribute("errEmail", "Email must not be null.");
			hasInvalid = true;
		}
		if (StringUtils.isEmpty(address)) {
			request.setAttribute("errAddress", "Address must not be null.");
			hasInvalid = true;
		}
		// Check invalid flag
		if (hasInvalid) {

			// Set invalid flag
			request.setAttribute("hasInvalid", true);

			// Get data from database
			List<Account> listAccounts = this.accountDao.getListAccounts();

			// Set data into request scope
			request.setAttribute("listAccounts", listAccounts);

			// Forward to login page with invalid message
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/account/create.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// Create account model
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);
		account.setType(type);
		account.setFullName(fullName);
		account.setEmail(email);
		account.setAddress(address);
		// Save account
		this.accountDao.createAccount(account);

		// Return to list categories screen
		response.sendRedirect(request.getContextPath() + "/list-accounts");
		;
	}
}
