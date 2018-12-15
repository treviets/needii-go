/**
 * 
 */
package net.needii.dto;

public class CustomerAddressDto extends BaseModelResponseImpl {
	
	private long id;
	
	private int addressType;

	private String fullTextAddress;

	private boolean isDefault;

	private String phone;

	private String receiverName;

	private boolean status;

	private String streetName;
	
	private String streetNumber;

	private CustomerDto customer;
	
	private CityDto city;
	
	private DistrictDto district;
	
	private WardDto ward;
	
	private boolean isDeleted;

	public CustomerAddressDto() {
		this.isDefault = false;
		this.status = true;
	}
	

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAddressType() {
		return this.addressType;
	}

	public void setAddressType(int addressType) {
		this.addressType = addressType;
	}

	public String getFullTextAddress() {
		return this.fullTextAddress;
	}

	public void setFullTextAddress(String fullTextAddress) {
		this.fullTextAddress = fullTextAddress;
	}
	
	public void setFullTextAddress(String wardName, String districtName, String cityName) {
		this.fullTextAddress = String.format("%s %s, %s, %s, %s", this.streetNumber, this.streetName, wardName, districtName, cityName);
	}

	public boolean getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReceiverName() {
		return this.receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getStreetName() {
		return this.streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public CustomerDto getCustomer() {
		return this.customer;
	}

	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public CityDto getCity() {
		return city;
	}

	public void setCity(CityDto city) {
		this.city = city;
	}

	public DistrictDto getDistrict() {
		return district;
	}

	public void setDistrict(DistrictDto district) {
		this.district = district;
	}

	public WardDto getWard() {
		return ward;
	}

	public void setWard(WardDto ward) {
		this.ward = ward;
	}


	public boolean getIsDeleted() {
		return isDeleted;
	}


	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	
	
	
	
}