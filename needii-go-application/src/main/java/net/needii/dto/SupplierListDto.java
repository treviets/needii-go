package net.needii.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.needii.jpa.entity.Supplier;
import util.Constants;

public class SupplierListDto extends BaseModelResponseImpl{
    private Long id;
    @JsonProperty("supplier_id")
    private Long supplierId;
    @JsonProperty("full_name")
    private String fullName;
    private String information;
    @JsonProperty("logo_url")
    private String logoUrl;
    private List<String> images;

    @JsonProperty("like_count")
    private int likeCount;
    @JsonProperty("subscribe_count")
    private int subscribeCount;
    @JsonProperty("share_count")
    private int shareCount;

    @JsonProperty("is_like")
    private boolean isLike;
    @JsonProperty("is_subscribe")
    private boolean isSubscribe;

    public SupplierListDto(){}

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Long> likeIds;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Long> subscribeIds;

    public SupplierListDto(Supplier entity, List<Long> likeIds, List<Long> subscribeIds) {
        this.id = entity.getId();
        this.supplierId = entity.getId();
        this.fullName = entity.getFullName();
        this.information = entity.getInformation();
        this.logoUrl = entity.getLogoUrl();
        this.shareCount = entity.getShareCount();
        this.subscribeCount = entity.getSubscribeCount();
        this.likeCount = entity.getLikeCount();
        this.isLike = false;
        this.isSubscribe = false;
        if (likeIds != null && !likeIds.isEmpty() && likeIds.contains(this.getId())){
            this.isLike = true;
        }
        if (subscribeIds != null && !subscribeIds.isEmpty() && subscribeIds.contains(this.getId())){
            this.isSubscribe = true;
        }

        List<String> tmp_images = new ArrayList<>();
        tmp_images.add(Constants.RESOURCE_DOMAIN + "/static/images/supplier/product1.jpg");
        int[] number =  {2,3,4,6,7,8};
        for (int i = 0; i < 3; i++) {
            tmp_images.add(Constants.RESOURCE_DOMAIN + "/static/images/supplier/product" + this.getRandom(number) + ".jpg");
        }
        if(images == null) {
        		this.images = tmp_images;
        }
        
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<?> mapToListResponse(List<?> baseEntities ) {
        List<Supplier> entities = (List<Supplier>) baseEntities;
        List<SupplierListDto> list =  new ArrayList<>();
        for (Supplier entity : entities) {
            list.add(new SupplierListDto(entity, this.likeIds, this.subscribeIds));
        }
        return list;
    }

    public String getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return String.valueOf(array[rnd]);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getLogoUrl() {
    		if(logoUrl != null && !logoUrl.contains("http")) {
    			return Constants.RESOURCE_DOMAIN + logoUrl;
    		}
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getSubscribeCount() {
        return subscribeCount;
    }

    public void setSubscribeCount(int subscribeCount) {
        this.subscribeCount = subscribeCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public boolean getIsLike() {
        return isLike;
    }

    public void setIsLike(boolean isLike) {
        this.isLike = isLike;
    }

    public boolean getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(boolean isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public List<Long> getLikeIds() {
        return likeIds;
    }

    public SupplierListDto setLikeIds(List<Long> likeIds) {
        this.likeIds = likeIds;
        return this;
    }

    public List<Long> getSubscribeIds() {
        return subscribeIds;
    }

    public SupplierListDto setSubscribeIds(List<Long> subscribeIds) {
        this.subscribeIds = subscribeIds;
        return this;
    }
}
