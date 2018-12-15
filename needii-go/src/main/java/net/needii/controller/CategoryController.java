/**
 * 
 */
package net.needii.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.needii.dto.BaseDto;
import net.needii.dto.CategoryDto;
import net.needii.dto.ResponseStatusEnum;
import net.needii.service.CategoryService;

/**
 * @author kelvin
 *
 */
@RestController
@RequestMapping("api/categories")
public class CategoryController extends BaseController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> get(HttpServletRequest request, @RequestParam(name="parent_id") int parentId) {
		BaseDto response = new BaseDto();
		try {
			List<CategoryDto> categoriesDto = categoryService.findByCategoryParentId(parentId);
			response.setData(categoriesDto);
			
		} catch(Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/last-child", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> getLastChild(HttpServletRequest request, @RequestParam(name="parent_id") int parentId) {
		BaseDto response = new BaseDto();
		try {
			List<CategoryDto> categoriesDto = categoryService.findByCategoryParentId(parentId);
			response.setData(categoriesDto);
			
		} catch(Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
}
