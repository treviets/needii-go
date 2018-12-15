package net.needii.jpa.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import util.validate.EmailValidatorConstraint;
import util.validate.PhoneValidatorConstraint;


/**
 * The persistent class for the suppliers database table.
 * 
 */
@Entity
@Table(name="suppliers")
@NamedQuery(name="Supplier.findAll", query="SELECT s FROM Supplier s")
public class Supplier extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="full_name")
	private String fullName;

	@PhoneValidatorConstraint
	private String phone;
	
	@EmailValidatorConstraint
	private String email;
	
	@Column(name="city_id")
	private int cityId;
	
	@Column(name="district_id")
	private int districtId;
	
	@Column(name="ward_id")
	private int wardId;
	
	@Column(name="street_number")
	private String streetNumber;
	
	@Column(name="full_text_address")
	private String fullTextAddress;

	private String information;

	@Column(name="profile_approve")
	private int profileApprove;

	@Column(name="reject_reason")
	private String rejectReason;
	
	@Column(name="logo_url")
	private String logoUrl;
	
	private int status;
	
	@Column(name="like_count")
	private Integer likeCount;
	
	@Column(name="subscribe_count")
	private Integer subscribeCount;
	
	@Column(name="share_count")
	private Integer shareCount;

	private String term;

	private String lat;

	private String lng;

    @NotFound(action=NotFoundAction.IGNORE)
    @ManyToOne
    @JoinColumn(name="category_id")
	private Category category;

    @NotFound(action=NotFoundAction.IGNORE)
	@OneToOne(mappedBy="supplier", cascade=CascadeType.ALL)
	private SupplierBankInfo bankInfo;
	
	@OneToOne(mappedBy="supplier", cascade=CascadeType.ALL)
	private SupplierCompanyProfile companyProfile;
	
	@OneToMany(mappedBy="supplier")
	private Set<OrderDetail> orderDetails;

    @NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="supplier_role_id")
	private SupplierRole supplierRole;
	
	//bi-directional many-to-one association to SupplierAddress
	@OneToMany(mappedBy="supplier")
	private Set<SupplierAddress> supplierAddresses = new HashSet<SupplierAddress>();

	@Column(name="identify_no")
	private String identifyNo;
	
	@Column(name="identify_place")
	private String identifyPlace;
	
	@Column(name="identify_date")
	private String identifyDate;
	
	@Column(name="identify_image")
	private String identifyImage;
	

	public SupplierBankInfo getBankInfo() {
		return bankInfo;
	}

	public void setBankInfo(SupplierBankInfo bankInfo) {
		this.bankInfo = bankInfo;
	}

	public SupplierCompanyProfile getCompanyProfile() {
		return companyProfile;
	}

	public void setCompanyProfile(SupplierCompanyProfile companyProfile) {
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

	public Supplier() {
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
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	/**
	 * @return the cityId
	 */
	public int getCityId() {
		return cityId;
	}

	/**
	 * @param cityId the cityId to set
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
	 * @param wardId the wardId to set
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
	 * @param districtId the districtId to set
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
	 * @param streetNumber the streetNumber to set
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public List<SupplierAddress> getSupplierAddressList() {
		List<SupplierAddress> list = new ArrayList<SupplierAddress>(this.supplierAddresses);
		return list;
	}
	
	public void setSupplierAddresses(List<SupplierAddress> supplierAddresses) {
		Set<SupplierAddress> set = new HashSet<SupplierAddress>(supplierAddresses);
		this.supplierAddresses = set;
	}
	
	public void setSupplierAddress(SupplierAddress supplierAddress) {
		Set<SupplierAddress> set = new HashSet<SupplierAddress>();
		set.add(supplierAddress);
		this.supplierAddresses = set;
	}

	public String getFullTextAddress() {
		return fullTextAddress;
	}

	public void setFullTextAddress(String fullTextAddress) {
		this.fullTextAddress = fullTextAddress;
	}

	public SupplierRole getSupplierRole() {
		return supplierRole;
	}

	public void setSupplierRole(SupplierRole supplierRole) {
		this.supplierRole = supplierRole;
	}

	public List<OrderDetail> getOrderDetails() {
		List<OrderDetail> list = new ArrayList<OrderDetail>(this.orderDetails);
		return list;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
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

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
	
	public int isActive() {
		return this.status;
	}

	public String getIdentifyImage() {
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
	 * @param id the id to set
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
	 * @param fullName the fullName to set
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
	 * @param phone the phone to set
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
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}