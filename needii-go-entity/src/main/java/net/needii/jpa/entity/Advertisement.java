package net.needii.jpa.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the admins database table.
 * 
 */
@Entity
@Table(name="advertisements")
@NamedQuery(name="Advertisement.findAll", query="SELECT a FROM Advertisement a")
public class Advertisement extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	@JsonProperty("supplier_id")
	private int supplierId;
	
	private String name;
	
	private String description;
	
	private String images;
	
	@JsonProperty("like_count")
	private int likeCount;
	
	@JsonProperty("share_count")
	private int shareCount;
	
	private boolean status;
	
	public Advertisement() {
		this.supplierId = 11;
		this.name = "Mango quảng cáo";
		this.description = "Mango đại hạ giá, bán sỉ giá lẻ, hàng xấu giá mắc, mại dô mại dô, mua 1 tặng 1 tính tiền 2. Mua nhanh kẻo hết, mại dô mại dô.";
		this.images = "https://i.imgur.com/7DPHn0q.jpg;" + 
				"https://i.imgur.com/tYDR84y.jpg;" + 
				"https://i.imgur.com/RFXJZ3J.jpg;" + 
				"https://i.imgur.com/1hsz6G9.jpg";
		this.likeCount = 100;
		this.shareCount = 200;
		this.status = true;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the status
	 */
	public boolean getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImages() {
		return images;
	}
	
	public List<String> getImageList() {
		if(this.images == null || this.images.isEmpty()) {
			return new ArrayList<>();
		} else {
			return new ArrayList<>(Arrays.asList(this.images.split(";")));
		}
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getShareCount() {
		return shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
}