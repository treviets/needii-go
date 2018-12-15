package net.needii.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.needii.dto.OrderDetailDto;
import net.needii.jpa.entity.Order;
import net.needii.jpa.entity.OrderDetail;
import net.needii.repository.OrderDetailRepository;
import net.needii.repository.OrderRepository;
import net.needii.service.OrderDetailService;

@Transactional
@Service
public class OrderDetailServiceImpl implements OrderDetailService{

	@Autowired
	private OrderDetailRepository dao;
	
	@Autowired OrderRepository orderRepository;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Override
	public List<OrderDetailDto> findByOrderId(long orderId) {
		Order order = orderRepository.findOne(orderId);
		return this.map(dao.findByOrder(order));
	}
	
	@Override
	public List<OrderDetailDto> findBySupplierId(long supplierId) {
		return this.map(orderDetailRepository.findBySupplierId(supplierId));
	}
	
	@Override
	public OrderDetailDto findOne(long id) {
		ModelMapper mapper = new ModelMapper();
		OrderDetailDto dto = new OrderDetailDto();
		mapper.map(dao.findOne(id), dto);
		return dto;
	}


	@Override
	public void create(OrderDetailDto orderDetailDto) {
		ModelMapper mapper = new ModelMapper();
		OrderDetail entity = new OrderDetail();
		mapper.map(orderDetailDto, entity);
		dao.save(entity);
	}

	@Override
	public void update(OrderDetailDto orderDetailDto) {
		ModelMapper mapper = new ModelMapper();
		OrderDetail entity = new OrderDetail();
		mapper.map(orderDetailDto, entity);
		dao.save(entity);
	}

	@Override
	public void delete(OrderDetailDto orderDetailDto) {
		ModelMapper mapper = new ModelMapper();
		OrderDetail entity = new OrderDetail();
		mapper.map(orderDetailDto, entity);
		dao.delete(entity);
	}

	@Override
	public List<OrderDetailDto> findBySupplierId(long supplierId, int limit, int offset) {
		return this.map(dao.findBySupplierId(supplierId));
	}

	@Override
	public Long count(long supplierId) {
		return Long.valueOf(dao.findBySupplierId(supplierId).size());
	}

	@Override
	public List<OrderDetailDto> findBySupplierIdAndOrderId(long supplierId, long orderId) {
		Order order = orderRepository.findOne(orderId);
		
		return this.map(dao.findBySupplierIdAndOrderId(supplierId, order));
	}
	
	public List<OrderDetailDto> map(List<OrderDetail> source) {
		ModelMapper mapper = new ModelMapper();
		List<OrderDetailDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			OrderDetailDto dto = new OrderDetailDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}
}
