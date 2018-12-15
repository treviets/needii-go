/**
 * 
 */
package net.needii.dto;

import net.needii.jpa.entity.District;

public class DistrictDto{
	
	private int id;
	
	private String name;
	
	public DistrictDto() {
	}
	
	public DistrictDto(District entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}
	
	
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
}