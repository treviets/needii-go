/**
 * 
 */
package net.needii.dto.request;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author longnguyen
 *
 */
public class AddFavoriteProductRequest extends BaseRequest {
	
	@JsonProperty("product_ids")
	private List<Long> productIds = new ArrayList<Long>();

	public List<Long> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<Long> productIds) {
		this.productIds = productIds;
	}

}
