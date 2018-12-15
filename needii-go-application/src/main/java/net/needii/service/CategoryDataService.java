/**
 * 
 */
package net.needii.service;

import java.util.List;

import net.needii.jpa.entity.CategoryData;

/**
 * @author kelvin
 *
 */
public interface CategoryDataService {

	List<CategoryData> findByCategoryId(long categoryId);
	
	CategoryData findOne(long id);
    
    void create(CategoryData entity);

    void update(CategoryData entity);

    void delete(CategoryData entity);
	
}
