/**
 * 
 */
package net.needii.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author vincent
 *
 */
public class SupplierPersonalRegisterRequest extends BaseRequest {
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("phone")
	private String phone;
	
	@JsonProperty("fullName")
	private String fullName;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("fullTextAddress")
	private String fullTextAddress;
	
	@JsonProperty("latitude")
	private String latitude;
	
	@JsonProperty("longitude")
	private String longitute;
	
	@JsonProperty("bankAccount")
	private String bankAccount;
	
	@JsonProperty("bankAccountName")
	private String bankAccountName;
	
	@JsonProperty("bankName")
	private String bankName;
	
	@JsonProperty("bankBranch")
	private String bankBranch;
	
	@JsonProperty("identifyNo")
	private String identifyNo;
	
	@JsonProperty("identifyImage")
	private String identifyImage;
	
	
	@JsonProperty("identifyPlace")
	private String identifyPlace;
	
	@JsonProperty("identifyDate")
	private String identifyDate;
	
	@JsonProperty("logoURL")
	private String logoURL;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullTextAddress() {
		return fullTextAddress;
	}
	public void setFullTextAddress(String fullTextAddress) {
		this.fullTextAddress = fullTextAddress;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBankAccountName() {
		return bankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}
	
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getIdentifyNo() {
		return identifyNo;
	}
	public void setIdentifyNo(String identifyNo) {
		this.identifyNo = identifyNo;
	}
	
	public String getIdentifyImage() {
		return identifyImage;
	}
	public void setIdentifyImage(String identifyImage) {
		this.identifyImage = identifyImage;
	}
	public String getIdentifyPlace() {
		return identifyPlace;
	}
	public void setIdentifyPlace(String identifyPlace) {
		this.identifyPlace = identifyPlace;
	}
	public String getIdentifyDate() {
		return identifyDate;
	}
	public void setIdentifyDate(String identifyDate) {
		this.identifyDate = identifyDate;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitute() {
		return longitute;
	}
	public void setLongitute(String longitute) {
		this.longitute = longitute;
	}
	public String getLogoURL() {
		return logoURL;
	}
	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}
	
	

}
