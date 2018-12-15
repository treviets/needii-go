/**
 * 
 */
package net.needii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.needii.jpa.entity.Category;

/**
 * @author vincent
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	List<Category> findByLevel(int level);
	
	List<Category> findByParentId(int parentId);
	
	List<Category> findLastChildByParentId(int parentId);
	
	List<Category> findByIdIn(Long[] ids);
    
}
