/**
 * 
 */
package net.needii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.needii.jpa.entity.SupplierReview;

/**
 * @author kelvin
 *
 */
public interface SupplierReviewRepository extends JpaRepository<SupplierReview, Long>{
	List<SupplierReview> findBySupplierId(long supplierId);
	List<SupplierReview> findByCustomerId(long customerId);
}
