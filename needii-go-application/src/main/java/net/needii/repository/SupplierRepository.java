/**
 * 
 */
package net.needii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.needii.jpa.entity.Supplier;

/**
 * @author kelvin
 *
 */
public interface SupplierRepository extends JpaRepository<Supplier, Long>{
	
	Supplier findByEmail(String email);
	
	Supplier findByPhone(String phone);
	List<Supplier> findByLatAndLng(double lat, double lng);
}
