/**
 * 
 */
package net.needii.service;

import net.needii.dto.CustomerDto;


/**
 * @author Vincent
 *
 */
public interface CustomerService {

	CustomerDto findOne(long id);
	
	CustomerDto findByReferenceCode(String code);
	
	CustomerDto findByEmail(String email);
	
	CustomerDto findByPhone(String phone);
    
    void create(CustomerDto customerDto);

    void update(CustomerDto customerDto);

    void delete(CustomerDto customerDto);
	
}
