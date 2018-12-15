/**
 * 
 */
package net.needii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.needii.jpa.entity.CategoryData;

/**
 * @author vincent
 *
 */
public interface CategoryDataRepository extends JpaRepository<CategoryData, Long>{

	List<CategoryData> findByCategoryId(long categoryId);
    
}
