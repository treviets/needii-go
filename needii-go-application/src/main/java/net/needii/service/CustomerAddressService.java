package net.needii.service;

import java.util.List;

import net.needii.dto.CustomerAddressDto;

public interface CustomerAddressService {
	
	List<CustomerAddressDto> findAll();
	
	List<CustomerAddressDto> findByCustomerId(int customerId);
	
	CustomerAddressDto findOne(long id);
    
    void create(CustomerAddressDto customerAddressDto);

    void update(CustomerAddressDto customerAddressDto);

    void delete(CustomerAddressDto customerAddressDto);
}
