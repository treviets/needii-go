/**
 * 
 */
package net.needii.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryDto extends BaseModelResponseImpl {
	
	private int id;
	
	private String name;
	
	private String description;
	
	@JsonProperty("icon_url")
	private String iconUrl;
	
	@JsonProperty("icon_url_active")
	private String iconUrlActive;
	
	private int level;
	
	@JsonProperty("parent_id")
	private int parentId;
	
	private Set<CategoryDataDto> categoryData;
	
	private float cashbackPercent;
	
	public CategoryDto() {
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

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the parentId
	 */
	public int getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getIconUrlActive() {
		return iconUrlActive;
	}

	public void setIconUrlActive(String iconUrlActive) {
		this.iconUrlActive = iconUrlActive;
	}

	public Set<CategoryDataDto> getCategoryData() {
		return categoryData;
	}

	public void setCategoryData(Set<CategoryDataDto> categoryData) {
		this.categoryData = categoryData;
	}


	public float getCashbackPercent() {
		return cashbackPercent;
	}


	public void setCashbackPercent(float cashbackPercent) {
		this.cashbackPercent = cashbackPercent;
	}
	
	
}