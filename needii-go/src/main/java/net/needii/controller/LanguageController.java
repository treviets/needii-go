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
import net.needii.dto.LanguageDto;
import net.needii.dto.ResponseStatusEnum;
import net.needii.jpa.entity.Language;
import net.needii.service.LanguageService;

/**
 * @author kelvin
 *
 */
@RestController
@RequestMapping("api/languages")
public class LanguageController extends BaseController {
	
	@Autowired
	private LanguageService languageService;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> get(HttpServletRequest request) {
		BaseDto response = new BaseDto();
		try {
			List<Language> entities = languageService.findAll();
			response.setData((new LanguageDto()).mapToListResponse(entities));
			
		} catch(Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
}
