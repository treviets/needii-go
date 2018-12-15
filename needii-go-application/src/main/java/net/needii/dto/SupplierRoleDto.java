package net.needii.dto;

import java.util.List;


/**
 * The persistent class for the supplier_roles database table.
 * 
 */
public class SupplierRoleDto{

	private int id;

	private String code;

	private String name;

	private List<SupplierDto> suppliers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SupplierDto> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<SupplierDto> suppliers) {
		this.suppliers = suppliers;
	}

	
}