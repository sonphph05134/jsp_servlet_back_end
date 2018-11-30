/*
 *== File Name: HibernateUtil.java
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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;

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

//Fix lỗi form có chứa enctype="multipart/form-data"
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024
		* 100)
/**
 * Assignment Demo -> EditProductController
 *
 * @author sonph
 */
public class EditProductController extends HttpServlet {

	/** Serial Version UID */
	private static final long serialVersionUID = -8346149941753835451L;

	/** Category Data Accessing Object */
	private CategoryDao categoryDao;

	/** Product Data Accessing Object */
	private ProductDao productDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditProductController() {
		super();
	}

	/**
	 * @see GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {

		// Instance new DAO
		this.productDao = new ProductDao();
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

		// Find product
		Product product = this.productDao.getProduct(Integer.parseInt(id));

		// Set category into request
		request.setAttribute("product", product);

		// Get data from database
		List<Category> listCategories = this.categoryDao.getListCategories();

		// Set data into request scope
		request.setAttribute("listCategories", listCategories);

		// Redirect to edit product JSP
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/product/edit.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Fix lỗi tiếng việt
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

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
		/*
		 * // Declare parameters 
		 * String code = null; 
		 * String name = null; 
		 * String categoryId = null; 
		 * String unitPrice = null; 
		 * String image = "data:${imageType};base64,"; 
		 * byte imageBytes[] = null;
		 * 
		 * // Try to read data try {
		 * 
		 * // Create a factory for disk-based file items DiskFileItemFactory factory =
		 * new DiskFileItemFactory();
		 * 
		 * // Create a new file upload handler ServletFileUpload upload = new
		 * ServletFileUpload(factory);
		 * 
		 * // Parse the request List<FileItem> items = upload.parseRequest(request);
		 * 
		 * // Process the uploaded items Iterator<FileItem> iter = items.iterator();
		 * while (iter.hasNext()) {
		 * 
		 * FileItem item = iter.next(); String inputName = item.getFieldName();
		 * 
		 * if (inputName.endsWith("code")) { code =
		 * item.getString(CommonConst.REQEUST_CHARACTER_ENCODING_UTF8); } else if
		 * (inputName.endsWith("name")) { name =
		 * item.getString(CommonConst.REQEUST_CHARACTER_ENCODING_UTF8); } else if
		 * (inputName.endsWith("categoryId")) { categoryId =
		 * item.getString(CommonConst.REQEUST_CHARACTER_ENCODING_UTF8); } else if
		 * (inputName.endsWith("unitPrice")) { unitPrice =
		 * item.getString(CommonConst.REQEUST_CHARACTER_ENCODING_UTF8); } else if
		 * (inputName.endsWith("image") && !item.isFormField()) { image =
		 * image.replace("${imageType}", item.getContentType()); imageBytes =
		 * item.get(); } } } catch (FileUploadException e) { e.printStackTrace(); }
		 */
		// Declare parameters
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String categoryId = request.getParameter("categoryId");
		String unitPrice = request.getParameter("unitPrice");
		String image = request.getParameter("image");
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
					image = image.replace("${imageType}", item.getContentType());
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
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/product/edit.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// Convert image to base 64 encoding
		String imageEncoded = null; 
		if(imageBytes != null) { 
		// Convert to base64 encoding 
		imageEncoded = Base64.getEncoder().encodeToString(imageBytes); }

		// Create product model
		Product product = new Product();
		product.setCode(code);
		product.setName(name);
		product.setCategoryId(Integer.parseInt(categoryId));
		product.setUnitPrice(Integer.parseInt(unitPrice));
		product.setImage(imageEncoded);

		// Save product
		this.productDao.updateProduct(product);

		// Return to list products screen
		response.sendRedirect(request.getContextPath() + "/list-products");
	}
}
