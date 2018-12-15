/**
 * 
 */
package net.needii.dto.request;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import util.Utils;
import util.validate.PhoneValidatorConstraint;

/**
 * @author longnguyen
 *
 */
public class CustomerVerifyRequest extends BaseRequest {
	@JsonProperty("phone")
	@PhoneValidatorConstraint
	private String phone;
	
	@JsonProperty("verify_code")
	private String verifyCode;
	
	@JsonProperty("device_uid")
	@NotEmpty
	private String deviceUID;

	public String getPhone() {
		return Utils.removeAllNoneNumberic(phone);
	}

	public String getVerifyCode() {
		return verifyCode;
	}
	
	public String getDeviceUID() {
		return deviceUID;
	}
}
