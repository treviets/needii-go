package net.needii.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.needii.dto.CityDto;
import net.needii.jpa.entity.City;
import net.needii.repository.CityRepository;
import net.needii.service.CityService;

@Transactional
@Service
public class CityServiceImpl implements CityService{

	@Autowired
	private CityRepository dao;
	
	@Override
	public List<CityDto> findAll() {
return this.map(dao.findAll());
	}
	
	@Override
	public Long count() {
		return dao.count();
	}
	
	@Override
	public CityDto findOne(int id) {
		ModelMapper mapper = new ModelMapper();
		CityDto cityDto = new CityDto();
		mapper.map(dao.findOne(id), cityDto);
		return cityDto;
	}
	
	private List<CityDto> map(List<City> source) {
		ModelMapper mapper = new ModelMapper();
		List<CityDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			CityDto dto = new CityDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}

}
