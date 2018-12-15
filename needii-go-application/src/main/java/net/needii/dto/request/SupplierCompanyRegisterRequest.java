/**
 * 
 */
package net.needii.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author vincent
 *
 */
public class SupplierCompanyRegisterRequest extends BaseRequest {
	
	@JsonProperty("phone")
	private String phone;
	
	@JsonProperty("companyLegalPresetativeName")
	private String companyLegalPresetativeName;
	
	
	@JsonProperty("companyName")
	private String companyName;
	
	@JsonProperty("categoryId")
	private String categoryId;
	
	@JsonProperty("title")
	private String title;
	
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
	
	@JsonProperty("licenceNo")
	private String licenceNo;
	
	@JsonProperty("companyImage")
	private String companyImage;
	
	
	@JsonProperty("licencePlace")
	private String licencePlace;
	
	@JsonProperty("licenceDate")
	private String licenceDate;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	public String getCompanyLegalPresetativeName() {
		return companyLegalPresetativeName;
	}

	public void setCompanyLegalPresetativeName(String companyLegalPresetativeName) {
		this.companyLegalPresetativeName = companyLegalPresetativeName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getLicenceNo() {
		return licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	public String getCompanyImage() {
		return companyImage;
	}

	public void setCompanyImage(String companyImage) {
		this.companyImage = companyImage;
	}

	public String getLicencePlace() {
		return licencePlace;
	}

	public void setLicencePlace(String licencePlace) {
		this.licencePlace = licencePlace;
	}

	public String getLicenceDate() {
		return licenceDate;
	}

	public void setLicenceDate(String licenceDate) {
		this.licenceDate = licenceDate;
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
}
