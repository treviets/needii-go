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
import org.springframework.web.bind.annotation.RestController;

import net.needii.dto.BaseDto;
import net.needii.dto.CityDto;
import net.needii.dto.ResponseStatusEnum;
import net.needii.service.CityService;

/**
 * @author kelvin
 *
 */
@RestController
@RequestMapping("api/cities")
public class CityController extends BaseController {
	
	@Autowired
	private CityService cityService;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> get(HttpServletRequest request) {
		BaseDto response = new BaseDto();
		try {
			List<CityDto> entities = cityService.findAll();
			response.setData((new CityDto()).mapToListResponse(entities));
			
		} catch(Exception ex) {
			this.setLog(ex);
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
}
