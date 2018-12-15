package net.needii.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.needii.dto.SkuDto;
import net.needii.jpa.entity.Skus;
import net.needii.repository.SkuRepository;
import net.needii.service.SkuService;

@Transactional
@Service
public class SkuServiceImpl implements SkuService{

	@Autowired
	private SkuRepository dao;
	
	@Override
	public List<SkuDto> findByProductId(long productId) {
		return this.map(dao.findByProductId(productId));
	}
	
	@Override
	public SkuDto findOne(long id) {
		ModelMapper mapper = new ModelMapper();
		SkuDto dto = new SkuDto();
		mapper.map(dao.findOne(id) , dto);
		return dto;
	}


	@Override
	public void create(SkuDto dto) {
		ModelMapper mapper = new ModelMapper();
		Skus entity = new Skus();
		mapper.map(dto,entity);
		dao.save(entity);
	}

	@Override
	public void update(SkuDto dto) {
		ModelMapper mapper = new ModelMapper();
		Skus entity = new Skus();
		mapper.map(dto,entity);
		dao.save(entity);
	}

	@Override
	public void delete(SkuDto dto) {
		ModelMapper mapper = new ModelMapper();
		Skus entity = new Skus();
		mapper.map(dto,entity);
		dao.delete(entity);
	}

	public List<SkuDto> map(List<Skus> source) {
		ModelMapper mapper = new ModelMapper();
		List<SkuDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			SkuDto dto = new SkuDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}
}
