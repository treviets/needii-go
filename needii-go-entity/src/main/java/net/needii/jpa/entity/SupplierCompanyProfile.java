package net.needii.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the supplier_addresses database table.
 * 
 */
@Entity
@Table(name="supplier_company_profile")
public class SupplierCompanyProfile extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id", nullable = true)
	private Supplier supplier;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="company_email")
	private String companyEmail;
	
	@Column(name="company_legal_representative")
	private String companyLegalRepresentative;
	
	@Column(name="title")
	private String title;

	@Column(name="address")
	private String address;
	
	@Column(name="lincense_number")
	private String lincenseNumber;
	
	@Column(name="register_place")
	private String registerPlace;
	
	@Column(name="lincense_image")
	private String lincenseImage;
	
	@Column(name="register_date")
	private String registerDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyLegalRepresentative() {
		return companyLegalRepresentative;
	}

	public void setCompanyLegalRepresentative(String companyLegalRepresentative) {
		this.companyLegalRepresentative = companyLegalRepresentative;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLincenseNumber() {
		return lincenseNumber;
	}

	public void setLincenseNumber(String lincenseNumber) {
		this.lincenseNumber = lincenseNumber;
	}

	public String getRegisterPlace() {
		return registerPlace;
	}

	public void setRegisterPlace(String registerPlace) {
		this.registerPlace = registerPlace;
	}

	public String getLincenseImage() {
		return lincenseImage;
	}

	public void setLincenseImage(String lincenseImage) {
		this.lincenseImage = lincenseImage;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	
	
	
}