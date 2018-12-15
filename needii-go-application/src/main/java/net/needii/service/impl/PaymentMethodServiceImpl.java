package net.needii.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.needii.dto.PaymentMethodDto;
import net.needii.jpa.entity.PaymentMethod;
import net.needii.jpa.entity.PaymentMethodTypeEnum;
import net.needii.repository.PaymentMethodRepository;
import net.needii.service.PaymentMethodService;

@Transactional
@Service
public class PaymentMethodServiceImpl implements PaymentMethodService{

	@Autowired
	private PaymentMethodRepository dao;
	
	@Override
	public List<PaymentMethodDto> findAll() {
		return this.map(dao.findAll());
	}
	
	@Override
	public PaymentMethodDto findOne(int id) {
		ModelMapper mapper = new ModelMapper();
		PaymentMethodDto dto = new PaymentMethodDto();
		mapper.map(dao.findOne(id), dto);
		return dto;
	}


	@Override
	public void create(PaymentMethodDto dto) {
		ModelMapper mapper = new ModelMapper();
		PaymentMethod entity = new PaymentMethod();
		mapper.map(dto, entity);
		dao.save(entity);
	}

	@Override
	public void update(PaymentMethodDto dto) {
		ModelMapper mapper = new ModelMapper();
		PaymentMethod entity = new PaymentMethod();
		mapper.map(dto, entity);
		dao.save(entity);
	}

	@Override
	public void delete(PaymentMethodDto dto) {
		ModelMapper mapper = new ModelMapper();
		PaymentMethod entity = new PaymentMethod();
		mapper.map(dto, entity);
		dao.delete(entity);
	}

	@Override
	public PaymentMethodDto findByType(PaymentMethodTypeEnum typeEnum) {
		ModelMapper mapper = new ModelMapper();
		PaymentMethodDto dto = new PaymentMethodDto();
		mapper.map(dao.findByType(typeEnum.getValue()), dto);
		return dto;
	}

	public List<PaymentMethodDto> map(List<PaymentMethod> source) {
		ModelMapper mapper = new ModelMapper();
		List<PaymentMethodDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			PaymentMethodDto dto = new PaymentMethodDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}
	
}
