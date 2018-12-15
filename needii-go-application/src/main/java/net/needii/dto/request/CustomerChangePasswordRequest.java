/**
 * 
 */
package net.needii.dto.request;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import util.Utils;

/**
 * @author longnguyen
 *
 */
public class CustomerChangePasswordRequest extends BaseRequest {
	
	@JsonProperty("old_password")
	@NotEmpty(message = "customer.oldpassword.notempty")
	private String oldPassword;
	
	@JsonProperty("new_password")
	@NotEmpty(message = "customer.newpassword.notempty")
	private String newPassword;

	public String getOldPassword() {
		return Utils.removeAllNoneNumberic(oldPassword);
	}

	public String getNewPassword() {
		return Utils.removeAllNoneNumberic(newPassword);
	}
}
