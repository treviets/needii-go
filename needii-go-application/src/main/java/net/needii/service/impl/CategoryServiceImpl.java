/**
 * 
 */
package net.needii.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.needii.dto.CategoryDto;
import net.needii.jpa.entity.Category;
import net.needii.repository.CategoryRepository;
import net.needii.service.CategoryService;

/**
 * @author kelvin
 *
 */
@Transactional
@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepository dao;
	
	@Override
	public List<CategoryDto> findAll() {
		return this.map(dao.findAll());
	}
	
	@Override
	public List<CategoryDto> findByLevel(int level) {
		return this.map(dao.findByLevel(level));
	}
	
	@Override
	public List<CategoryDto> findByCategoryParentId(int parentId) {
		return this.map(dao.findByParentId(parentId));
	}
	
	@Override
	public List<CategoryDto> findByIds(Long[] ids) {
		return this.map(dao.findByIdIn(ids));
	}
	
	@Override
	public CategoryDto findOne(int id) {
		ModelMapper mapper = new ModelMapper();
		CategoryDto categoryDto = new CategoryDto();
		mapper.map(dao.findOne(id), categoryDto);
		
		return categoryDto;
	}
	
	@Override
	public CategoryDto findOne(int id, String language) {
		ModelMapper mapper = new ModelMapper();
		CategoryDto categoryDto = new CategoryDto();
		mapper.map(dao.findOne(id), categoryDto);
		
		return categoryDto;
	}

	@Override
	public CategoryDto findOneByName(String name) {
		return null;
		//return dao.findOne(name);
	}

	@Override
	public void create(Category entity) {
		dao.save(entity);
	}

	@Override
	public void update(Category entity) {
		dao.save(entity);
	}

	@Override
	public void delete(Category entity) {
		dao.delete(entity);
	}
	
	
	public List<CategoryDto> map(List<Category> source) {
		ModelMapper mapper = new ModelMapper();
		List<CategoryDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			CategoryDto dto = new CategoryDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}

}
