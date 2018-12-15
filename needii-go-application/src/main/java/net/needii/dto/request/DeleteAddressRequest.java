/**
 * 
 */
package net.needii.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author longnguyen
 *
 */
public class DeleteAddressRequest extends BaseRequest {
	
	@JsonProperty("customer_address_id")
	private long customerAddressId;

	/**
	 * @return the customerAddressId
	 */
	public long getCustomerAddressId() {
		return customerAddressId;
	}

	/**
	 * @param customerAddressId the customerAddressId to set
	 */
	public void setCustomerAddressId(long customerAddressId) {
		this.customerAddressId = customerAddressId;
	}
}
