package net.needii.service;

import java.util.List;

import net.needii.dto.WardDto;

public interface WardService {
	
	List<WardDto> findAll();
	
	Long count();
	
	WardDto findOne(int id);
    
	List<WardDto> findByDistrictId(int districtId);
}
