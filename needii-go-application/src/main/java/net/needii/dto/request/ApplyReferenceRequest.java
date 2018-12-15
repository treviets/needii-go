/**
 * 
 */
package net.needii.dto.request;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author longnguyen
 *
 */
public class ApplyReferenceRequest extends BaseRequest {
	
	@JsonProperty("reference_code")
	@NotBlank(message = "CustomerDto.ReferenceCode.Empty")
	private String referenceCode;

	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}
}
