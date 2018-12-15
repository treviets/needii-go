package net.needii.jpa.entity;

import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the carts database table.
 * 
 */
@Entity
@Table(name="carts")
@NamedQuery(name="Cart.findAll", query="SELECT c FROM Cart c")
public class Cart extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	
	@Column(name="device_uid")
	private String deviceUID;
	
	@Column(name="promotion_id")
	private int promotionCodeId;
	
	@Column(name="total_promotion_price")
	private float promotionPrice;
	
	@Column(name="price")
	private float price;
	
	@Column(name="total_shipping_fee")
	private float shippingFee;
	
	@Column(name="total_price")
	private float totalPrice;
	
	@Column(name="total_cashback")
	private float totalCashback;
	
	//bi-directional many-to-one association to CartItem
	@OneToMany(mappedBy="cart")
	private List<CartItem> cartItems;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;

	public Cart() {
		this.promotionCodeId = 0;
		this.promotionPrice = 0;
		this.price = 0;
		this.shippingFee = 0;
		this.totalPrice = 0;
		this.totalCashback = 0;
	}

	public long getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<CartItem> getCartItems() {
		return this.cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the deviceUID
	 */
	public String getDeviceUID() {
		return deviceUID;
	}

	/**
	 * @param deviceUID the deviceUID to set
	 */
	public void setDeviceUID(String deviceUID) {
		this.deviceUID = deviceUID;
	}

	/**
	 * @return the promotionCodeId
	 */
	public int getPromotionCodeId() {
		return promotionCodeId;
	}

	/**
	 * @param promotionCodeId the promotionCodeId to set
	 */
	public void setPromotionCodeId(int promotionCodeId) {
		this.promotionCodeId = promotionCodeId;
	}

	/**
	 * @return the promotionPrice
	 */
	public float getPromotionPrice() {
		return promotionPrice;
	}

	/**
	 * @param promotionPrice the promotionPrice to set
	 */
	public void setPromotionPrice(float promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(float shippingFee) {
		this.shippingFee = shippingFee;
	}

	public float getTotalCashback() {
		return totalCashback;
	}

	public void setTotalCashback(float totalCashback) {
		this.totalCashback = totalCashback;
	}

}