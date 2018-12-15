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
public class LoginRequest extends BaseRequest {
	@JsonProperty("phone")
	@PhoneValidatorConstraint
	private String phone;
	
	@JsonProperty("password")
	@NotEmpty
	private String password;
	
	@JsonProperty("device_uid")
	@NotEmpty
	private String deviceUID;

	public String getPhone() {
		return Utils.removeAllNoneNumberic(phone);
	}

	public String getPassword() {
		return password;
	}

	public String getDeviceUID() {
		return deviceUID;
	}
}
