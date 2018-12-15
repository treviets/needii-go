package net.needii.service;

import java.util.List;

import net.needii.dto.OrderDetailDto;

public interface OrderDetailService {
	
	List<OrderDetailDto> findByOrderId(long orderId);
	
	List<OrderDetailDto> findBySupplierIdAndOrderId(long supplierId, long orderid);
	
	List<OrderDetailDto> findBySupplierId(long supplierId);
	
	List<OrderDetailDto> findBySupplierId(long supplierId, int limit, int offset);
	
	Long count(long supplierId);
	
	OrderDetailDto findOne(long id);
    
    void create(OrderDetailDto entity);

    void update(OrderDetailDto entity);

    void delete(OrderDetailDto entity);
}
