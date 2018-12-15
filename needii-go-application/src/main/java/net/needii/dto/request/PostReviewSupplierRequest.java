/**
 * 
 */
package net.needii.dto.request;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * @author longnguyen
 *
 */
public class PostReviewSupplierRequest extends BaseRequest {
	
	@NotEmpty(message="Supplier.Review.Required")
	private List<ReviewSupplierRequest> reviews = new ArrayList<ReviewSupplierRequest>();

	public List<ReviewSupplierRequest> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewSupplierRequest> reviews) {
		this.reviews = reviews;
	}

}
