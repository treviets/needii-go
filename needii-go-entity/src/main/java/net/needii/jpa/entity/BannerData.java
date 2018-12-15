package net.needii.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the banner_data database table.
 * 
 */
@Entity
@Table(name="banner_data")
@DynamicUpdate
@NamedQuery(name="BannerData.findAll", query="SELECT b FROM BannerData b")
public class BannerData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="banner_type")
	private int bannerType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at", updatable = false)
	@CreationTimestamp
	private Date createdAt;

	@Column(name="language_id")
	private int languageId;

	private String link;

	@Column(name = "`order`", length = 10, precision = 0)
	private int order;

	@Column(name = "`status`", length = 1, precision = 0) 
	private int status;

	private String title;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	@UpdateTimestamp
	private Date updatedAt;

	//bi-directional many-to-one association to Banner
	@ManyToOne(fetch=FetchType.LAZY)
	private Banner banner;

	//bi-directional many-to-one association to BannerMedia
	@OneToMany(mappedBy="bannerData")
	private List<BannerMedia> bannerMedias;

	public BannerData() {}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBannerType() {
		return this.bannerType;
	}

	public void setBannerType(int bannerType) {
		this.bannerType = bannerType;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getOrder() {
		return this.order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Banner getBanner() {
		return this.banner;
	}

	public void setBanner(Banner banner) {
		this.banner = banner;
	}

	public List<BannerMedia> getBannerMedias() {
		return this.bannerMedias;
	}
	
	public BannerMedia getBannerMedia() {
		BannerMedia bannerMedia = this.getBannerMedias()
										.stream()
										.findFirst()
									    .orElse(new BannerMedia());
		return bannerMedia;
	}
	
	public void setBannerMedias(List<BannerMedia> bannerMedias) {
		this.bannerMedias = bannerMedias;
	}

	public BannerMedia addBannerMedia(BannerMedia bannerMedia) {
		getBannerMedias().add(bannerMedia);
		bannerMedia.setBannerData(this);

		return bannerMedia;
	}

	public BannerMedia removeBannerMedia(BannerMedia bannerMedia) {
		getBannerMedias().remove(bannerMedia);
		bannerMedia.setBannerData(null);

		return bannerMedia;
	}

}