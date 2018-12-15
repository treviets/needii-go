package net.needii.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the product_attributes database table.
 * 
 */
@Entity
@Table(name="product_attribute_values")
@NamedQuery(name="ProductAttributeValue.findAll", query="SELECT pav FROM ProductAttributeValue pav")
public class ProductAttributeValue extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	
	private String value;

	private boolean status;
	
	@ManyToOne
	@JoinColumn(name = "product_attribute_id")
	private ProductAttribute productAttribute;

	public ProductAttributeValue() {
		this.status = true;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public ProductAttribute getProductAttribute() {
		return productAttribute;
	}

	public void setProductAttribute(ProductAttribute productAttribute) {
		this.productAttribute = productAttribute;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}