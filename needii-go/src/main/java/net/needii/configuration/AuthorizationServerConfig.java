/**
 * 
 */
package net.needii.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

   @Value("${security.jwt.client-id}")
   private String clientId;

   @Value("${security.jwt.client-secret}")
   private String clientSecret;

   @Value("${security.jwt.grant-type}")
   private String grantType;

   @Value("${security.jwt.scope-read}")
   private String scopeRead;

   @Value("${security.jwt.scope-write}")
   private String scopeWrite = "write";

   @Value("${security.jwt.resource-ids}")
   private String resourceIds;

   @Autowired
   private TokenStore tokenStore;

   @Autowired
   private JwtAccessTokenConverter accessTokenConverter;

   @Autowired
   private AuthenticationManager authenticationManager;
   
   @Autowired
   private UserDetailsService userDetailsService;

   @Override
   public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
      configurer
              .inMemory()
              .withClient("needii_guest")
              .secret("bmVlZGlpX2d1ZXN0")
              .authorizedGrantTypes("password", "authorization_code")
              .scopes(scopeRead)
              .accessTokenValiditySeconds(360000000)
              .and()
              .withClient(clientId)
              .secret(clientSecret)
              .authorizedGrantTypes("password", "authorization_code", "refresh_token")
              .scopes(scopeRead, scopeWrite)
              .accessTokenValiditySeconds(3600) //seconds
              //.accessTokenValiditySeconds(30)
              .resourceIds(resourceIds);
   }

   @Override
   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
      TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
      enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
      endpoints.tokenStore(tokenStore)
              .accessTokenConverter(accessTokenConverter)
              .tokenEnhancer(enhancerChain)
              .authenticationManager(authenticationManager)
              .userDetailsService(userDetailsService);
   }

   @Bean
   public TokenEnhancer tokenEnhancer() {
       return new CustomTokenEnhancer();
   }
}