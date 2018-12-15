/**
 * 
 */
package net.needii.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.needii.jpa.entity.Advertisement;

public class AdvertisementDto extends BaseModelResponseImpl {
	
	private int id;
	
	@JsonProperty("supplier_id")
	private int supplierId;

    @JsonProperty("full_name")
	private String fullName;
	
	private String infomation;
	
	private List<String> images;

    @JsonProperty("logo_url")
    private String logoUrl;

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
	
	public AdvertisementDto() {
	}
	
	public AdvertisementDto(Advertisement entity) {
		this.id = entity.getId();
		this.supplierId = entity.getSupplierId();
		this.fullName = entity.getName();
		this.infomation = entity.getDescription();
		this.images = entity.getImageList();
		this.shareCount = entity.getShareCount();
		this.likeCount = entity.getLikeCount();
		this.logoUrl = "https://i.imgur.com/7DPHn0q.jpg";
		this.subscribeCount = 0;
		this.isLike = false;
		this.isSubscribe = false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> mapToListResponse(List<?> baseEntities) {
		List<Advertisement> entities = (List<Advertisement>) baseEntities;
		
		List<AdvertisementDto> list =  new ArrayList<AdvertisementDto>(); 
		for (Advertisement entity : entities) {
			list.add(new AdvertisementDto(entity));
		}
		return list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getInfomation() {
		return infomation;
	}

	public void setInfomation(String infomation) {
		this.infomation = infomation;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}