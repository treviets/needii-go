/**
 * 
 */
package net.needii.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author kelvin
 *
 */
@Entity
@Table(name = "customers")
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
public class Customer extends BaseUser {
	private static final long serialVersionUID = 1L;

	@Column(name = "auth_type")
	private int authType;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	private String avatar;

	@Column(name = "favorite_product_ids")
	private String favoriteProductIds;

	@Column(name = "needii_cash")
	private float neediiCash;

	private int gender;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login_at")
	private Date lastLoginAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reference_at")
	private Date referenceAt;

	@Column(name = "verify_code")
	private String verifyCode;

	@Column(name = "is_authenticated")
	private boolean isAuthenticated;

	@Column(name = "reference_code")
	private String referenceCode;

	@Column(name = "reference_by")
	private long referenceBy;

	@Column(name = "facebook_uid")
	private String facebookUID;

	@Column(name = "google_uid")
	private String googleUID;

	private boolean status;

	
}
