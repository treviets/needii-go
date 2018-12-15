/**
 * 
 */
package net.needii.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Vincent
 *
 */
@Entity
@Table(name = "customers")
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
public class Customer extends BaseUser {
	private static final long serialVersionUID = 1L;

	@Column(name = "auth_type")
	private int authType;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	private String avatar;

	@Column(name = "favorite_product_ids")
	private String favoriteProductIds;

	@Column(name = "needii_cash")
	private float neediiCash;

	private int gender;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login_at")
	private Date lastLoginAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reference_at")
	private Date referenceAt;

	@Column(name = "verify_code")
	private String verifyCode;

	@Column(name = "is_authenticated")
	private boolean isAuthenticated;

	@Column(name = "reference_code")
	private String referenceCode;

	@Column(name = "reference_by")
	private long referenceBy;

	@Column(name = "facebook_uid")
	private String facebookUID;

	@Column(name = "google_uid")
	private String googleUID;

	private boolean status;

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
