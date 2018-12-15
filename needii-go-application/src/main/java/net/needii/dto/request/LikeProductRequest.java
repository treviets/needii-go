/**
 * 
 */
package net.needii.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author longnguyen
 *
 */
public class LikeProductRequest extends BaseRequest {
	
	@JsonProperty("product_id")
	private Long productId;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
