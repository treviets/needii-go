/**
 * 
 */
package net.needii.service;

import net.needii.dto.CartDto;

/**
 * @author kelvin
 *
 */
public interface CartService {

	CartDto findOne(long id);
	
	CartDto findByCustomerId(long customerId);
	
	CartDto findByDeviceUID(String deviceUID);
	
	void create(CartDto cartDto);

    void update(CartDto cartDto);

    void delete(CartDto cartDto);
    
    void createOrUpdate(CartDto cartDto);
	
}
