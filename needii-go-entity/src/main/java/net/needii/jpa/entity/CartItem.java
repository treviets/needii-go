package net.needii.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the cart_items database table.
 * 
 */
@Entity
@Table(name="cart_items")
@NamedQuery(name="CartItem.findAll", query="SELECT c FROM CartItem c")
public class CartItem extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	
	@Column(name="sku_name")
	private String skuName;
	
	@Column(name="sku_image")
	private String skuImage;
	
	private int quantity;

	@Column(name="unit_price")
	private float unitPrice;
	
	@Column(name="shipping_fee")
	private float shippingFee;
	
	@Column(name="total_price")
	private float totalPrice;
	
	@Column(name="cashback")
	private float cashback;

	//bi-directional many-to-one association to Cart
	@ManyToOne
	private Cart cart;

	@Column(name="product_id")
	private Long productId;
	
	@ManyToOne
	@JoinColumn(name = "sku_id")
	private Skus sku;

	public CartItem() {
		this.shippingFee = 0;
		this.cashback = 0;
		this.setIsDeleted(false);
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Cart getCart() {
		return this.cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Skus getSku() {
		return sku;
	}

	public void setSku(Skus sku) {
		this.sku = sku;
	}

	public float getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(float shippingFee) {
		this.shippingFee = shippingFee;
	}

	/**
	 * @return the skuName
	 */
	public String getSkuName() {
		return skuName;
	}

	/**
	 * @param skuName the skuName to set
	 */
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	/**
	 * @return the skuImage
	 */
	public String getSkuImage() {
		return skuImage;
	}

	/**
	 * @param skuImage the skuImage to set
	 */
	public void setSkuImage(String skuImage) {
		this.skuImage = skuImage;
	}

	public float getCashback() {
		return cashback;
	}

	public void setCashback(float cashback) {
		this.cashback = cashback;
	}

}