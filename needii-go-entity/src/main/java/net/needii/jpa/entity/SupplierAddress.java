package net.needii.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Min;


/**
 * The persistent class for the supplier_addresses database table.
 * 
 */
@Entity
@Table(name="supplier_addresses")
@NamedQuery(name="SupplierAddress.findAll", query="SELECT s FROM SupplierAddress s")
public class SupplierAddress extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="city_id")
	@Min(1)
	private int cityId;
	
	@Column(name="district_id")
	@Min(1)
	private int districtId;
	
	@Column(name="ward_id")
	@Min(1)
	private int wardId;
	
	@Column(name="street_name")
	private String streetName;
	
	@Column(name="full_text_address")
	private String fullTextAddress;
	
	private boolean status;

	//bi-directional many-to-one association to Supplier
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;

	public SupplierAddress() {
	}

	public int getId() {
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
	
	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
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