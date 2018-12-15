package net.needii.service;

import java.util.List;

import net.needii.dto.CityDto;

public interface CityService {
	
	List<CityDto> findAll();
	
	Long count();
	
	CityDto findOne(int id);
}
