package net.needii.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class ProductDataDto{
	
	private long id;

	
	private String description;

	private String shortDescription;

	private String name;
	
	private String slug;

	private String unit;

	@JsonIgnore
	private ProductDto product;
	private int languageId;
	private String startTime;
	private String endTime;
	private String warrantyPeriod;
	private String warrantyType;
	private String productStatus;
	private String isDeliveryGlobalFree;
	private String deliveryFeeInCity;
	private String freeForOrderAmountReachToInCity;
	private String deliveryGlobalFee;
	private String freeForOrderAmountReachToInGlobal;
	private boolean isDeleted;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public ProductDto getProduct() {
		return product;
	}
	public void setProduct(ProductDto product) {
		this.product = product;
	}
	public int getLanguageId() {
		return languageId;
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
	public boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	
}