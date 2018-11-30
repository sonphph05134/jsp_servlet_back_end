/*
 *== File Name: CreateProductController.java
 *== Project: assignment-backend
 *== Package: io.sonph.assignment.controllers
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
import io.sonph.assignment.dao.CategoryDao;
import io.sonph.assignment.dao.ProductDao;
import io.sonph.assignment.model.Account;
import io.sonph.assignment.model.Category;
import io.sonph.assignment.model.Product;



/**
 * Assignment Demo -> CreateProductController
 *
 * @author duongnguyen
 */
public class CreateProductController extends HttpServlet {

	/** Serial Version UID */
	private static final long serialVersionUID = -7636109188540673941L;

	/** Category Data Accessing Object */
	private CategoryDao categoryDao;

	/** Product Data Accessing Object */
	private ProductDao productDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateProductController() {
		super();
	}

	/**
	 * @see GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {

		// Instance new DAO
		this.categoryDao		= new CategoryDao();
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
		List<Category> listCategories = this.categoryDao.getListCategories();

		// Set data into request scope
		request.setAttribute("listCategories", listCategories);

		// Redirect to list categories JSP
		RequestDispatcher dispatcher = request.getServletContext()
														.getRequestDispatcher("/views/product/create.jsp");
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

		// Declare parameters
		String code = null;
		String name = null;
		String categoryId = null;
		String unitPrice = null;
		String image = "data:${imageType};base64,";
		byte imageBytes[] = null;

		// Try to read data
		try {

			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// Parse the request
			List<FileItem> items = upload.parseRequest(request);

			// Process the uploaded items
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {

				FileItem item = iter.next();
				String inputName = item.getFieldName();

				if (inputName.endsWith("code")) {
					code = item.getString(CommonConst.REQEUST_CHARACTER_ENCODING_UTF8);
				} else if (inputName.endsWith("name")) {
					name = item.getString(CommonConst.REQEUST_CHARACTER_ENCODING_UTF8);
				} else if (inputName.endsWith("categoryId")) {
					categoryId = item.getString(CommonConst.REQEUST_CHARACTER_ENCODING_UTF8);
				} else if (inputName.endsWith("unitPrice")) {
					unitPrice = item.getString(CommonConst.REQEUST_CHARACTER_ENCODING_UTF8);
				} else if (inputName.endsWith("image") && !item.isFormField()) {
					image =image.replace("${imageType}", item.getContentType());
					imageBytes = item.get();
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		// Validate parameters
		boolean hasInvalid = false;

		// Validate code
		if (StringUtils.isBlank(code)) {
			request.setAttribute("errCode", "Code is reuqired field.");
			hasInvalid = true;
		} else if (code.length() > 20) {
			request.setAttribute("errCode", "Code must not be greater than 20 characters.");
			hasInvalid = true;
		}

		// Validate name
		if (StringUtils.isBlank(name)) {
			request.setAttribute("errName", "Name is reuqired field.");
			hasInvalid = true;
		} else if (name.length() > 255) {
			request.setAttribute("errName", "Name must not be greater than 255 characters.");
			hasInvalid = true;
		}

		// Validate category
		if (StringUtils.isBlank(categoryId)) {
			request.setAttribute("errCategoryId", "Category is reuqired field.");
			hasInvalid = true;
		} else if (!StringUtils.isNumeric(categoryId)) {
			request.setAttribute("errCategoryId", "Category must be a number.");
			hasInvalid = true;
		}

		// Validate unitPrice
		if (StringUtils.isBlank(unitPrice)) {
			request.setAttribute("errUnitPrice", "Unit price is reuqired field.");
			hasInvalid = true;
		} else if (!StringUtils.isNumeric(unitPrice)) {
			request.setAttribute("errUnitPrice", "Unit price must be a number.");
			hasInvalid = true;
		}

		// Check invalid flag
		if (hasInvalid) {

			// Set invalid flag
			request.setAttribute("hasInvalid", true);

			// Get data from database
			List<Category> listCategories = this.categoryDao.getListCategories();

			// Set data into request scope
			request.setAttribute("listCategories", listCategories);

			// Forward to login page with invalid message
			RequestDispatcher dispatcher = request.getServletContext()
														.getRequestDispatcher("/views/product/create.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// Check duplicate code
		if (this.productDao.getProduct(code) != null) {

			// Set error flag
			request.setAttribute("hasError", true);
			request.setAttribute("errMessage", "The code [" + code + "] was used before. Please use other code");

			// Get data from database
			List<Category> listCategories = this.categoryDao.getListCategories();

			// Set data into request scope
			request.setAttribute("listCategories", listCategories);

			// Forward to login page with invalid message
			RequestDispatcher dispatcher = request.getServletContext()
														.getRequestDispatcher("/views/product/create.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// Convert image to base 64 encoding
		String imageEncoded = null;
		if (imageBytes != null) {
			// Convert to base64 encoding string
			imageEncoded = image + Base64.getEncoder().encodeToString(imageBytes);
		}

		// Create product model
		Product product = new  Product();
		product.setCode(code);
		product.setName(name);
		product.setCategoryId(Integer.parseInt(categoryId));
		product.setUnitPrice(Integer.parseInt(unitPrice));
		product.setImage(imageEncoded);

		// Save product
		this.productDao.createProduct(product);

		// Return to list products screen
		response.sendRedirect(request.getContextPath() + "/list-products");
	}
}
