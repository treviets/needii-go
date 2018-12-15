package net.needii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.needii.jpa.entity.CartItem;

/**
 * @author vincent
 *
 */
public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	
	List<CartItem> findByCartId(long cartId);
    
}
