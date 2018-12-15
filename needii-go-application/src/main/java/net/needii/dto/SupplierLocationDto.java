/**
 * 
 */
package net.needii.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.needii.jpa.entity.Supplier;

public class SupplierLocationDto extends BaseModelResponseImpl {
	
	private Long id;
	
	@JsonProperty("full_name")
	private String fullName;
	
	private String address;
	
	private String lat;
	
	private String lng;
	
	public SupplierLocationDto() {
	}
	
	public SupplierLocationDto(Supplier entity) {
		this.id = entity.getId();
		this.fullName = entity.getFullName();
		this.lat = entity.getLat();
		this.lng = entity.getLng();
		this.address = entity.getFullTextAddress();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> mapToListResponse(List<?> baseEntities) {
		List<Supplier> entities = (List<Supplier>) baseEntities;
		
		List<SupplierLocationDto> list =  new ArrayList<SupplierLocationDto>(); 
		for (Supplier entity : entities) {
			list.add(new SupplierLocationDto(entity));
		}
		return list;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}
}