/**
 * 
 */
package net.needii.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.needii.dto.CustomerDto;
import net.needii.jpa.entity.Customer;
import net.needii.repository.CustomerRepository;
import net.needii.service.CustomerService;

/**
 * @author Vincent
 *
 */
@Transactional
@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerRepository dao;
	
	@Override
	public CustomerDto findOne(long id) {
		ModelMapper mapper = new ModelMapper();
		CustomerDto customerDto = new CustomerDto();
		Customer customer = dao.findOne(id);
		if(customer == null) {
			return null;
		}
		mapper.map(customer, customerDto);
		return customerDto;
	}
	
	@Override
	public CustomerDto findByReferenceCode(String code) {
		ModelMapper mapper = new ModelMapper();
		CustomerDto customerDto = new CustomerDto();
		mapper.map(findByReferenceCode(code), customerDto);
		return customerDto;
	}
	
	@Override
	public CustomerDto findByEmail(String email) {
		ModelMapper mapper = new ModelMapper();
		CustomerDto customerDto = new CustomerDto();
		mapper.map(findByEmail(email), customerDto);
		return customerDto;
	}
	
	@Override
	public CustomerDto findByPhone(String phone) {
		ModelMapper mapper = new ModelMapper();
		CustomerDto customerDto = new CustomerDto();
		mapper.map(dao.findByPhone(phone), customerDto);
		return customerDto;
	}

	@Override
	public void create(CustomerDto customerDto) {
		ModelMapper mapper = new ModelMapper();
		Customer customerEntity = new Customer();
		mapper.map(customerDto, customerEntity);
		dao.save(customerEntity);
	}

	@Override
	public void update(CustomerDto customerDto) {
		ModelMapper mapper = new ModelMapper();
		Customer customerEntity = new Customer();
		mapper.map(customerDto, customerEntity);
		dao.save(customerEntity);
	}

	@Override
	public void delete(CustomerDto customerDto) {
		ModelMapper mapper = new ModelMapper();
		Customer customerEntity = new Customer();
		mapper.map(customerDto, customerEntity);
		dao.delete(customerEntity);
	}
	

}
