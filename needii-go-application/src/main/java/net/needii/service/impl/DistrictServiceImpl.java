package net.needii.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.needii.dto.DistrictDto;
import net.needii.jpa.entity.District;
import net.needii.repository.DistrictRepository;
import net.needii.service.DistrictService;

@Transactional
@Service
public class DistrictServiceImpl implements DistrictService{

	@Autowired
	private DistrictRepository dao;
	
	@Override
	public List<DistrictDto> findAll() {
		return this.map(dao.findAll());
	}
	
	@Override
	public Long count() {
		return dao.count();
	}
	
	@Override
	public DistrictDto findOne(int id) {
		ModelMapper mapper = new ModelMapper();
		DistrictDto districtDto = new DistrictDto();
		mapper.map(dao.findOne(id), districtDto);
		return districtDto;
	}

	@Override
	public List<DistrictDto> findByCityId(int cityId) {
		return this.map(dao.findByCityId(cityId));
	}

	private List<DistrictDto> map(List<District> source) {
		ModelMapper mapper = new ModelMapper();
		List<DistrictDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			DistrictDto dto = new DistrictDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}
}
