package net.needii.service;

import java.util.List;

import net.needii.dto.ProductDto;
import net.needii.jpa.entity.Product;
import util.Option;

public interface ProductService {
	
	List<ProductDto> findAll();
	
	List<ProductDto> findAll(Option option, Boolean... isForCount);

    Long count(Option option);
	
	List<ProductDto> findByIds(Long[] ids);
	
	List<ProductDto> findByIdsNoSort(Long[] ids);
	
	ProductDto findOne(Long id);
    
    ProductDto create(ProductDto entiry);

    ProductDto update(ProductDto product);
    
    void delete(ProductDto productDto);
    public List<ProductDto> map(List<Product> source);
    public List<Product> revertMap(List<ProductDto> source);
}
