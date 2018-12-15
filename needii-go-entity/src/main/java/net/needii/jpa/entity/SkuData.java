package net.needii.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import util.Utils;


/**
 * The persistent class for the sku_data database table.
 * 
 */
@Entity
@Table(name="sku_data")
@NamedQuery(name="SkuData.findAll", query="SELECT s FROM SkuData s")
public class SkuData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="is_deleted")
	private byte isDeleted;

	private String name;

	//bi-directional many-to-one association to Skus
	@ManyToOne
	@JoinColumn(name="sku_id")
	@JsonIgnore
	private Skus skus;

    @Column(name="language_id")
	private int languageId;

	public SkuData() {
	}

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

	public Skus getSkus() {
		return this.skus;
	}

	public void setSkus(Skus skus) {
		this.skus = skus;
	}

	public int getLanguageId() {
		return this.languageId;
	}

	public void setLanguage(int languageId) {
		this.languageId = languageId;
	}

}