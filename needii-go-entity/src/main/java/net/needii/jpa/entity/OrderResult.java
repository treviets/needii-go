package net.needii.jpa.entity;

import javax.persistence.*;

public class OrderResult {
	
	@Column(name = "id", nullable = false)
	private long id;
	
	@Column(name = "message", nullable = false)
	private String message;
	
	public OrderResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

