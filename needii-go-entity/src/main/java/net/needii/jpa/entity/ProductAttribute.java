package net.needii.jpa.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import util.Constants;


/**
 * The persistent class for the product_attributes database table.
 * 
 */
@Entity
@Table(name="product_attributes")
@NamedQuery(name="ProductAttribute.findAll", query="SELECT p FROM ProductAttribute p")
public class ProductAttribute extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="value_data")
	private String values;

	private boolean status;
	
	@OneToMany(mappedBy="productAttribute")
	private Set<ProductAttributeData> data = new HashSet<ProductAttributeData>();
	
//	@OneToMany(mappedBy="productAttribute")
//	private Set<ProductAttributeValue> values = new HashSet<ProductAttributeValue>();

	public ProductAttribute() {
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

	public Set<ProductAttributeData> getData() {
		return data;
	}

	public void setData(Set<ProductAttributeData> data) {
		this.data = data;
	}
	
	public List<ProductAttributeData> getDataList() {
		List<ProductAttributeData> list = new ArrayList<ProductAttributeData>(this.data);
		return list;
	}

	public void setData(List<ProductAttributeData> dataSet) {
		Set<ProductAttributeData> set = new HashSet<ProductAttributeData>(dataSet);
		this.data = set;
	}
	
	public ProductAttributeData getDataByLanguage() {
		return this.getDataByLanguage(Constants.DEFAULT_VIETNAMESE_LANGUAGE_ID, true);
	}
	public ProductAttributeData getDataByLanguage(int languageId) {
		return this.getDataByLanguage(languageId, true);
	}
	public ProductAttributeData getDataByLanguage(int languageId, boolean getDefault) {
		
		ProductAttributeData data = this.getDataList()
										.stream()
										.filter(x -> x.getLanguageId() == languageId)
										.findFirst()
									    .orElse(null);
		if(data == null) {
			if(!getDefault) return new ProductAttributeData();
			else {
				data = this.getDataList()
						.stream()
						.filter(x -> x.getLanguageId() == Constants.DEFAULT_VIETNAMESE_LANGUAGE_ID)
						.findFirst().orElse(new ProductAttributeData());
			}
		}
		return data;
	}
	
	public int languageExsist(int languageId) {
		return this.checkLanguageExsist(languageId);
	}
	
	public boolean isLanguageExsist(int languageId) {
		ProductAttributeData data = this.getDataList()
				.stream()
				.filter(x -> x.getLanguageId() == languageId)
				.findFirst()
			    .orElse(null);
		
		return data != null;
	}
	
	private int checkLanguageExsist(int languageId) 
	{
		ProductAttributeData data = this.getDataList()
				.stream()
				.filter(x -> x.getLanguageId() == languageId)
				.findFirst()
			    .orElse(null);
		if(data == null) return 0;
		return 1;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}
	
	public List<String> getValueAsListString() {
		if(this.values == null || this.values.isEmpty()) {
			return new ArrayList<String>();
		} else {
			return new ArrayList<String>(Arrays.asList(this.values.split(";"))) ;
		}
	}

}