package net.needii.dto;

import util.Constants;

/**
 * The persistent class for the supplier_addresses database table.
 * 
 */
public class SupplierCompanyProfileDto{
	
	private int id;
	
	private String companyName;
	
	private String companyEmail;
	
	private String companyLegalRepresentative;
	
	private String title;

	private String address;
	
	private String lincenseNumber;
	
	private String registerPlace;
	
	private String lincenseImage;
	
	private String registerDate;
	
	private SupplierDto supplier;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		if(lincenseImage != null && !lincenseImage.contains("http")) {
			return Constants.RESOURCE_DOMAIN + lincenseImage;
		}
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

	public SupplierDto getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierDto supplier) {
		this.supplier = supplier;
	}
	
	
	
}