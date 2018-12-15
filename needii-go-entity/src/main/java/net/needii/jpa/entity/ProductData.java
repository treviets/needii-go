package net.needii.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import util.Utils;


/**
 * The persistent class for the product_data database table.
 * 
 */
@Entity
@Table(name="product_data")
@DynamicUpdate
@NamedQuery(name="ProductData.findAll", query="SELECT p FROM ProductData p")
public class ProductData extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Lob
	private String description;

	@Column(name="short_description")
	private String shortDescription;

	private String name;
	
	private String slug;

	private String unit;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	//bi-directional many-to-one association to Language
	@Column(name="language_id")
	private int languageId;
	
	@Column(name="start_time")
	private String startTime;
	
	@Column(name="end_time")
	private String endTime;
	
	@Column(name="warranty_period")
	private String warrantyPeriod;
	
	@Column(name="warranty_type")
	private String warrantyType;
	
	@Column(name="product_status")
	private String productStatus;
	
	@Column(name="is_delivery_global_free")
	private String isDeliveryGlobalFree;
	
	@Column(name="delivery_fee_in_city")
	private String deliveryFeeInCity;
	
	@Column(name="free_for_order_amount_reach_to_in_city")
	private String freeForOrderAmountReachToInCity;
	
	@Column(name="delivery_global_fee")
	private String deliveryGlobalFee;
	
	@Column(name="free_for_order_amount_reach_to_in_global")
	private String freeForOrderAmountReachToInGlobal;

	public ProductData() {
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

	public String getShortDescription() {
		return this.shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getName() {
		return this.name;
	}
	
	public String getNameLowcase() {
		return this.name.toLowerCase();
	}
	
	public String getNameLowcaseNoneSpace() {
		return this.name.toLowerCase().replaceAll(" ", "");
	}
	
	public String getNameNoneUnicode() {
		return Utils.removeVietnameseFromString(this.name);
	}
	
	public String getNameNoneUnicodeNoneSpace() {
		return Utils.removeVietnameseFromString(this.name).replaceAll(" ", "");
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSlug() {
		return this.slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getWarrantyPeriod() {
		return warrantyPeriod;
	}

	public void setWarrantyPeriod(String warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}

	public String getWarrantyType() {
		return warrantyType;
	}

	public void setWarrantyType(String warrantyType) {
		this.warrantyType = warrantyType;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getIsDeliveryGlobalFree() {
		return isDeliveryGlobalFree;
	}

	public void setIsDeliveryGlobalFree(String isDeliveryGlobalFree) {
		this.isDeliveryGlobalFree = isDeliveryGlobalFree;
	}

	public String getDeliveryFeeInCity() {
		return deliveryFeeInCity;
	}

	public void setDeliveryFeeInCity(String deliveryFeeInCity) {
		this.deliveryFeeInCity = deliveryFeeInCity;
	}

	public String getFreeForOrderAmountReachToInCity() {
		return freeForOrderAmountReachToInCity;
	}

	public void setFreeForOrderAmountReachToInCity(String freeForOrderAmountReachToInCity) {
		this.freeForOrderAmountReachToInCity = freeForOrderAmountReachToInCity;
	}

	public String getDeliveryGlobalFee() {
		return deliveryGlobalFee;
	}

	public void setDeliveryGlobalFee(String deliveryGlobalFee) {
		this.deliveryGlobalFee = deliveryGlobalFee;
	}

	public String getFreeForOrderAmountReachToInGlobal() {
		return freeForOrderAmountReachToInGlobal;
	}

	public void setFreeForOrderAmountReachToInGlobal(String freeForOrderAmountReachToInGlobal) {
		this.freeForOrderAmountReachToInGlobal = freeForOrderAmountReachToInGlobal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}