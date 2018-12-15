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

import net.needii.dto.BannerDto;
import net.needii.dto.BaseDto;
import net.needii.dto.ResponseStatusEnum;
import net.needii.jpa.entity.Banner;
import net.needii.jpa.entity.Language;
import net.needii.service.BannerService;

/**
 * @author kelvin
 *
 */
@RestController
@RequestMapping("api/banners")
public class BannerController extends BaseController {
	
	@Autowired
	private BannerService bannerService;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> get(HttpServletRequest request) {
		BaseDto response = new BaseDto();
		try {
			Language language = this.getLanguage(request);
			List<Banner> entities = bannerService.findAll();
			response.setData((new BannerDto()).mapToListResponse(entities, language));
			
		} catch(Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
}
