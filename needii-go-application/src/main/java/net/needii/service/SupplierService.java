/**
 * 
 */
package net.needii.service;

import java.util.List;

import net.needii.dto.SupplierDto;
import util.Option;

/**
 * @author kelvin
 *
 */
public interface SupplierService {

    List<SupplierDto> findAll(Option option);

    Long count(Option option);
    
    List<SupplierDto> findByLocation(double lat, double lng, double distance);

    SupplierDto findOne(long id);
    
	SupplierDto findEmail(String email);
	
	SupplierDto findByPhone(String phone);
    
	SupplierDto create(SupplierDto entity);

	SupplierDto update(SupplierDto entity);

    void delete(SupplierDto entity);
	
}
