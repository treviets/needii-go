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
public class ReviewSupplierRequest extends BaseRequest {
	
	@JsonProperty("supplier_id")
	@Min(value=1, message="Supplier.Id.Required")
	private int supplierId;
	
	@JsonProperty("customer_name")
	@NotEmpty(message = "SupplierReview.CustomerName.Empty")
	private String customerName;
	
	@NotEmpty(message = "SupplierReview.Content.Empty")
	private String content;
	
	@JsonProperty("star_rate")
	private int starRate;

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the starRate
	 */
	public int getStarRate() {
		if(starRate > 5) {
			starRate = 5;
		}
		if(starRate <=  0) {
			starRate = 3;
		}
		return starRate;
	}

	/**
	 * @param starRate the starRate to set
	 */
	public void setStarRate(int starRate) {
		this.starRate = starRate;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}
