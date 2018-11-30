/*
 *== File Name: Account.java
 *== Project: assignment-backend
 *== Package: io.sonph.assignment.util
 */
package io.sonph.assignment.model;

/**
 * Assignment Demo -> Accounts
 *
 * @author sonph
 */
public class Account implements java.io.Serializable {

	/** Serial Version UID */
	private static final long serialVersionUID = 2054033959352550045L;

	/** Accounts.Id */
	private Integer id;

	/** Accounts.Username */
	private String username;

	/** Accounts.Password */
	private String password;

	/** Accounts.Type */
	private String type;

	/** Accounts.Email */
	private String email;

	/** Accounts.FullName */
	private String fullName;

	/** Accounts.Address */
	private String address;

	/**
	 * Default constructor
	 */
	public Account() {
		super();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return this.fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
}
