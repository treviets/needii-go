package net.needii.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the supplier_reviews database table.
 * 
 */
@Entity
@Table(name="supplier_reviews")
@NamedQuery(name="SupplierReview.findAll", query="SELECT s FROM SupplierReview s")
public class SupplierReview extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	@Column(name="customer_name")
	private String customerName;
	
	private String content;

	private int rate;

	@Column(name="is_show")
	private boolean isShow;
	
	@Column(name="is_approved")
	private boolean isApproved;

	//bi-directional many-to-one association to Supplier
	@ManyToOne(fetch=FetchType.LAZY)
	private Supplier supplier;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	private Customer customer;

	public SupplierReview() {
		this.isShow = false;
		this.isApproved = false;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the rate
	 */
	public int getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(int rate) {
		this.rate = rate;
	}

	/**
	 * @return the isShow
	 */
	public boolean isShow() {
		return isShow;
	}

	/**
	 * @param isShow the isShow to set
	 */
	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	/**
	 * @return the isApproved
	 */
	public boolean isApproved() {
		return isApproved;
	}

	/**
	 * @param isApproved the isApproved to set
	 */
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	/**
	 * @return the supplier
	 */
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * @param supplier the supplier to set
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}