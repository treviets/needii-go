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

import net.needii.dto.SupplierReviewDto;
import net.needii.jpa.entity.SupplierReview;
import net.needii.repository.SupplierReviewRepository;
import net.needii.service.SupplierReviewService;

/**
 * @author kelvin
 *
 */
@Transactional
@Service
public class SupplierReviewServiceImpl implements SupplierReviewService{
	@Autowired
	private SupplierReviewRepository dao;
	
	@Override
	public SupplierReviewDto findOne(long id) {
		ModelMapper mapper = new ModelMapper();
		SupplierReviewDto dto = new SupplierReviewDto();
		mapper.map(dao.findOne(id), dto);
		return dto;
	}
	
	@Override
	public List<SupplierReviewDto> findBySupplierId(long supplierId) {
		return this.map(dao.findBySupplierId(supplierId));
	}
	
	@Override
	public List<SupplierReviewDto> findByCustomerId(long customerId) {
		return this.map(dao.findByCustomerId(customerId));
	}

	@Override
	public void create(SupplierReviewDto dto) {
		ModelMapper mapper = new ModelMapper();
		SupplierReview entity = new SupplierReview();
		mapper.map(dto, entity);
		dao.save(entity);
	}

	@Override
	public void update(SupplierReviewDto dto) {
		ModelMapper mapper = new ModelMapper();
		SupplierReview entity = new SupplierReview();
		mapper.map(dto, entity);
		dao.save(entity);
	}

	@Override
	public void delete(SupplierReviewDto dto) {
		ModelMapper mapper = new ModelMapper();
		SupplierReview entity = new SupplierReview();
		mapper.map(dto, entity);
		dao.delete(entity);
	}

	@Override
	public List<SupplierReviewDto> findBySupplierId(long supplierId, int limit, int offset) {
		//paging will implement in the future
		return this.map(dao.findBySupplierId(supplierId));
	}

	@Override
	public Long countBySupplierId(long supplierId) {
		return dao.count();
	}

	@Override
	public List<SupplierReviewDto> findByCustomerId(long customerId, int limit, int offset) {
		// paging will implement in the future
		return this.map(dao.findByCustomerId(customerId));
	}

	@Override
	public Long countByCustomerId(long customerId) {
		return Long.valueOf(dao.findByCustomerId(customerId).size());
	}
	
	public List<SupplierReviewDto> map(List<SupplierReview> source) {
		ModelMapper mapper = new ModelMapper();
		List<SupplierReviewDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			SupplierReviewDto dto = new SupplierReviewDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}

}
