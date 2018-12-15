package net.needii.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.needii.dto.OrderDto;
import net.needii.jpa.entity.Order;
import net.needii.jpa.entity.OrderStatusEnum;
import net.needii.repository.OrderRepository;
import net.needii.service.OrderService;

@Transactional
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository dao;
	
	@Override
	public List<OrderDto> findAll() {
		return this.map(dao.findAll());
	}
	
	@Override
	public List<OrderDto> findAll(OrderStatusEnum statusEnum) {
		return this.map(dao.findByOrderStatus(statusEnum.getValue()));
	}
	
	@Override
	public List<OrderDto> findByCustomerId(long customerId) {
		return this.map(dao.findByCustomerId(customerId));
	}
	
	@Override
	public List<OrderDto> findByCustomerId(long customerId, int limit, int offset) {
		return this.map(dao.findByCustomerId(customerId));
	}
	
	@Override
	public Long count(long customerId) {
		return Long.valueOf(dao.findByCustomerId(customerId).size());
	}
	
	@Override
	public OrderDto findOne(long id) {
		ModelMapper mapper = new ModelMapper();
		OrderDto orderDto = new OrderDto();
		mapper.map(dao.findOne(id),orderDto);
		return orderDto;
	}
	
	@Override
	public OrderDto findByOrderCode(String code) {
		ModelMapper mapper = new ModelMapper();
		OrderDto orderDto = new OrderDto();
		mapper.map(dao.findByOrderCode(code),orderDto);
		return orderDto;
	}


	@Override
	public OrderDto create(OrderDto orderDto) {
		ModelMapper mapper = new ModelMapper();
		Order entity = new Order();
		mapper.map(orderDto, entity);
		mapper.map(dao.save(entity), orderDto);
		return orderDto;
	}

	@Override
	public OrderDto update(OrderDto orderDto) {
		ModelMapper mapper = new ModelMapper();
		Order entity = new Order();
		mapper.map(orderDto, entity);
		mapper.map(dao.save(entity), orderDto);
		return orderDto;
	}

	@Override
	public void delete(OrderDto orderDto) {
		ModelMapper mapper = new ModelMapper();
		Order entity = new Order();
		mapper.map(orderDto, entity);
		dao.delete(entity);
	}
	
	public List<OrderDto> map(List<Order> source) {
		ModelMapper mapper = new ModelMapper();
		List<OrderDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			OrderDto dto = new OrderDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}

}
