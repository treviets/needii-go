package net.needii.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import util.Utils;


/**
 * The persistent class for the sku_data database table.
 * 
 */
public class SkuDataDto{

	private String id;

	private byte isDeleted;

	private String name;

	@JsonIgnore
	private SkuDto skus;

	private int languageId;

	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(byte isDeleted) {
		this.isDeleted = isDeleted;
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

	public SkuDto getSkus() {
		return this.skus;
	}

	public void setSkus(SkuDto skus) {
		this.skus = skus;
	}

	public int getLanguageId() {
		return this.languageId;
	}

	public void setLanguage(int languageId) {
		this.languageId = languageId;
	}

}