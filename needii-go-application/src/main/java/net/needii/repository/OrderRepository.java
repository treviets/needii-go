/**
 * 
 */
package net.needii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.needii.jpa.entity.Order;

/**
 * @author kelvin
 *
 */
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	List<Order> findByCustomerId(long customerId);
	List<Order> findByOrderStatus(int status);
	Order findByOrderCode(String code);
}
