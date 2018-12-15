/**
 * 
 */
package net.needii.dto.request;


import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.needii.jpa.entity.SocialTypeEnum;

/**
 * @author longnguyen
 *
 */
public class ConnectSocialRequest extends BaseRequest {
	
	@JsonProperty("full_name")
	private String fullName;
	
	private String email;
	
	private String avatar;
	
	@JsonProperty("social_uid")
	@NotEmpty(message="CustomerDto.SocialUID.Empty")
	private String socialUID;
	
	@JsonProperty("social_type")
	@Min(1)
	private int socialType;

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
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar == null ? "" : this.avatar;
	}

	/**
	 * @param avatar the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * @return the socialUID
	 */
	public String getSocialUID() {
		return socialUID;
	}

	/**
	 * @param socialUID the socialUID to set
	 */
	public void setSocialUID(String socialUID) {
		this.socialUID = socialUID;
	}

	/**
	 * @return the socialType
	 */
	public int getSocialType() {
		return socialType;
	}
	
	public SocialTypeEnum getSocialTypeEnum() {
		switch(this.socialType) {
		case 1:
			return SocialTypeEnum.FACEBOOK;
		case 2:
			return SocialTypeEnum.GOOGLE;
		default:
			return SocialTypeEnum.FACEBOOK;
		}
	}

	/**
	 * @param socialType the socialType to set
	 */
	public void setSocialType(int socialType) {
		this.socialType = socialType;
	}
}
