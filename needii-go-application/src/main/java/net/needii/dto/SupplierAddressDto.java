package net.needii.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the supplier_addresses database table.
 * 
 */
public class SupplierAddressDto {
	private long id;

	@JsonProperty("city_id")
	private int cityId;
	
	@JsonProperty("district_id")
	private int districtId;
	
	@JsonProperty("ward_id")
	private int wardId;
	
	@JsonProperty("street_name")
	private String streetName;
	
	@JsonProperty("full_text_address")
	private String fullTextAddress;
	
	private boolean status;
	
	@JsonIgnore
	private SupplierDto supplier;

	
	public long getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullTextAddress() {
		return this.fullTextAddress;
	}

	public void setFullTextAddress(String fullTextAddress) {
		this.fullTextAddress = fullTextAddress;
	}

	public String getStreetName() {
		return this.streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	public SupplierDto getSupplier() {
		return this.supplier;
	}

	public void setSupplier(SupplierDto supplier) {
		this.supplier = supplier;
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
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

}