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
public class RefreshTokenRequest extends BaseRequest {
	@JsonProperty("refresh_token")
	@NotEmpty
	private String refreshToken;

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
