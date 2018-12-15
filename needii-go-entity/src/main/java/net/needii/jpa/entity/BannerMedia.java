package net.needii.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;


/**
 * The persistent class for the banner_medias database table.
 * 
 */
@Entity
@Table(name="banner_medias")
@NamedQuery(name="BannerMedia.findAll", query="SELECT b FROM BannerMedia b")
@DynamicUpdate
public class BannerMedia extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String image;

	private String size;

	private String type;

	//bi-directional many-to-one association to BannerData
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="banner_data_id")
	private BannerData bannerData;

	//bi-directional many-to-one association to Supplier
	@ManyToOne(fetch=FetchType.LAZY)
	private Supplier supplier;

	public BannerMedia() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BannerData getBannerData() {
		return this.bannerData;
	}

	public void setBannerData(BannerData bannerData) {
		this.bannerData = bannerData;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}