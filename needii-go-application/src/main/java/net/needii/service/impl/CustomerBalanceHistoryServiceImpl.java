package net.needii.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.needii.dto.CustomerBalanceHistoryDto;
import net.needii.jpa.entity.CustomerBalanceHistory;
import net.needii.repository.CustomerBalanceHistoryRepository;
import net.needii.service.CustomerBalanceHistoryService;

@Transactional
@Service
public class CustomerBalanceHistoryServiceImpl implements CustomerBalanceHistoryService{

	@Autowired
	private CustomerBalanceHistoryRepository dao;
	
	@Override
	public List<CustomerBalanceHistoryDto> findByCustomerId(long customerId) {
		return this.map(dao.findByCustomerId(customerId));
	}
	
	@Override
	public CustomerBalanceHistoryDto findOne(long id) {
		ModelMapper mapper = new ModelMapper();
		CustomerBalanceHistoryDto customerBalanceHistoryDto = new CustomerBalanceHistoryDto();
		mapper.map(dao.findOne(id), customerBalanceHistoryDto);
		return customerBalanceHistoryDto;
	}


	@Override
	public void create(CustomerBalanceHistoryDto customerBalanceHistoryDto) {
		ModelMapper mapper = new ModelMapper();
		CustomerBalanceHistory entity = new CustomerBalanceHistory();
		mapper.map(customerBalanceHistoryDto, entity);
		dao.save(entity);
	}

	@Override
	public void update(CustomerBalanceHistoryDto customerBalanceHistoryDto) {
		ModelMapper mapper = new ModelMapper();
		CustomerBalanceHistory entity = new CustomerBalanceHistory();
		mapper.map(customerBalanceHistoryDto, entity);
		dao.save(entity);
	}

	@Override
	public void delete(CustomerBalanceHistoryDto customerBalanceHistoryDto) {
		ModelMapper mapper = new ModelMapper();
		CustomerBalanceHistory entity = new CustomerBalanceHistory();
		mapper.map(customerBalanceHistoryDto, entity);
		dao.delete(entity);
	}

	@Override
	public List<CustomerBalanceHistoryDto> findByCustomerId(long customerId, int limit, int offset) {
		return this.map(dao.findByCustomerId(customerId));
	}

	@Override
	public long count(long customerId) {
		return Long.valueOf(dao.findByCustomerId(customerId).size());
	}
	
	public List<CustomerBalanceHistoryDto> map(List<CustomerBalanceHistory> source) {
		ModelMapper mapper = new ModelMapper();
		List<CustomerBalanceHistoryDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			CustomerBalanceHistoryDto dto = new CustomerBalanceHistoryDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}

}
