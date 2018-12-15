package net.needii.dto.request;

/**
 * The persistent class for the products database table.
 * 
 */
public class ProductRequest extends BaseRequest {
	
	private Long id;
	
	private String name;
	private String slug;
	private String shortDescription;
	private String description;
	private String unit;
	private Integer languageId;
	private String startTime;
	private String endTime;
	private String warrantyPeriod;
	private String warrantyType;
	private String productStatus;
	
	private String isDeliveryGlobalFree;
	private String deliveryFeeInCity;
	private String freeForOrderAmountReachToInCity;
	private String freeFoFrderAmountFeachToInGlobal;
	
	private String deliveryGlobalFee;
	
	
	private String rejectReason;
	
	private String image;
	
	private int approve;

	private float price;

	private float lastPrice;
	
	private int quantity;

	private float cashbackPercent;

	private float discountPercent;

	private int status;
	
	private int isReject;
	
	private int isNew;
	
	private int isHot;
	
	private int isBestSeller;
	
	private int isShowHome;
	
	private int isPromotion;
	
	private int isTodayDeal;
	
	private int isCommingDeal;
	
	private int availableForSale;
	
	private int categoryId;
	
	private long supplierId;

	private String skuCode;
	
	
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

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getWarrantyPeriod() {
		return warrantyPeriod;
	}

	public void setWarrantyPeriod(String warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}

	public String getWarrantyType() {
		return warrantyType;
	}

	public void setWarrantyType(String warrantyType) {
		this.warrantyType = warrantyType;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int isApprove() {
		return approve;
	}

	public void setApprove(int approve) {
		this.approve = approve;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int isReject() {
		return isReject;
	}

	public void setIsReject(int isReject) {
		this.isReject = isReject;
	}

	public int isNew() {
		return isNew;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}

	public int isHot() {
		return isHot;
	}

	public void setIsHot(int isHot) {
		this.isHot = isHot;
	}

	public int isBestSeller() {
		return isBestSeller;
	}

	public void setIsBestSeller(int isBestSeller) {
		this.isBestSeller = isBestSeller;
	}

	public int isShowHome() {
		return isShowHome;
	}

	public void setIsShowHome(int isShowHome) {
		this.isShowHome = isShowHome;
	}

	public int isPromotion() {
		return isPromotion;
	}

	public void setIsPromotion(int isPromotion) {
		this.isPromotion = isPromotion;
	}

	public int isTodayDeal() {
		return isTodayDeal;
	}

	public void setIsTodayDeal(int isTodayDeal) {
		this.isTodayDeal = isTodayDeal;
	}

	public int isCommingDeal() {
		return isCommingDeal;
	}

	public void setIsCommingDeal(int isCommingDeal) {
		this.isCommingDeal = isCommingDeal;
	}

	public int isAvailableForSale() {
		return availableForSale;
	}

	public void setIsAvailableForSale(int availableForSale) {
		this.availableForSale = availableForSale;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	
	public String getIsDeliveryGlobalFree() {
		return isDeliveryGlobalFree;
	}

	public void setIsDeliveryGlobalFree(String isDeliveryGlobalFree) {
		this.isDeliveryGlobalFree = isDeliveryGlobalFree;
	}

	public String getDeliveryFeeInCity() {
		return deliveryFeeInCity;
	}

	public void setDeliveryFeeInCity(String deliveryFeeInCity) {
		this.deliveryFeeInCity = deliveryFeeInCity;
	}

	public String getFreeForOrderAmountReachToInCity() {
		return freeForOrderAmountReachToInCity;
	}

	public void setFreeForOrderAmountReachToInCity(String freeForOrderAmountReachToInCity) {
		this.freeForOrderAmountReachToInCity = freeForOrderAmountReachToInCity;
	}

	public String getFreeFoFrderAmountFeachToInGlobal() {
		return freeFoFrderAmountFeachToInGlobal;
	}

	public void setFreeFoFrderAmountFeachToInGlobal(String freeFoFrderAmountFeachToInGlobal) {
		this.freeFoFrderAmountFeachToInGlobal = freeFoFrderAmountFeachToInGlobal;
	}

	public String getDeliveryGlobalFee() {
		return deliveryGlobalFee;
	}

	public void setDeliveryGlobalFee(String deliveryGlobalFee) {
		this.deliveryGlobalFee = deliveryGlobalFee;
	}
	
	
	
	
}