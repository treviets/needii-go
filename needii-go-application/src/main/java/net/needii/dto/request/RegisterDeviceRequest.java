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
public class RegisterDeviceRequest extends BaseRequest {
	@JsonProperty("device_uid")
	@NotEmpty
	private String deviceUID;
	
	@JsonProperty("push_token")
	private String pushToken;
	
	@JsonProperty("os_name")
	@NotEmpty
	private String osName;

	public String getDeviceUID() {
		return deviceUID;
	}

	public void setDeviceUID(String deviceUID) {
		this.deviceUID = deviceUID;
	}

	public String getPushToken() {
		return pushToken == null ? "" : pushToken;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}
	

}
