/**
 * 
 */
package net.needii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.needii.jpa.entity.Skus;

/**
 * @author kelvin
 *
 */
public interface SkuRepository extends JpaRepository<Skus, Long>{
	List<Skus> findByProductId(long productId);
}
