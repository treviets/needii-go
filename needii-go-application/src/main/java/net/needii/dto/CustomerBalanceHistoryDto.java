/**
 * 
 */
package net.needii.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerBalanceHistoryDto extends BaseModelResponseImpl {
	
	private long id;
	
	private float amount;
	
	private String note;
	
	@JsonProperty("created_at")
	private String createdAt;
	
	private CustomerDto customer;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}
	
	
}