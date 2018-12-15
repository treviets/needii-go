/**
 * 
 */
package net.needii.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.icu.text.SimpleDateFormat;

import net.needii.dto.BaseDto;
import net.needii.dto.BaseListDataDto;
import net.needii.dto.CategoryDto;
import net.needii.dto.ProductDataDto;
import net.needii.dto.ProductDto;
import net.needii.dto.ResponseStatusEnum;
import net.needii.dto.SkuDataDto;
import net.needii.dto.SkuDto;
import net.needii.dto.SupplierDto;
import net.needii.dto.request.ProductRequest;
import net.needii.jpa.entity.security.NeediiUser;
import net.needii.service.CategoryService;
import net.needii.service.ProductService;
import net.needii.service.SupplierService;
import util.Constants;
import util.Option;
import util.Pagination;

/**
 * @author kelvin
 *
 */
@RestController
@RequestMapping("api/products")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;
	
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SupplierService supplierService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> get(
			HttpServletRequest request,
			@RequestParam(name="category_ids", required = false, defaultValue = "0") int categoryIds,
			@RequestParam(name="supplier_id", required = false, defaultValue  = "0") long supplierId,
            @RequestParam(name="page", required = false, defaultValue  = "1") int offset,
            @RequestParam(name="limit", required = false, defaultValue  = Constants.PAGE_NUMBER_REQUEST) int limit) {
		BaseDto response = new BaseDto();
		try {
			Option option = new Option();
            Map<String, Object> queryString = new HashMap<>();
            queryString.put("category_ids", categoryIds);
            queryString.put("supplier_id", supplierId);
            option.setQueryString(queryString);
			Pagination pagination = new Pagination(offset, limit);
			option.setPagination(pagination);
			
			NeediiUser neediiUser = this.getCurrentUser(request);
			
			List<ProductDto> productDtos = productService.findAll(option, false);
			for(ProductDto dto : productDtos) {
				dto.setFavoriteIds(neediiUser.getLikeProductIds());
			}
            BaseListDataDto data = new BaseListDataDto();
            data.setData(productDtos);
            data.setTotalRecord(productService.count(option));
            data.setLimit(pagination.getLimit());
            response.setData(data);
		} catch(Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> get(HttpServletRequest request, @PathVariable long id) {
		BaseDto response = new BaseDto();
		try {
            ProductDto productDto = productService.findOne(id);
            NeediiUser neediiUser = this.getCurrentUser(request);
            productDto.setFavoriteIds(neediiUser.getLikeProductIds());
			response.setData(productDto);
		} catch(Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> search(
			HttpServletRequest request,
			@RequestParam(name="keyword", required = false) String keyword,
			@RequestParam(name="offset", required = false, defaultValue  = "1") int offset) {
		BaseDto response = new BaseDto();
		try {
			offset = offset < 1 ? 0 : offset - 1;
			BaseListDataDto data = new BaseListDataDto();
			
			data.setLimit(Constants.PAGE_NUMBER);
            response.setData(data);
		} catch(Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
			this.setLog(ex);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/create/", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> createProduct(HttpServletRequest request, @RequestBody ProductRequest productRequest){
		BaseDto response = new BaseDto();
		
		CategoryDto category = categoryService.findOne(productRequest.getCategoryId());
		SupplierDto supplier = supplierService.findOne(productRequest.getSupplierId());
		
        ProductDto product = new ProductDto();
        product.setCategory(category);
        product.setSupplier(supplier);
        product.setQuantity(productRequest.getQuantity());
        product.setIsNew(productRequest.isNew());
        product.setIsTodayDeal(productRequest.isTodayDeal());
        product.setIsCommingDeal(productRequest.isCommingDeal());
        product.setIsBestSeller(productRequest.isBestSeller());
        product.setIsHot(productRequest.isHot());
        product.setApprove(0);
        product.setIsShowHome(productRequest.isShowHome());
        product.setIsPromotion(productRequest.isPromotion());
        product.setAvailableForSale(productRequest.isAvailableForSale());
        product.setStatus(0);
        product.setPrice(productRequest.getPrice());
        product.setLastPrice(productRequest.getLastPrice());
        product.setCashbackPercent(productRequest.getCashbackPercent() != 0 ? productRequest.getCashbackPercent() : category.getCashbackPercent());
        product.setDiscountPercent(productRequest.getDiscountPercent());
        product.setIsReject(productRequest.isReject());
        product.setRejectReason(productRequest.getRejectReason());
        product.setImage(productRequest.getImage().replace(Constants.RESOURCE_DOMAIN, ""));
        
        //set for product data
        ProductDataDto productData = new ProductDataDto();
        productData.setDescription(productRequest.getDescription());
        productData.setIsDeleted(false);
        productData.setLanguageId(Constants.DEFAULT_VIETNAMESE_LANGUAGE_ID);
        productData.setName(productRequest.getName());
        productData.setProduct(product);
        productData.setShortDescription(productRequest.getShortDescription());
        productData.setSlug(productRequest.getSlug());
        productData.setUnit(productRequest.getUnit());
        productData.setEndTime(productRequest.getEndTime());
        productData.setProductStatus(productRequest.getProductStatus());
        productData.setStartTime(productRequest.getStartTime());
        productData.setWarrantyPeriod(productRequest.getWarrantyPeriod());
        productData.setWarrantyType(productRequest.getWarrantyType());
        
        productData.setDeliveryFeeInCity(productRequest.getDeliveryFeeInCity());
        productData.setDeliveryGlobalFee(productRequest.getDeliveryGlobalFee());
        productData.setIsDeliveryGlobalFree(productRequest.getIsDeliveryGlobalFree());
        productData.setFreeForOrderAmountReachToInCity(productRequest.getFreeForOrderAmountReachToInCity());
        productData.setFreeForOrderAmountReachToInGlobal(productRequest.getFreeFoFrderAmountFeachToInGlobal());
        
        List<ProductDataDto> productDatas = new ArrayList<>();
        productDatas.add(productData);
        product.setProductData(productDatas);
        
        //set for sku
        SkuDto sku = new SkuDto();
        sku.setSkuCode(productRequest.getSkuCode());
        sku.setCashbackPercent(productRequest.getCashbackPercent());
        sku.setDiscountPercent(productRequest.getDiscountPercent());
        sku.setImageSlide(productRequest.getImage().replace(Constants.RESOURCE_DOMAIN, ""));
        sku.setIsDefault(true);
        sku.setLastPrice(productRequest.getLastPrice());
        sku.setPrice(productRequest.getPrice());
        sku.setProduct(product);
        sku.setQuantity(productRequest.getQuantity());
        sku.setStatus(false);
        //set for Sku data
        SkuDataDto skuData = new SkuDataDto();
        skuData.setLanguage(Constants.DEFAULT_VIETNAMESE_LANGUAGE_ID);
        skuData.setName(productRequest.getName());
        skuData.setSkus(sku);
        Set<SkuDataDto> skuDatas = new HashSet<>();
        skuDatas.add(skuData);
        
        List<SkuDto> skus = new ArrayList<>();
        skus.add(sku);
        product.setSkuses(skus);
        
        ProductDto productDto = productService.create(product);
       
        response.setData(productDto);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/upload-image/", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<BaseDto> retainAsset(@RequestParam("file") MultipartFile file)
			throws IOException, NoSuchAlgorithmException {
		BaseDto response = new BaseDto();
		response.setData(processRetainAsset(file));
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	private String processRetainAsset(MultipartFile file) throws IOException, NoSuchAlgorithmException {
		String rootPath = "/opt/tomcat/webapps/needii";
		String fileName = "/static/upload/products/"+ new SimpleDateFormat("yyyyMMddmmmssss").format(new Date()) + file.getOriginalFilename();
		File uploadFile = new File(rootPath+fileName);
		
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
		FileCopyUtils.copy(file.getInputStream(), stream);
		stream.close();

		return Constants.RESOURCE_DOMAIN+fileName;
	}
	
	@RequestMapping(value = "/update/", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> updateProduct(@RequestBody ProductRequest productRequest){
		BaseDto response = new BaseDto();
		ProductDto product = productService.findOne(productRequest.getId());
		if(product == null) {
			response.setStatus(ResponseStatusEnum.NOT_FOUND);
			response.setMessageError("Product is not existing");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		CategoryDto category = categoryService.findOne(productRequest.getCategoryId());
		SupplierDto supplier = supplierService.findOne(productRequest.getSupplierId());
		
        product.setCategory(category);
        product.setSupplier(supplier);
        product.setQuantity(productRequest.getQuantity());
        product.setIsNew(productRequest.isNew());
        product.setIsTodayDeal(productRequest.isTodayDeal());
        product.setIsCommingDeal(productRequest.isCommingDeal());
        product.setIsBestSeller(productRequest.isBestSeller());
        product.setIsHot(productRequest.isHot());
        product.setApprove(0);
        product.setIsShowHome(productRequest.isShowHome());
        product.setIsPromotion(productRequest.isPromotion());
        product.setAvailableForSale(productRequest.isAvailableForSale());
        product.setStatus(productRequest.getStatus());
        product.setPrice(productRequest.getPrice());
        product.setLastPrice(productRequest.getLastPrice());
        product.setCashbackPercent(productRequest.getCashbackPercent() != 0 ? productRequest.getCashbackPercent() : category.getCashbackPercent());
        product.setDiscountPercent(productRequest.getDiscountPercent());
        product.setIsReject(productRequest.isReject());
        product.setRejectReason(productRequest.getRejectReason());
        
		productService.update(product);
        return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
