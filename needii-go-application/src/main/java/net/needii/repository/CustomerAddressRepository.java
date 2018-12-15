package net.needii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.needii.jpa.entity.CustomerAddress;

/**
 * @author vincent
 *
 */
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long>{
	
	List<CustomerAddress> findByCustomerId(long customerId);
	
    
}
