package net.needii.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



/**
 * The persistent class for the category_data database table.
 * 
 */
@Entity
@Table(name="product_attribute_data")
@NamedQuery(name="ProductAttributeData.findAll", query="SELECT pa FROM ProductAttributeData pa")
public class ProductAttributeData extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String description;

	private String name;

	@Column(name = "language_id")
	private int languageId;
	
	@ManyToOne
	@JoinColumn(name = "product_attribute_id")
	private ProductAttribute productAttribute;

	public ProductAttributeData() {
		
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public ProductAttribute getProductAttribute() {
		return productAttribute;
	}

	public void setProductAttribute(ProductAttribute productAttribute) {
		this.productAttribute = productAttribute;
	}

}