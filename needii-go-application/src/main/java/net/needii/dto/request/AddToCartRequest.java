/**
 * 
 */
package net.needii.dto.request;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author longnguyen
 *
 */
public class AddToCartRequest extends BaseRequest {
	
	@JsonProperty("product_id")
	@Min(1)
	private Long productId;
	
	@JsonProperty("sku_id")
	@Min(1)
	private int skuId;
	
	private int quantity;
	
	@JsonProperty("customer_id")
	@Min(0)
	private int customerId;
	
	@JsonProperty("device_uid")
	@NotEmpty(message="CustomerDto.DeviceUID.Empty")
	private String deviceUID;

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
	public int getSkuId() {
		return skuId;
	}

	/**
	 * @param skuId the skuId to set
	 */
	public void setSkuId(int skuId) {
		this.skuId = skuId;
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

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getDeviceUID() {
		return deviceUID;
	}

	public void setDeviceUID(String deviceUID) {
		this.deviceUID = deviceUID;
	}
}
