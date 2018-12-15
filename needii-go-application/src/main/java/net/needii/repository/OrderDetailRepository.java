/**
 * 
 */
package net.needii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.needii.jpa.entity.Order;
import net.needii.jpa.entity.OrderDetail;

/**
 * @author kelvin
 *
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
	
	List<OrderDetail> findBySupplierId(long supplierId);
	List<OrderDetail> findByOrder(Order order);
	List<OrderDetail> findBySupplierIdAndOrderId(long supplierId, Order order);
	
}
