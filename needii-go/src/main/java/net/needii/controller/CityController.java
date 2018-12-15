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
import org.springframework.web.bind.annotation.RestController;

import net.needii.dto.BaseDto;

/**
 * @author Vincent
 *
 */
@RestController
@RequestMapping("cities")
public class CityController extends BaseController {

	
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> get(HttpServletRequest request) {
		BaseDto response = new BaseDto();
		
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
}
