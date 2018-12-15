/**
 * 
 */
package net.needii.dto;

import net.needii.jpa.entity.Ward;

public class WardDto {
	
	private int id;
	
	private String name;
	
	public WardDto() {
	}
	
	public WardDto(Ward entity) {
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