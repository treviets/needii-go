/**
 * 
 */
package net.needii.service;

import java.util.List;

import net.needii.dto.CategoryDto;
import net.needii.jpa.entity.Category;

/**
 * @author kelvin
 *
 */
public interface CategoryService {

	List<CategoryDto> findAll();
	
	List<CategoryDto> findByLevel(int level);
	
	List<CategoryDto> findByCategoryParentId(int parentId);
	
	List<CategoryDto> findByIds(Long[] ids) ;
	
	CategoryDto findOne(int id);
	
	CategoryDto findOne(int id, String language);
    
	CategoryDto findOneByName(String name);
    
    void create(Category entity);

    void update(Category entity);

    void delete(Category entity);
	
}
