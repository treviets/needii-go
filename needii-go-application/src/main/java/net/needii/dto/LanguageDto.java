/**
 * 
 */
package net.needii.dto;

import java.util.ArrayList;
import java.util.List;

import net.needii.jpa.entity.Language;

public class LanguageDto extends BaseModelResponseImpl {
	
	private int id;
	
	private String name;
	
	private String code;
	
	private String flag;
	
	public LanguageDto() {
	}
	
	public LanguageDto(Language entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.code = entity.getCode();
		this.flag = entity.getFlag();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> mapToListResponse(List<?> baseEntities) {
		List<Language> entities = (List<Language>) baseEntities;
		
		List<LanguageDto> list =  new ArrayList<LanguageDto>(); 
		for (Language entity : entities) {
			list.add(new LanguageDto(entity));
		}
		return list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}