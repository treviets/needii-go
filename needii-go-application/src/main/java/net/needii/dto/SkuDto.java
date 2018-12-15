package net.needii.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import util.Constants;

public class SkuDto {
	private long id;
	private String name;
	@JsonProperty("sku_code")
	private String skuCode;
	private float price;
	private float lastPrice;
	private int quantity;
	@JsonProperty("cashback_percent")
	private float cashbackPercent;
	@JsonProperty("discount_percent")
	private float discountPercent;
	private boolean status;
	@JsonProperty("is_default")
	private boolean isDefault;
	private List<String> images = new ArrayList<>();
	private String imageSlide;
	@JsonIgnore
	private ProductDto product;
	private List<SkuDataDto> skuData;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean aDefault) {
		isDefault = aDefault;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(float lastPrice) {
		this.lastPrice = lastPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getCashbackPercent() {
		return cashbackPercent;
	}

	public void setCashbackPercent(float cashbackPercent) {
		this.cashbackPercent = cashbackPercent;
	}

	public float getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(float discountPercent) {
		this.discountPercent = discountPercent;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getSkuCode() {
		return this.skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public boolean validToSell() {
		return true;
	}
	
	public String getImageSlide() {
		return this.imageSlide;
	}

	public void setImageSlide(String imageSlide) {
		this.imageSlide = imageSlide;
	}
	
	@JsonIgnore
	public List<String> getImageSlideAsListString() {
		if(this.imageSlide == null || this.imageSlide.isEmpty()) {
			return new ArrayList<>();
		} else {
			return new ArrayList<>(Arrays.asList(this.imageSlide.split(";")));
		}
	}
	
	@JsonIgnore
	public Set<String> getImageWithPathAsListString() {
		if(this.imageSlide == null || this.imageSlide.isEmpty()) {
			return new HashSet<>();
		} else {
			Set<String> images = new HashSet<>(Arrays.asList(this.imageSlide.split(";")));
			Set<String> result = new HashSet<>();
			for (String image : images) {
				if(image != null && !image.contains("http")) {
					result.add(Constants.RESOURCE_DOMAIN + image);
				} else {
					result.add(image);
				}
				
			}
			return result;
		}
	}

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}

	public List<SkuDataDto> getSkuData() {
		return skuData;
	}

	public void setSkuData(List<SkuDataDto> skuData) {
		this.skuData = skuData;
	}
	
	
}
