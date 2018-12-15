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
import net.needii.dto.ResponseStatusEnum;
import net.needii.dto.WardDto;
import net.needii.service.WardService;

/**
 * @author kelvin
 *
 */
@RestController
@RequestMapping("api/wards")
public class WardController extends BaseController {
	
	@Autowired
	private WardService wardService;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> get(HttpServletRequest request, @RequestParam(name="district_id") int districtId) {
		BaseDto response = new BaseDto();
		try {
			List<WardDto> entities = wardService.findByDistrictId(districtId);
			response.setData((new WardDto()).mapToListResponse(entities));
			
		} catch(Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
}
