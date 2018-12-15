/**
 * 
 */
package net.needii.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Vincent
 *
 */
public class TokenDto {
	
	private long id;
	
	private String user_name;
	
	private String client_id;
	
	private String[] aud;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	private String avatar;
	
	private String access_token;
	
	private String refresh_token;
	
	private String token_type;
	
	private List<Integer> category_ids;
	
	private List<Long> subscribe_supplier_ids;
	
	private List<Long> like_supplier_ids;
	
	private List<Long> supplier_ids;
	
	private List<Long> like_product_ids;
	
	private boolean is_supplier;
	
	private long exp;
	
	@JsonIgnore
	private long expires_in;
	
	@JsonIgnore
	private String scope;
	
	@JsonIgnore
	private String jti;
	
	private String[] authorities;

	/**
	 * @return the access_token
	 */
	public String getAccess_token() {
		return access_token;
	}

	/**
	 * @param access_token the access_token to set
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	/**
	 * @return the token_type
	 */
	public String getToken_type() {
		return token_type;
	}

	/**
	 * @param token_type the token_type to set
	 */
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	/**
	 * @return the expires_in
	 */
	public long getExpires_in() {
		return expires_in;
	}

	/**
	 * @param expires_in the expires_in to set
	 */
	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}

	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * @return the jti
	 */
	public String getJti() {
		return jti;
	}

	/**
	 * @param jti the jti to set
	 */
	public void setJti(String jti) {
		this.jti = jti;
	}
	/**
	 * @return the employeeId
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * @param avatar the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String[] getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}

	public boolean isIs_supplier() {
		return is_supplier;
	}

	public void setIs_supplier(boolean is_supplier) {
		this.is_supplier = is_supplier;
	}

	public List<Integer> getCategory_ids() {
		return category_ids;
	}

	public void setCategory_ids(List<Integer> category_ids) {
		this.category_ids = category_ids;
	}

	public List<Long> getSubscribe_supplier_ids() {
		return subscribe_supplier_ids;
	}

	public void setSubscribe_supplier_ids(List<Long> subscribe_supplier_ids) {
		this.subscribe_supplier_ids = subscribe_supplier_ids;
	}

	public List<Long> getLike_supplier_ids() {
		return like_supplier_ids;
	}

	public void setLike_supplier_ids(List<Long> like_supplier_ids) {
		this.like_supplier_ids = like_supplier_ids;
	}

	public List<Long> getLike_product_ids() {
		return like_product_ids;
	}

	public void setLike_product_ids(List<Long> like_product_ids) {
		this.like_product_ids = like_product_ids;
	}

	public List<Long> getSupplier_ids() {
		return supplier_ids;
	}

	public void setSupplier_ids(List<Long> supplier_ids) {
		this.supplier_ids = supplier_ids;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String[] getAud() {
		return aud;
	}

	public void setAud(String[] aud) {
		this.aud = aud;
	}

	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}
	
	
	
	
}