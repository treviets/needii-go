/**
 * 
 */
package net.needii.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.needii.dto.BaseDto;

/**
 * @author kelvin
 *
 */
@RestController
@RequestMapping("api/districts")
public class DistrictController extends BaseController {
	
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> get(HttpServletRequest request, @RequestParam(name="city_id") int cityId) {
		BaseDto response = new BaseDto();
		
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
}
