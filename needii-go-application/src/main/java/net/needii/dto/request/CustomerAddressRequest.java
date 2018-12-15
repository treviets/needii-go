/**
 * 
 */
package net.needii.dto.request;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import util.validate.PhoneValidatorConstraint;

/**
 * @author longnguyen
 *
 */
public class CustomerAddressRequest extends BaseRequest {
	
	private long id;
	
	@JsonProperty("city_id")
	@Min(1)
	private int cityId;
	
	@JsonProperty("district_id")
	@Min(1)
	private int districtId;
	
	@JsonProperty("ward_id")
	@Min(1)
	private int wardId;

	@JsonProperty("address_type")
	@Min(0)
	private int addressType;

	@PhoneValidatorConstraint
	private String phone;

	@JsonProperty("receiver_name")
	@NotEmpty(message = "CustomerAddress.NotEmpty.ReceiverName")
	private String receiverName;

	@JsonProperty("street_name")
	@NotEmpty(message = "CustomerAddress.NotEmpty.StreetName")
	private String streetName;
	
	@JsonProperty("street_number")
	@NotEmpty(message = "CustomerAddress.NotEmpty.StreetNumber")
	private String streetNumber;

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
	 * @return the addressType
	 */
	public int getAddressType() {
		return addressType;
	}

	/**
	 * @param addressType the addressType to set
	 */
	public void setAddressType(int addressType) {
		this.addressType = addressType;
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
	 * @return the receiverName
	 */
	public String getReceiverName() {
		return receiverName;
	}

	/**
	 * @param receiverName the receiverName to set
	 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
