package net.needii.jpa.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the supplier_roles database table.
 * 
 */
@Entity
@Table(name="supplier_roles")
@NamedQuery(name="SupplierRole.findAll", query="SELECT s FROM SupplierRole s")
public class SupplierRole extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String code;

	private String name;

	//bi-directional many-to-one association to Supplier
	@OneToMany(mappedBy="supplierRole")
	private List<Supplier> suppliers;

	public SupplierRole() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Supplier> getSuppliers() {
		return this.suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}
}