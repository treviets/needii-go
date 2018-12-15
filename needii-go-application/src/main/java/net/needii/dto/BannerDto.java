/**
 * 
 */
package net.needii.dto;

import java.util.ArrayList;
import java.util.List;

import net.needii.jpa.entity.Banner;
import net.needii.jpa.entity.BannerData;
import net.needii.jpa.entity.BannerMedia;
import net.needii.jpa.entity.Language;
import util.Constants;

public class BannerDto extends BaseModelResponseImpl {
	
	private int id;
	
	private String title;
	
	private String url;
	
	private String link;
	
	private int type;
	
	private long value;
	
	public BannerDto() {
	}
	
	public BannerDto(Banner entity, Language language) {
		BannerData data = entity.getData(language.getId(), true);
		BannerMedia media = data.getBannerMedia();
		this.id = entity.getId();
		this.title = data.getTitle();
		this.setUrl(Constants.RESOURCE_DOMAIN + media.getImage());
		this.setLink(data.getLink());
		this.type = data.getBannerType();
		this.value = 11;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> mapToListResponse(List<?> baseEntities, Language language) {
		List<Banner> entities = (List<Banner>) baseEntities;
		
		List<BannerDto> list =  new ArrayList<BannerDto>(); 
		for (Banner entity : entities) {
			list.add(new BannerDto(entity, language));
		}
		return list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}
}