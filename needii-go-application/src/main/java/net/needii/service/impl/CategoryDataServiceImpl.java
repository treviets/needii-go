/**
 * 
 */
package net.needii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.needii.jpa.entity.CategoryData;
import net.needii.repository.CategoryDataRepository;
import net.needii.service.CategoryDataService;
/**
 * @author kelvin
 *
 */
@Transactional
@Service
public class CategoryDataServiceImpl implements CategoryDataService{
	@Autowired
	private CategoryDataRepository dao;
	
	@Override
	public List<CategoryData> findByCategoryId(long categoryId) {
		return dao.findByCategoryId(categoryId);
	}
	
	@Override
	public CategoryData findOne(long id) {
		return dao.findOne(id);
	}

	@Override
	public void create(CategoryData entity) {
		dao.save(entity);
	}

	@Override
	public void update(CategoryData entity) {
		dao.save(entity);
	}

	@Override
	public void delete(CategoryData entity) {
		// TODO Auto-generated method stub
		dao.delete(entity);
	}
	

}
