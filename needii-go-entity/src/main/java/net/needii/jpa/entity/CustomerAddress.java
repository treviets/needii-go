package net.needii.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;


/**
 * The persistent class for the customer_addresses database table.
 * 
 */
@Entity
@Table(name="customer_addresses")
@NamedQuery(name="CustomerAddress.findAll", query="SELECT c FROM CustomerAddress c")
public class CustomerAddress extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	
	@Column(name="address_type")
	@Min(0)
	private int addressType;

	@Column(name="full_text_address")
	private String fullTextAddress;

	@Column(name="is_default")
	private boolean isDefault;

	@Column(name="phone")
	private String phone;

	@Column(name="receiver_name")
	private String receiverName;

	private boolean status;

	@Column(name="street_name")
	private String streetName;
	
	@Column(name="street_number")
	private String streetNumber;

	@ManyToOne
	private Customer customer;
	
	@OneToOne
	@JoinColumn(name = "city_id")
	private City city;
	
	@OneToOne
	@JoinColumn(name = "district_id")
	private District district;
	
	@OneToOne
	@JoinColumn(name = "ward_id")
	private Ward ward;

	public CustomerAddress() {
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

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Ward getWard() {
		return ward;
	}

	public void setWard(Ward ward) {
		this.ward = ward;
	}

}