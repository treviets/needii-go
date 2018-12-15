package net.needii.service;

import java.util.List;

import net.needii.dto.OrderDto;
import net.needii.jpa.entity.OrderStatusEnum;

public interface OrderService {
	
	List<OrderDto> findAll();
	
	List<OrderDto> findAll(OrderStatusEnum statusEnum);
	
	List<OrderDto> findByCustomerId(long customerId);
	
	List<OrderDto> findByCustomerId(long customerId, int limit, int offset);
	
	Long count(long customerId);
	
	OrderDto findOne(long id);
	
	OrderDto findByOrderCode(String code);
	
	OrderDto create(OrderDto entity);

	OrderDto update(OrderDto entity);

	void delete(OrderDto entity);
}
