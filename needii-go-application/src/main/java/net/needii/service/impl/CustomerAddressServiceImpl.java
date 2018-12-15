package net.needii.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.needii.dto.CustomerAddressDto;
import net.needii.jpa.entity.CustomerAddress;
import net.needii.repository.CustomerAddressRepository;
import net.needii.service.CustomerAddressService;

@Transactional
@Service
public class CustomerAddressServiceImpl implements CustomerAddressService{

	@Autowired
	private CustomerAddressRepository dao;
	
	@Override
	public List<CustomerAddressDto> findAll() {
		return this.map(dao.findAll());
	}
	
	@Override
	public List<CustomerAddressDto> findByCustomerId(int customerId) {
		return this.map(dao.findByCustomerId(customerId));
	}
	
	@Override
	public CustomerAddressDto findOne(long id) {
		ModelMapper mapper = new ModelMapper();
		CustomerAddressDto customerAddressDto = new CustomerAddressDto();
		mapper.map(dao.findOne(id), customerAddressDto);
		return customerAddressDto;
	}


	@Override
	public void create(CustomerAddressDto customerAddressDto) {
		ModelMapper mapper = new ModelMapper();
		CustomerAddress customerAddress = new CustomerAddress();
		mapper.map(customerAddressDto, customerAddress);
		dao.save(customerAddress);
	}

	@Override
	public void update(CustomerAddressDto customerAddressDto) {
		ModelMapper mapper = new ModelMapper();
		CustomerAddress customerAddress = new CustomerAddress();
		mapper.map(customerAddressDto, customerAddress);
		dao.save(customerAddress);
	}

	@Override
	public void delete(CustomerAddressDto customerAddressDto) {
		ModelMapper mapper = new ModelMapper();
		CustomerAddress customerAddress = new CustomerAddress();
		mapper.map(customerAddressDto, customerAddress);
		
		dao.delete(customerAddress);
	}
	
	private List<CustomerAddressDto> map(List<CustomerAddress> source) {
		ModelMapper mapper = new ModelMapper();
		List<CustomerAddressDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			CustomerAddressDto dto = new CustomerAddressDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}

}
