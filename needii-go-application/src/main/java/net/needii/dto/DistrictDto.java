/**
 * 
 */
package net.needii.dto;

import java.util.ArrayList;
import java.util.List;

import net.needii.jpa.entity.District;

public class DistrictDto extends BaseModelResponseImpl {
	
	private int id;
	
	private String name;
	
	public DistrictDto() {
	}
	
	public DistrictDto(District entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> mapToListResponse(List<?> baseEntities) {
		List<District> entities = (List<District>) baseEntities;
		
		List<DistrictDto> list =  new ArrayList<DistrictDto>(); 
		for (District entity : entities) {
			list.add(new DistrictDto(entity));
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
}