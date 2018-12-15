/**
 * 
 */
package net.needii.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductListDto{

    private Long id;
    private String name;
    @JsonProperty("supplier_full_name")
    private String supplierFullName;
    private float price;
    @JsonProperty("last_price")
    private float lastPrice;
    private String description;
    @JsonProperty("short_description")
    private String shortDescription;
    private String image;
    @JsonProperty("supplier_image")
    private String supplierImage;
    @JsonProperty("is_approve")
    private boolean isApprove;
    private int quantity;
    @JsonProperty("cashback_percent")
    private float cashbackPercent;
    @JsonProperty("discount_percent")
    private float discountPercent;
    private boolean status;
    @JsonProperty("is_reject")
    private boolean isReject;
    @JsonProperty("is_new")
    private boolean isNew;
    @JsonProperty("is_hot")
    private boolean isHot;
    @JsonProperty("is_best_seller")
    private boolean isBestSeller;
    @JsonProperty("is_show_home")
    private boolean isShowHome;
    @JsonProperty("is_promotion")
    private boolean isPromotion;
    @JsonProperty("is_today_deal")
    private boolean isTodayDeal;
    @JsonProperty("is_comming_deal")
    private boolean isCommingDeal;
    @JsonProperty("available_for_sale")
    private boolean availableForSale;
    @JsonProperty("is_favorite")
    private boolean isFavorite;
    @JsonProperty("sku_responses")
    private List<SkuDto> skuDtos;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Long> favoriteIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean getIsApprove() {
        return isApprove;
    }

    public void setApprove(boolean isApprove) {
        this.isApprove = isApprove;
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

    public boolean status() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getIsReject() {
        return isReject;
    }

    public void setIsReject(boolean isReject) {
        this.isReject = isReject;
    }

    public boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public boolean getIsHot() {
        return isHot;
    }

    public void setIsHot(boolean isHot) {
        this.isHot = isHot;
    }

    public boolean getIsBestSeller() {
        return isBestSeller;
    }

    public void setBestSeller(boolean isBestSeller) {
        this.isBestSeller = isBestSeller;
    }

    public boolean getIsShowHome() {
        return isShowHome;
    }

    public void setShowHome(boolean isShowHome) {
        this.isShowHome = isShowHome;
    }

    public boolean getIsPromotion() {
        return isPromotion;
    }

    public void setPromotion(boolean isPromotion) {
        this.isPromotion = isPromotion;
    }

    public boolean getIsTodayDeal() {
        return isTodayDeal;
    }

    public void setIsTodayDeal(boolean isTodayDeal) {
        this.isTodayDeal = isTodayDeal;
    }

    public boolean getIsCommingDeal() {
        return isCommingDeal;
    }

    public void setCommingDeal(boolean isCommingDeal) {
        this.isCommingDeal = isCommingDeal;
    }

    public boolean isAvailableForSale() {
        return availableForSale;
    }

    public void setAvailableForSale(boolean availableForSale) {
        this.availableForSale = availableForSale;
    }

    public List<SkuDto> getSkuResponses() {
        return skuDtos;
    }

    public void setSkuResponses(List<SkuDto> skuDtos) {
        this.skuDtos = skuDtos;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public List<Long> getFavoriteIds() {
        return favoriteIds;
    }

    public ProductListDto setFavoriteIds(List<Long> favoriteIds) {
        this.favoriteIds = favoriteIds;
        return this;
    }
    public String getSupplierFullName() {
        return supplierFullName;
    }

    public void setSupplierFullName(String supplierFullName) {
        this.supplierFullName = supplierFullName;
    }

    public String getSupplierImage() {
        return supplierImage;
    }

    public void setSupplierImage(String supplierImage) {
        this.supplierImage = supplierImage;
    }
}