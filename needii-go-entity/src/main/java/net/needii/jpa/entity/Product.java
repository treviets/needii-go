package net.needii.jpa.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import util.Constants;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@DynamicUpdate
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int approve;

	@Column(name="available_for_sale")
	private int availableForSale;

	@Column(name="cashback_percent")
	private float cashbackPercent;

	@Column(name="discount_percent")
	private float discountPercent;

	private String image;

	@Lob
	@Column(name="image_album")
	private String imageAlbum;

	@Column(name="is_best_seller")
	private int isBestSeller;

	@Column(name="is_comming_deal")
	private int isCommingDeal;

	@Column(name="is_hot")
	private int isHot;

	@Column(name="is_new")
	private int isNew;

	@Column(name="is_promotion")
	private int isPromotion;

	@Column(name="is_reject")
	private int isReject;

	@Column(name="is_show_home")
	private int isShowHome;

	@Column(name="is_today_deal")
	private int isTodayDeal;

	@Column(name="last_price")
	private float lastPrice;

	@Column(name="like_count")
	private int likeCount;

	@Column(name = "`order`", length = 10, precision = 0)
	private int order;

	private float price;

	private int quantity;

	@Column(name="reject_reason")
	private String rejectReason;

	@Column(name="search_count")
	private int searchCount;

	@Column(name="sell_count")
	private int sellCount;

	@Column(name="share_count")
	private int shareCount;

	private int status;

	@Column(name="view_count")
	private int viewCount;


	@OneToMany(mappedBy="product", cascade=CascadeType.ALL)
	private List<ProductData> productData;
	
	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="cate_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name="supplier_id")
	private Supplier supplier;

	//bi-directional many-to-one association to Skus
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL)
	private List<Skus> skuses;

	public Product() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getApprove() {
		return this.approve;
	}

	public void setApprove(int approve) {
		this.approve = approve;
	}

	public int getAvailableForSale() {
		return this.availableForSale;
	}

	public void setAvailableForSale(int availableForSale) {
		this.availableForSale = availableForSale;
	}

	public float getCashbackPercent() {
		return this.cashbackPercent;
	}

	public void setCashbackPercent(float cashbackPercent) {
		this.cashbackPercent = cashbackPercent;
	}

	public float getDiscountPercent() {
		return this.discountPercent;
	}

	public void setDiscountPercent(float discountPercent) {
		this.discountPercent = discountPercent;
	}

	@JsonIgnore
    public String getImage() { 
		return this.image; 
	}
	public String getImageWithPath() {
		return Constants.RESOURCE_DOMAIN+ this.image;
	}
    public void setImage(String image) {
		this.image = image;
	}

	public String getImageAlbum() {
		return this.imageAlbum;
	}

	public void setImageAlbum(String imageAlbum) {
		this.imageAlbum = imageAlbum;
	}

	public int getIsBestSeller() {
		return this.isBestSeller;
	}

	public void setIsBestSeller(int isBestSeller) {
		this.isBestSeller = isBestSeller;
	}

	public int getIsCommingDeal() {
		return this.isCommingDeal;
	}

	public void setIsCommingDeal(int isCommingDeal) {
		this.isCommingDeal = isCommingDeal;
	}

	public int getIsHot() {
		return this.isHot;
	}

	public void setIsHot(int isHot) {
		this.isHot = isHot;
	}

	public int getIsNew() {
		return this.isNew;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}

	public int getIsPromotion() {
		return this.isPromotion;
	}

	public void setIsPromotion(int isPromotion) {
		this.isPromotion = isPromotion;
	}

	public int getIsReject() {
		return this.isReject;
	}

	public void setIsReject(int isReject) {
		this.isReject = isReject;
	}

	public int getIsShowHome() {
		return this.isShowHome;
	}

	public void setIsShowHome(int isShowHome) {
		this.isShowHome = isShowHome;
	}

	public int getIsTodayDeal() {
		return this.isTodayDeal;
	}

	public void setIsTodayDeal(int isTodayDeal) {
		this.isTodayDeal = isTodayDeal;
	}

	public float getLastPrice() {
		return this.lastPrice;
	}

	public void setLastPrice(float lastPrice) {
		this.lastPrice = lastPrice;
	}

	public int getLikeCount() {
		return this.likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getOrder() {
		return this.order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRejectReason() {
		return this.rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public int getSearchCount() {
		return this.searchCount;
	}

	public void setSearchCount(int searchCount) {
		this.searchCount = searchCount;
	}

	public int getSellCount() {
		return this.sellCount;
	}

	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}

	public int getShareCount() {
		return this.shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public int getViewCount() {
		return this.viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public List<ProductData> getProductData() {
		return this.productData;
	}

	@JsonIgnore
	public void setProductData(List<ProductData> productData) {
		this.productData = productData;
	}

	@JsonIgnore
	public ProductData addProductData(ProductData productData) {
		getProductData().add(productData);
		productData.setProduct(this);

		return productData;
	}

	@JsonIgnore
	public ProductData removeProductData(ProductData productData) {
		getProductData().remove(productData);
		productData.setProduct(null);

		return productData;
	}

	@JsonIgnore
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@JsonIgnore
	public ProductData getData() {
		return this.getDataByLanguage(Constants.DEFAULT_VIETNAMESE_LANGUAGE_ID, true);
	}
	
	@JsonIgnore
	public ProductData getData(int languageId) {
		return this.getDataByLanguage(languageId, false);
	}
	
	@JsonIgnore
	private ProductData getDataByLanguage(int languageId, boolean getDefault) {
		ProductData productData = this.getProductData()
										.stream()
										.filter(x -> x.getLanguageId() == languageId)
										.findFirst()
									    .orElse(null);
		if(productData == null) {
			if(!getDefault) return new ProductData();
			else {
				productData = this.getProductData().stream()
				.filter(x -> x.getLanguageId() == Constants.DEFAULT_VIETNAMESE_LANGUAGE_ID)
				.findFirst() .orElse(new ProductData());
			}
		}
		return productData;					   
	}
	
	public int languageExsist(int languageId) {
		return this.checkLanguageExsist(languageId);
	}
	
	private int checkLanguageExsist(int languageId) 
	{
		ProductData productData = this.getProductData()
				.stream()
				.filter(x -> x.getLanguageId() == languageId)
				.findFirst()
			    .orElse(null);
		if(productData == null) return 0;
		return 1;
	}
	
	public boolean validToSell() {
		return true;
	}

	/**
	 * @return the skuses
	 */
	
	public List<Skus> getSkuses() {
		List<Skus> list = new ArrayList<Skus>(this.skuses);
		return list;
	}

	/**
	 * @param skuses the skuses to set
	 */
	public void setSkuses(List<Skus> skuses) {
		this.skuses = skuses;
	}

	@JsonIgnore
    public Supplier getSupplier() {
        return this.supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

	public List<String> getNames() {
		return this.productData.stream().map(ProductData::getName).collect(Collectors.toList());
	}

}