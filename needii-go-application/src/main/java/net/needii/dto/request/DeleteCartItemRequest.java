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
public class DeleteCartItemRequest extends BaseRequest {
	
	@JsonProperty("cart_id")
	@Min(1)
	private long cartId;
	
	@JsonProperty("cart_item_id")
	@Min(1)
	private long cartItemId;
	
	@JsonProperty("customer_id")
	@Min(1)
	private int customerId;
	
	@JsonProperty("device_uid")
	@NotEmpty(message="CustomerDto.DeviceUID.Empty")
	private String deviceUID;

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public long getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(long cartItemId) {
		this.cartItemId = cartItemId;
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
