/*
 *== File Name: CommonConst.java
 *== Project: assignment-backend
 *== Package: io.sonph.assignment.util
 */
package io.sonph.assignment.common;

/**
 * Assignment Demo -> Common Constant
 *
 * @author sonph
 */
public final class CommonConst {

	/**
	 * Private Constructor to make sure class cannot instance
	 */
	private CommonConst() {
	}

	/** Request Character encoding */
	public static final String REQEUST_CHARACTER_ENCODING_UTF8 = "UTF-8";

	/** Session attribute: account */
	public static final String SESSION_ATTRIBUTE_ACCOUNT = "account";

	/** Authenticate: login path */
	public static final String AUTHETICATE_LOGIN_PATH = "/login";

	/** Authenticate: return path */
	public static final String AUTHETICATE_RETURN_PATH = "/";

	/** User type: admin */
	public static final String USER_TYPE_ADMIN = "admin";
}
