/**
 * 
 */
package net.needii.dto.request;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author longnguyen
 *
 */
public class LogoutRequest extends BaseRequest {
	@JsonProperty("device_uid")
	@NotEmpty
	private String deviceUID;
	
	public String getDeviceUID() {
		return deviceUID;
	}

	public void setDeviceUID(String deviceUID) {
		this.deviceUID = deviceUID;
	}
}
