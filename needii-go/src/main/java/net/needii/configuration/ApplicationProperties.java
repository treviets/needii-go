/**
 * 
 */
package net.needii.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
 
/**
 * @author Vincent
 *
 */

@Component
public class ApplicationProperties {
	
	@Value("${spring.datasource.driver.classname}")
	private String driverClassname;
	
	@Value("${spring.datasource.url}")
	private String datasourceUrl;
	
	@Value("${spring.datasource.username}")
	private String datasourceUsername;
	
	@Value("${spring.datasource.password}")
	private String datasourcePassword;
	
	@Value("${security.oauth2-endpoint}")
	private String oauthEndpoint;
	
	@Value("${security.jwt.client-id}")
	private String clientId;
	
	@Value("${security.jwt.client-secret}")
	private String clientSecret;
	
	@Value("${elasticsearch.cluster-name}")
	private String elasticsearchClusterName;
	
	@Value("${elasticsearch.host}")
	private String elasticsearchHost;
	
	@Value("${elasticsearch.port}")
	private int elasticsearchPort;

	public String getOauthEndpoint() {
		return oauthEndpoint;
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public String getDatasourceUrl() {
		return datasourceUrl;
	}

	public String getDatasourceUsername() {
		return datasourceUsername;
	}

	public String getDatasourcePassword() {
		return datasourcePassword;
	}

	public String getDriverClassname() {
		return driverClassname;
	}

	public String getElasticsearchClusterName() {
		return elasticsearchClusterName;
	}

	public String getElasticsearchHost() {
		return elasticsearchHost;
	}

	public int getElasticsearchPort() {
		return elasticsearchPort;
	}
}