/**
 * 
 */
package net.needii.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.needii.jpa.entity.Customer;

/**
 * @author kelvin
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	Customer findByEmail(String email);
	
	Customer findByPhone(String phone);
	
	Customer findByReferenceCode(String refereneCode);
}
