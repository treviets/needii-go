/**
 * 
 */
package net.needii.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.needii.dto.AdvertisementDto;
import net.needii.dto.BaseDto;
import net.needii.dto.ResponseStatusEnum;
import net.needii.jpa.entity.Advertisement;

/**
 * @author kelvin
 *
 */
@RestController
@RequestMapping("api/advertisements")
public class AdvertisementController extends BaseController {
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> get(HttpServletRequest request) {
		BaseDto response = new BaseDto();
		try {
			List<Advertisement> entities = new ArrayList<Advertisement>();
			for (int i = 0; i < 10; i++) {
				Advertisement entity = new Advertisement();
				entities.add(entity);
			}
			response.setData((new AdvertisementDto()).mapToListResponse(entities));
		} catch(Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
}
