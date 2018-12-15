package net.needii.jpa.entity;

public class ProductAttributeSearchCriteria {
	
	private String name;
	
	private int status;
	
	public ProductAttributeSearchCriteria(String name, int status) {
		this.name = name;
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
