/**
 * 
 */
package net.needii.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import util.Utils;
import util.validate.PhoneValidatorConstraint;

/**
 * @author longnguyen
 *
 */
public class CustomerRegisterRequest extends BaseRequest {
	
	@JsonProperty("phone")
	@PhoneValidatorConstraint
	private String phone;
	
	public String getPhone() {
		return Utils.removeAllNoneNumberic(phone);
	}

}
