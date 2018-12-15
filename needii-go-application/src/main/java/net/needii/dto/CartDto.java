/**
 * 
 */
package net.needii.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartDto extends BaseModelResponseImpl {
	
	private long id;
	
	private float price;
	
	@JsonProperty("shipping_fee")
	private float shippingFee;
	
	@JsonProperty("total_price")
	private float totalPrice;
	
	@JsonProperty("total_cashback")
	private float totalCashback;
	
	@JsonProperty("cart_items")
	private List<CartItemDto> cartItems;
	
	private String deviceUID;
	
	private CustomerDto customer;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * @return the shippingFee
	 */
	public float getShippingFee() {
		return shippingFee;
	}

	/**
	 * @param shippingFee the shippingFee to set
	 */
	public void setShippingFee(float shippingFee) {
		this.shippingFee = shippingFee;
	}

	/**
	 * @return the totalPrice
	 */
	public float getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the cartItems
	 */
	public List<CartItemDto> getCartItems() {
		return cartItems;
	}

	/**
	 * @param cartItems the cartItems to set
	 */
	public void setCartItems(List<CartItemDto> cartItems) {
		this.cartItems = cartItems;
	}

	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

	public String getDeviceUID() {
		return deviceUID;
	}

	public void setDeviceUID(String deviceUID) {
		this.deviceUID = deviceUID;
	}

	public float getTotalCashback() {
		return totalCashback;
	}

	public void setTotalCashback(float totalCashback) {
		this.totalCashback = totalCashback;
	}
	
	
	
}