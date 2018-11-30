/*
 *== File Name: Detail.java
 *== Project: assignment-backend
 *== Package: io.sonph.assignment.util
 */
package io.sonph.assignment.model;

/**
 * Assignment Demo -> Details
 *
 * @author sonph
 */
public class Detail implements java.io.Serializable {

	/** Serial Version UID */
	private static final long serialVersionUID = 4455228973025395790L;

	/** Details.ID */
	private Integer id;

	/** Details.CartId */
	private Integer cartId;

	/** Details.ProductId */
	private Integer productId;

	/** Details.Quantity */
	private Integer quantity;

	/**
	 * Default Constructor
	 */
	public Detail() {
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
	 * @return the cartId
	 */
	public Integer getCartId() {
		return this.cartId;
	}

	/**
	 * @param cartId the cartId to set
	 */
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return this.productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return this.quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
