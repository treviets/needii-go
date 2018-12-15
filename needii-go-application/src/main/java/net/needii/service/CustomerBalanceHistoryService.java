package net.needii.service;

import java.util.List;

import net.needii.dto.CustomerBalanceHistoryDto;

public interface CustomerBalanceHistoryService {
	
	List<CustomerBalanceHistoryDto> findByCustomerId(long customerId);
	
	List<CustomerBalanceHistoryDto> findByCustomerId(long customerId, int limit, int offset);
	
	long count(long customerId);
	
	CustomerBalanceHistoryDto findOne(long id);
    
    void create(CustomerBalanceHistoryDto entity);

    void update(CustomerBalanceHistoryDto entity);

    void delete(CustomerBalanceHistoryDto entity);
}
