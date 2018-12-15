/**
 * 
 */
package net.needii.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartItemDto extends BaseModelResponseImpl {
	
	private long id;
	
	@JsonProperty("product_id")
	private Long productId;
	
	@JsonProperty("sku_id")
	private long skuId;
	
	@JsonProperty("sku_image")
	private String skuImage;
	
	@JsonProperty("sku_name")
	private String skuName;
	
	private int quantity;
	
	@JsonProperty("unit_price")
	private float unitPrice;
	
	@JsonProperty("total_price")
	private float totalPrice;
	
	@JsonProperty("cashback")
	private float cashback;
	
	private SkuDto sku;
	
	private CartDto cart;
	
	private float shippingFee;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * @return the skuId
	 */
	public long getSkuId() {
		return skuId;
	}

	/**
	 * @param skuId the skuId to set
	 */
	public void setSkuId(int skuId) {
		this.skuId = skuId;
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
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getSkuImage() {
		return skuImage;
	}

	public void setSkuImage(String skuImage) {
		this.skuImage = skuImage;
	}

	public float getCashback() {
		return cashback;
	}

	public void setCashback(float cashback) {
		this.cashback = cashback;
	}

	public SkuDto getSku() {
		return sku;
	}

	public void setSku(SkuDto sku) {
		this.sku = sku;
	}

	public CartDto getCart() {
		return cart;
	}

	public void setCart(CartDto cart) {
		this.cart = cart;
	}
	
	public void calculateAndSetCashback(float cashbackPercent) {
		if(cashbackPercent > 0) {
			this.cashback = (this.totalPrice / 100) * cashbackPercent;
		} else {
			this.cashback = 0;
		}
		
	}

	public float getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(float shippingFee) {
		this.shippingFee = shippingFee;
	}
	
	
	
}