/**
 * 
 */
package net.needii.service;

import java.util.List;

import net.needii.dto.SupplierReviewDto;

/**
 * @author kelvin
 *
 */
public interface SupplierReviewService {

	SupplierReviewDto findOne(long id);
	
	List<SupplierReviewDto> findBySupplierId(long supplierId);
	
	List<SupplierReviewDto> findBySupplierId(long supplierId, int limit, int offset);
	
	Long countBySupplierId(long supplierId);
	
	List<SupplierReviewDto> findByCustomerId(long customerId);
	
	List<SupplierReviewDto> findByCustomerId(long customerId, int limit, int offset);
	
	Long countByCustomerId(long customerId);
	
	void create(SupplierReviewDto entity);

    void update(SupplierReviewDto entity);

    void delete(SupplierReviewDto entity);
	
}
