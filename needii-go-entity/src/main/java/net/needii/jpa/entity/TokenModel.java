/**
 * 
 */
package net.needii.jpa.entity;

import java.util.List;

/**
 * @author Vincent
 *
 */
public class TokenModel {
	private int id;
	
	private String user_name;
	
	private String phone;
	
	private String email;
	
	private String[] authorities;
	
	private String client_id;
	
	private String[] scope;
	
	private String jti;
	
	private String refresh_token;
	
	private long exp;
	
	private boolean is_supplier;
	
	private List<Integer> category_ids;
	
	private List<Integer> subscribe_supplier_ids;
	
	private List<Integer> like_supplier_ids;
	
	private List<Integer> supplier_ids;
	
	private List<Integer> like_product_ids;
	
	private String[] aud;

	/**
	 * @return the authorities
	 */
	public String[] getAuthorities() {
		return authorities;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}

	/**
	 * @return the client_id
	 */
	public String getClient_id() {
		return client_id;
	}

	/**
	 * @param client_id the client_id to set
	 */
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	/**
	 * @return the scope
	 */
	public String[] getScope() {
		return scope;
	}

	/**
	 * @param scope the scope to set
	 */
	public void setScope(String[] scope) {
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
	 * @return the exp
	 */
	public long getExp() {
		return exp;
	}

	/**
	 * @param exp the exp to set
	 */
	public void setExp(long exp) {
		this.exp = exp;
	}

	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String[] getAud() {
		return aud;
	}

	public void setAud(String[] aud) {
		this.aud = aud;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isIs_supplier() {
		return is_supplier;
	}

	public void setIs_supplier(boolean is_supplier) {
		this.is_supplier = is_supplier;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Integer> getCategory_ids() {
		return category_ids;
	}

	public void setCategory_ids(List<Integer> category_ids) {
		this.category_ids = category_ids;
	}

	public List<Integer> getSubscribe_supplier_ids() {
		return subscribe_supplier_ids;
	}

	public void setSubscribe_supplier_ids(List<Integer> subscribe_supplier_ids) {
		this.subscribe_supplier_ids = subscribe_supplier_ids;
	}

	public List<Integer> getLike_supplier_ids() {
		return like_supplier_ids;
	}

	public void setLike_supplier_ids(List<Integer> like_supplier_ids) {
		this.like_supplier_ids = like_supplier_ids;
	}

	public List<Integer> getLike_product_ids() {
		return like_product_ids;
	}

	public void setLike_product_ids(List<Integer> like_product_ids) {
		this.like_product_ids = like_product_ids;
	}

	public List<Integer> getSupplier_ids() {
		return supplier_ids;
	}

	public void setSupplier_ids(List<Integer> supplier_ids) {
		this.supplier_ids = supplier_ids;
	}

}