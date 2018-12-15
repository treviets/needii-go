package net.needii.service;

import java.util.List;

import net.needii.dto.SkuDto;

public interface SkuService {
	
	List<SkuDto> findByProductId(long productId);
	
	SkuDto findOne(long id);
    
    void create(SkuDto entity);

    void update(SkuDto entity);

    void delete(SkuDto entity);
}
