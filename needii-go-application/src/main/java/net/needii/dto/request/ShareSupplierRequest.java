/**
 * 
 */
package net.needii.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author longnguyen
 *
 */
public class ShareSupplierRequest extends BaseRequest {
	
	@JsonProperty("supplier_id")
	private int supplierId;

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

}
