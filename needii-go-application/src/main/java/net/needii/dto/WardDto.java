/**
 * 
 */
package net.needii.dto;

import java.util.ArrayList;
import java.util.List;

import net.needii.jpa.entity.Ward;

public class WardDto extends BaseModelResponseImpl {
	
	private int id;
	
	private String name;
	
	public WardDto() {
	}
	
	public WardDto(Ward entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> mapToListResponse(List<?> baseEntities) {
		List<Ward> entities = (List<Ward>) baseEntities;
		
		List<WardDto> list =  new ArrayList<WardDto>(); 
		for (Ward entity : entities) {
			list.add(new WardDto(entity));
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