package net.needii.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the customer_balance_histories database table.
 * 
 */
@Entity
@Table(name="customer_balance_histories")
@NamedQuery(name="CustomerBalanceHistory.findAll", query="SELECT c FROM CustomerBalanceHistory c")
public class CustomerBalanceHistory extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private float amount;

	private String note;

	@ManyToOne(fetch=FetchType.LAZY)
	private Customer customer;

	public CustomerBalanceHistory() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}