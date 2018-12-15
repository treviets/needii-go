/**
 * 
 */
package net.needii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.needii.jpa.entity.Product;

/**
 * @author Vincent
 *
 */
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findByIdIn(Long[] ids);
	
}
