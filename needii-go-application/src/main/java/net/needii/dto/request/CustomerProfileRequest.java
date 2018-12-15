/**
 * 
 */
package net.needii.dto.request;


import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import util.validate.EmailValidatorConstraint;

/**
 * @author longnguyen
 *
 */
public class CustomerProfileRequest extends BaseRequest {
	
	@JsonProperty("full_name")
	@NotEmpty(message = "CustomerDto.NotEmpty.FullName")
	private String fullName;
	
	@EmailValidatorConstraint(message="CustomerDto.Email.Invalid")
	private String email;
	
	private String birthday;

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}
