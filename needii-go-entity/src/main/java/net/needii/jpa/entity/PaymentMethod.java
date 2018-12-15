package net.needii.jpa.entity;

import javax.persistence.*;


/**
 * The persistent class for the admins database table.
 * 
 */
@Entity
@Table(name="payment_methods")
@NamedQuery(name="PaymentMethod.findAll", query="SELECT pm FROM PaymentMethod pm")
public class PaymentMethod extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	private int type;
	
	private String name;
	
	private boolean status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}