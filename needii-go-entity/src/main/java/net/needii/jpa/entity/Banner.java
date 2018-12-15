package net.needii.jpa.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import util.Constants;


/**
 * The persistent class for the banners database table.
 * 
 */
@Entity
@Table(name="banners")
@NamedQuery(name="Banner.findAll", query="SELECT b FROM Banner b")
public class Banner extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to BannerData
	@OneToMany(mappedBy="banner")
	private List<BannerData> bannerData;
	
	//bi-directional many-to-one association to Supplier
	@ManyToOne(fetch=FetchType.LAZY)
	private Supplier supplier;

	public Banner() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<BannerData> getBannerData() {
		return this.bannerData;
	}

	public void setBannerData(List<BannerData> bannerData) {
		this.bannerData = bannerData;
	}

	public BannerData addBannerData(BannerData bannerData) {
		getBannerData().add(bannerData);
		bannerData.setBanner(this);

		return bannerData;
	}

	public BannerData removeBannerData(BannerData bannerData) {
		getBannerData().remove(bannerData);
		bannerData.setBanner(null);

		return bannerData;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public BannerData getData() {
		return this.getDataByLanguage(Constants.DEFAULT_VIETNAMESE_LANGUAGE_ID, true);
	}
	
	public BannerData getData(int languageId) {
		return this.getDataByLanguage(languageId, false);
	}
	
	public BannerData getData(int languageId, boolean getDefault) {
		return this.getDataByLanguage(languageId, getDefault);
	}
	
	private BannerData getDataByLanguage(int languageId, boolean getDefault) {
		BannerData bannerData = this.getBannerData()
										.stream()
										.filter(x -> x.getLanguageId() == languageId)
										.findFirst()
									    .orElse(null);
		if(bannerData == null) {
			if(!getDefault) return new BannerData();
			else {
				bannerData = this.getBannerData().stream()
				.filter(x -> x.getLanguageId() == Constants.DEFAULT_VIETNAMESE_LANGUAGE_ID)
				.findFirst() .orElse(new BannerData());
			}
		}
		return bannerData;					   
	}
	
	public int languageExsist(int languageId) {
		return this.checkLanguageExsist(languageId);
	}
	
	private int checkLanguageExsist(int languageId) 
	{
		BannerData bannerData = this.getBannerData()
				.stream()
				.filter(x -> x.getLanguageId() == languageId)
				.findFirst()
			    .orElse(null);
		if(bannerData == null) return 0;
		return 1;
	}
}

