/**
 * 
 */
package net.needii.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import util.Constants;

public class ProductDto{

	private Long id;

	private int approve;

	private int availableForSale;

	private float cashbackPercent;

	private float discountPercent;

	private String image;

	private String imageAlbum;

	private int isBestSeller;

	private int isCommingDeal;

	private int isHot;

	private int isNew;

	private int isPromotion;

	private int isReject;

	private int isShowHome;

	private int isTodayDeal;

	private float lastPrice;

	private int likeCount;

	private int order;

	private float price;

	private int quantity;

	private String rejectReason;

	private int searchCount;

	private int sellCount;

	private int shareCount;

	private int status;
	private int viewCount;
	
	private List<ProductDataDto> productData;
	
	@JsonIgnore
	private CategoryDto category;

	@JsonIgnore
	private SupplierDto supplier;
	
	
	private List<SkuDto> skuses;

	@JsonIgnore
	private List<Long> favoriteIds;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int isApprove() {
		return approve;
	}

	public void setApprove(int approve) {
		this.approve = approve;
	}

	public int isAvailableForSale() {
		return availableForSale;
	}

	public void setAvailableForSale(int availableForSale) {
		this.availableForSale = availableForSale;
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

	public String getImage() {
		if(image != null && !image.startsWith("http")) {
			return Constants.RESOURCE_DOMAIN + image;
		}
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImageAlbum() {
		return imageAlbum;
	}

	public void setImageAlbum(String imageAlbum) {
		this.imageAlbum = imageAlbum;
	}

	public int getIsBestSeller() {
		return isBestSeller;
	}

	public void setIsBestSeller(int isBestSeller) {
		this.isBestSeller = isBestSeller;
	}

	public int getIsCommingDeal() {
		return isCommingDeal;
	}

	public void setIsCommingDeal(int isCommingDeal) {
		this.isCommingDeal = isCommingDeal;
	}

	public int getIsHot() {
		return isHot;
	}

	public void setIsHot(int isHot) {
		this.isHot = isHot;
	}

	public int getIsNew() {
		return isNew;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}

	public int getIsPromotion() {
		return isPromotion;
	}

	public void setIsPromotion(int isPromotion) {
		this.isPromotion = isPromotion;
	}

	public int getIsReject() {
		return isReject;
	}

	public void setIsReject(int isReject) {
		this.isReject = isReject;
	}

	public int getIsShowHome() {
		return isShowHome;
	}

	public void setIsShowHome(int isShowHome) {
		this.isShowHome = isShowHome;
	}

	public int getIsTodayDeal() {
		return isTodayDeal;
	}

	public void setIsTodayDeal(int isTodayDeal) {
		this.isTodayDeal = isTodayDeal;
	}

	public float getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(float lastPrice) {
		this.lastPrice = lastPrice;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public int getSearchCount() {
		return searchCount;
	}

	public void setSearchCount(int searchCount) {
		this.searchCount = searchCount;
	}

	public int getSellCount() {
		return sellCount;
	}

	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}

	public int getShareCount() {
		return shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public List<ProductDataDto> getProductData() {
		return productData;
	}

	public void setProductData(List<ProductDataDto> productData) {
		this.productData = productData;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public SupplierDto getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierDto supplier) {
		this.supplier = supplier;
	}

	public List<SkuDto> getSkuses() {
		return skuses;
	}

	public void setSkuses(List<SkuDto> skuses) {
		this.skuses = skuses;
	}

	public boolean validToSell() {
		return true;
	}

	public void setFavoriteIds(List<Long> favoriteIds) {
		this.favoriteIds = favoriteIds;
	}

	@JsonProperty("is_favorite")
	public boolean getIsFavorite() {
		if (favoriteIds != null && !favoriteIds.isEmpty() && favoriteIds.contains(this.getId())){
            return true;
        }
		
		return false;
	}
	
}