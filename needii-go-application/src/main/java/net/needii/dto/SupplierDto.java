package net.needii.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import util.Constants;

public class SupplierDto extends BaseModelResponseImpl {

	private Long id;

	@JsonProperty("full_name")
	private String fullName;

	private String phone;

	private String email;

	@JsonProperty("city_id")
	private int cityId;

	@JsonProperty("district_id")
	private int districtId;

	@JsonProperty("ward_id")
	private int wardId;

	@JsonProperty("street_number")
	private String streetNumber;

	@JsonProperty("full_text_address")
	private String fullTextAddress;

	private String information;

	@JsonProperty("profile_approve")
	private int profileApprove;

	@JsonProperty("reject_reason")
	private String rejectReason;

	@JsonProperty("logo_url")
	private String logoUrl;

	private int status;

	@JsonProperty("like_count")
	private Integer likeCount;

	@JsonProperty("subscribe_count")
	private Integer subscribeCount;

	@JsonProperty("share_count")
	private Integer shareCount;

	private String term;

	private String lat;

	private String lng;
	
	@JsonProperty("role_id")
	private int roleId;

	@JsonProperty("is_like")
	private boolean isLike;
	
	@JsonProperty("is_subscribe")
	private boolean isSubscribe;

	private CategoryDto category;

	private SupplierBankInfoDto bankInfo;

	private SupplierCompanyProfileDto companyProfile;

	@JsonIgnore
	private Set<OrderDetailDto> orderDetails;

	@JsonIgnore
	private SupplierRoleDto supplierRole;
	@JsonIgnore
	private Set<SupplierAddressDto> supplierAddresses;

	@JsonProperty("identify_no")
	private String identifyNo;

	@JsonProperty("identify_place")
	private String identifyPlace;

	@JsonProperty("identify_date")
	private String identifyDate;

	@JsonProperty("identify_image")
	private String identifyImage;

	public SupplierBankInfoDto getBankInfo() {
		return bankInfo;
	}

	public void setBankInfo(SupplierBankInfoDto bankInfo) {
		this.bankInfo = bankInfo;
	}

	public SupplierCompanyProfileDto getCompanyProfile() {
		return companyProfile;
	}

	public void setCompanyProfile(SupplierCompanyProfileDto companyProfile) {
		this.companyProfile = companyProfile;
	}

	public String getIdentifyNo() {
		return identifyNo;
	}

	public void setIdentifyNo(String identifyNo) {
		this.identifyNo = identifyNo;
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

	public SupplierDto() {
		this.status = 0;
	}

	public String getInformation() {
		return this.information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public int getProfileApprove() {
		return this.profileApprove;
	}

	public void setProfileApprove(int profileApprove) {
		this.profileApprove = profileApprove;
	}

	public String getRejectReason() {
		return this.rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTerm() {
		return this.term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getLogoUrl() {
		if (logoUrl != null && !logoUrl.contains("http")) {
			return Constants.RESOURCE_DOMAIN + logoUrl;
		}
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the cityId
	 */
	public int getCityId() {
		return cityId;
	}

	/**
	 * @param cityId
	 *            the cityId to set
	 */
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return the wardId
	 */
	public int getWardId() {
		return wardId;
	}

	/**
	 * @param wardId
	 *            the wardId to set
	 */
	public void setWardId(int wardId) {
		this.wardId = wardId;
	}

	/**
	 * @return the districtId
	 */
	public int getDistrictId() {
		return districtId;
	}

	/**
	 * @param districtId
	 *            the districtId to set
	 */
	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	/**
	 * @return the streetNumber
	 */
	public String getStreetNumber() {
		return streetNumber;
	}

	/**
	 * @param streetNumber
	 *            the streetNumber to set
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public Set<SupplierAddressDto> getSupplierAddressList() {
		return this.supplierAddresses;
	}

	public void setSupplierAddresses(Set<SupplierAddressDto> supplierAddresses) {
		this.supplierAddresses = supplierAddresses;
	}

	public void setSupplierAddress(SupplierAddressDto supplierAddress) {
		Set<SupplierAddressDto> set = new HashSet<SupplierAddressDto>();
		set.add(supplierAddress);
		this.supplierAddresses = set;
	}

	public String getFullTextAddress() {
		return fullTextAddress;
	}

	public void setFullTextAddress(String fullTextAddress) {
		this.fullTextAddress = fullTextAddress;
	}

	public SupplierRoleDto getSupplierRole() {
		return supplierRole;
	}

	public void setSupplierRole(SupplierRoleDto supplierRole) {
		this.supplierRole = supplierRole;
	}

	public List<OrderDetailDto> getOrderDetails() {
		if(orderDetails != null) {
			List<OrderDetailDto> list = new ArrayList<OrderDetailDto>(this.orderDetails);
			return list;
		}
		return null;
	}

	public void setOrderDetails(Set<OrderDetailDto> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Integer getLikeCount() {
		return likeCount == null ? 0 : likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = (likeCount < 0 || likeCount == null) ? 0 : likeCount;
	}

	public int getSubscribeCount() {
		return subscribeCount == null ? 0 : subscribeCount;
	}

	public void setSubscribeCount(int subscribeCount) {
		this.subscribeCount = subscribeCount < 0 ? 0 : subscribeCount;
	}

	public int getShareCount() {
		return shareCount == null ? 0 : shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public CategoryDto getCategory() {
		return this.category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public int isActive() {
		return this.status;
	}

	public String getIdentifyImage() {
		if (identifyImage != null && !identifyImage.contains("http")) {
			return Constants.RESOURCE_DOMAIN + identifyImage;
		}
		return identifyImage;
	}

	public void setIdentifyImage(String identifyImage) {
		this.identifyImage = identifyImage;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getIsLike() {
		return isLike;
	}

	public void setIsLike(boolean isLike) {
		this.isLike = isLike;
	}

	public boolean getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(boolean isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public void setSubscribeCount(Integer subscribeCount) {
		this.subscribeCount = subscribeCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}

	
	
}
