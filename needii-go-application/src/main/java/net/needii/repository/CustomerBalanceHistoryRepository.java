/**
 * 
 */
package net.needii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.needii.jpa.entity.CustomerBalanceHistory;

/**
 * @author Vincent
 *
 */
public interface CustomerBalanceHistoryRepository extends JpaRepository<CustomerBalanceHistory, Long>{
	
	List<CustomerBalanceHistory> findByCustomerId(long customerId);
	
}
