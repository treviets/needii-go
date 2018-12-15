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
import net.needii.dto.DistrictDto;
import net.needii.dto.ResponseStatusEnum;
import net.needii.service.DistrictService;

/**
 * @author kelvin
 *
 */
@RestController
@RequestMapping("api/districts")
public class DistrictController extends BaseController {
	
	@Autowired
	private DistrictService districtService;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> get(HttpServletRequest request, @RequestParam(name="city_id") int cityId) {
		BaseDto response = new BaseDto();
		try {
			List<DistrictDto> entities = districtService.findByCityId(cityId);
			response.setData((new DistrictDto()).mapToListResponse(entities));
			
		} catch(Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
}
