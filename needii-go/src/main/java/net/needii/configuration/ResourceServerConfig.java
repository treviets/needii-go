/**
 * 
 */
package net.needii.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private ResourceServerTokenServices tokenServices;

    @Value("${security.jwt.resource-ids}")
    private String resourceIds;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceIds).tokenServices(tokenServices);
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .requestMatchers()
            .and()
            .authorizeRequests()
            .antMatchers(
            		"/api/configs", "/api/configs/**", 
            		"/api/customers/login", "/api/customers/register", "/api/customers/external-login", "/api/customers/forgot-password", 
            		"/api/suppliers/login", "/api/suppliers/register", "/api/suppliers/forgot-password",
            		"/api/refresh-token")
            	.permitAll()
            .antMatchers("/api/**" ).hasAnyRole("GUEST", "CUSTOMER", "SUPPLIER")
            .anyRequest().authenticated();
    }
}