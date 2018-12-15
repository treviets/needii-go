/**
 * 
 */
package net.needii.service;

import java.util.List;

import net.needii.dto.CartItemDto;

/**
 * @author kelvin
 *
 */
public interface CartItemService {

	CartItemDto findOne(long id);
	
	List<CartItemDto> findByCartId(long cartId);
	
	void create(CartItemDto cartItemDto);

    void update(CartItemDto cartItemDto);

    void delete(CartItemDto cartItemDto);
    
    void createOrUpdate(CartItemDto cartItemDto);
	
}
