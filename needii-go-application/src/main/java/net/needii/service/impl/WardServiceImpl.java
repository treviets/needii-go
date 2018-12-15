package net.needii.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.needii.dto.WardDto;
import net.needii.jpa.entity.Ward;
import net.needii.repository.WardRepository;
import net.needii.service.WardService;

@Transactional
@Service
public class WardServiceImpl implements WardService{

	@Autowired
	private WardRepository dao;
	
	@Override
	public List<WardDto> findAll() {
		return this.map(dao.findAll());
	}
	
	@Override
	public Long count() {
		return dao.count();
	}
	
	@Override
	public WardDto findOne(int id) {
		ModelMapper mapper = new ModelMapper();
		WardDto wardDto = new WardDto();
		mapper.map(dao.findOne(id), wardDto);
		return wardDto;
	}

	@Override
	public List<WardDto> findByDistrictId(int districtId) {
		return this.map(dao.findByDistrictId(districtId));
	}
	
	private List<WardDto> map(List<Ward> source) {
		ModelMapper mapper = new ModelMapper();
		List<WardDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			WardDto dto = new WardDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}

}
