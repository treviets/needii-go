package net.needii.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.needii.dto.ProductDto;
import net.needii.jpa.entity.Product;
import net.needii.repository.CategoryRepository;
import net.needii.repository.ProductRepository;
import net.needii.repository.SupplierRepository;
import net.needii.service.ProductService;
import util.Option;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	SupplierRepository supplierRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@PersistenceContext
	private EntityManager entityManager;
	private CriteriaBuilder creteriaBuilder;

	ModelMapper mapper;

	@Override
	public List<ProductDto> findAll() {
		return this.map(productRepository.findAll());
	}

	@Override
	public List<ProductDto> findAll(Option option, Boolean... isForCount) {
		CriteriaQuery<Product> products = creteriaBuilder.createQuery(Product.class);
		Root<Product> rootProducts = products.from(Product.class);
		products.select(rootProducts);

		if (option.getQueryString().get("category_ids") != null) {
			if (option.getQueryString().get("supplier_id") != null) {
				
				products.where(
						creteriaBuilder.equal(rootProducts.get("category"),
								categoryRepository.findOne(Integer.valueOf(option.getQueryString().get("category_ids").toString()))),
						creteriaBuilder.equal(rootProducts.get("supplier"),
								supplierRepository.findOne(Long.valueOf(option.getQueryString().get("supplier_id").toString()))));
			} else {
				products.where(creteriaBuilder.equal(rootProducts.get("category"),
						option.getQueryString().get("category_ids")));
			}
		} else {
			if (option.getQueryString().get("supplier_id") != null) {
				products.where(creteriaBuilder.equal(rootProducts.get("supplier"),
						option.getQueryString().get("supplier_id")));
			}
		}

		TypedQuery<Product> finalQuery = entityManager.createQuery(products);
		if (isForCount == null || !isForCount[0]) {
			// paging
			finalQuery.setFirstResult(option.getPagination().getOffset());
			finalQuery.setMaxResults(option.getPagination().getLimit());
		}

		return this.map(finalQuery.getResultList());
	}

	@Override
	public Long count(Option option) {
		return Long.valueOf(findAll(option, true).size());
	}

	@Override
	public ProductDto findOne(Long id) {
		ProductDto productDto = new ProductDto();
		Product productEntity = productRepository.getOne(id);
		if (productEntity == null) {
			return null;
		}
		mapper.map(productEntity, productDto);
		return productDto;
	}

	@Override
	public ProductDto create(ProductDto productDto) {
		Product productEntity = new Product();
		mapper.map(productDto, productEntity);
		mapper.map(productRepository.save(productEntity), productDto);
		return productDto;
	}

	@Override
	public ProductDto update(ProductDto productDto) {
		Product productEntity = new Product();
		mapper.map(productDto, productEntity);
		mapper.map(productRepository.save(productEntity), productDto);
		return productDto;
	}

	@Override
	public void delete(ProductDto productDto) {
		Product productEntity = new Product();
		mapper.map(productDto, productEntity);
		productRepository.delete(productEntity);
	}

	@Override
	public List<ProductDto> findByIds(Long[] ids) {
		List<Product> products = productRepository.findByIdIn(ids);
		return this.map(products);
	}

	@Override
	public List<ProductDto> findByIdsNoSort(Long[] ids) {
		List<Product> products = productRepository.findByIdIn(ids);
		return this.map(products);
	}

	public List<ProductDto> map(List<Product> source) {
		List<ProductDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			ProductDto dto = new ProductDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}
	
	public List<Product> revertMap(List<ProductDto> source) {
		List<Product> entities = new ArrayList<>();
		source.stream().map((dto) -> {
			Product entity = new Product();
			mapper.map(dto, entity);
			return entity;
		}).forEachOrdered((entity) -> {
			entities.add(entity);
		});
		return entities;
	}
	
	@PostConstruct
	private void postInit() {
		mapper = new ModelMapper();
		mapper.addMappings(productPropertiesSkip);
		creteriaBuilder = entityManager.getCriteriaBuilder();
	}
	
PropertyMap<Product, ProductDto> productPropertiesSkip = new PropertyMap<Product, ProductDto>() {
		
		@Override
		protected void configure() {
			skip().getProductData().forEach((element)->{
				element.setProduct(null);
			});
			skip().getSkuses().forEach((skuElement)->{
				skuElement.setProduct(null);
				skuElement.getSkuData().forEach((e)->{
					e.setSkus(null);
				});
			});
			skip().getSupplier().setBankInfo(null);
			skip().getSupplier().setCompanyProfile(null);
			skip().getSupplier().setOrderDetails(null);
			skip().getSupplier().setSupplierAddress(null);
			skip().getSupplier().setSupplierAddresses(null);
			skip().getSupplier().setSupplierRole(null);
			
		}
	};
}
