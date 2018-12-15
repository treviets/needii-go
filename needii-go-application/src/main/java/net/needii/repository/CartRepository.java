package net.needii.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.needii.jpa.entity.Cart;

/**
 * @author vincent
 *
 */
public interface CartRepository extends JpaRepository<Cart, Long>{
	
	Cart findByCustomerId(long customerId);
	
	Cart findByDeviceUID(String deviceUID);
    
}
