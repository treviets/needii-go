/**
 * 
 */
package net.needii.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.needii.jpa.entity.Customer;
import util.Constants;

public class CustomerProfileDto extends BaseModelResponseImpl {
	
	private Long id;
	
	@JsonProperty("full_name")
	private String fullName;
	
	private String email;
	
	private String phone;
	
	private String birthday;
	
	private String avatar;
	
	private int gender;
	
	@JsonProperty("needii_cash")
	private float neediiCash;
	
	@JsonProperty("is_connect_with_facebook")
	private boolean isConnectWithFacebook;
	
	@JsonProperty("is_connect_with_google")
	private boolean isConnectWithGoogle;
	
	@JsonProperty("reference_code")
	private String referenceCode;
	
	public CustomerProfileDto() {
	}
	
	public CustomerProfileDto(Customer entity) {
		this.id = entity.getId();
		this.fullName = entity.getFullName();
		this.email = entity.getEmail();
		this.phone = entity.getPhone();
		this.birthday = entity.getBirthdayFormatVN();
		this.avatar = Constants.RESOURCE_DOMAIN + entity.getAvatar();
		this.gender = entity.getGender();
		this.neediiCash = entity.getNeediiCash();
		this.referenceCode = entity.getReferenceCode();
		if(!entity.getFacebookUID().isEmpty()) {
			this.isConnectWithFacebook = true;
		}
		if(!entity.getGoogleUID().isEmpty()) {
			this.isConnectWithGoogle = true;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

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
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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

	/**
	 * @return the gender
	 */
	public int getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}

	public float getNeediiCash() {
		return neediiCash;
	}

	public void setNeediiCash(float neediiCash) {
		this.neediiCash = neediiCash;
	}

	public boolean getIsConnectWithFacebook() {
		return isConnectWithFacebook;
	}

	public void setIsConnectWithFacebook(boolean isConnectWithFacebook) {
		this.isConnectWithFacebook = isConnectWithFacebook;
	}

	public boolean getIsConnectWithGoogle() {
		return isConnectWithGoogle;
	}

	public void setIsConnectWithGoogle(boolean isConnectWithGoogle) {
		this.isConnectWithGoogle = isConnectWithGoogle;
	}

	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

	public String getAvatar() {
		if(avatar != null && !avatar.contains("http:")) {
			return Constants.RESOURCE_DOMAIN + avatar;
		}
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}