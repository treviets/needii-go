package net.needii.service;

import java.util.List;

import net.needii.dto.DistrictDto;

public interface DistrictService {
	
	List<DistrictDto> findAll();
	
	Long count();
	
	DistrictDto findOne(int id);
    
	List<DistrictDto> findByCityId(int cityId);
}
