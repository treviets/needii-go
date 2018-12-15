/**
 * 
 */
package net.needii.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author kelvin
 *
 */
public class CustomerDto {
	
	private Long id;
	
	private String fullName;

	@JsonIgnore
	private String password;

	private String phone;
	
	private String email;
	
	@JsonProperty("auth_type")
	private int authType;

	private Date birthday;

	private String avatar;

	@JsonProperty("favorite_product_ids")
	private String favoriteProductIds;

	@JsonProperty("needii_cash")
	private float neediiCash;

	private int gender;

	@JsonProperty("last_login_at")
	private Date lastLoginAt;

	@JsonProperty("reference_at")
	private Date referenceAt;

	@JsonProperty("verify_code")
	private String verifyCode;

	@JsonProperty("is_authenticated")
	private boolean isAuthenticated;

	@JsonProperty("reference_code")
	private String referenceCode;

	@JsonProperty("reference_by")
	private long referenceBy;

	@JsonProperty("facebook_uid")
	private String facebookUID;

	@JsonProperty("google_uid")
	private String googleUID;

	private boolean status;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAuthType() {
		return authType;
	}

	public void setAuthType(int authType) {
		this.authType = authType;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getFavoriteProductIds() {
		return favoriteProductIds;
	}

	public void setFavoriteProductIds(String favoriteProductIds) {
		this.favoriteProductIds = favoriteProductIds;
	}

	public float getNeediiCash() {
		return neediiCash;
	}

	public void setNeediiCash(float neediiCash) {
		this.neediiCash = neediiCash;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getLastLoginAt() {
		return lastLoginAt;
	}

	public void setLastLoginAt(Date lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}

	public Date getReferenceAt() {
		return referenceAt;
	}

	public void setReferenceAt(Date referenceAt) {
		this.referenceAt = referenceAt;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}

	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

	public long getReferenceBy() {
		return referenceBy;
	}

	public void setReferenceBy(long referenceBy) {
		this.referenceBy = referenceBy;
	}

	public String getFacebookUID() {
		return facebookUID;
	}

	public void setFacebookUID(String facebookUID) {
		this.facebookUID = facebookUID;
	}

	public String getGoogleUID() {
		return googleUID;
	}

	public void setGoogleUID(String googleUID) {
		this.googleUID = googleUID;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	
	
	
}
