/**
 * 
 */
package net.needii.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.needii.dto.BaseDto;
import net.needii.dto.TokenDto;
import net.needii.jpa.entity.security.NeediiUser;
import util.Utils;

/**
 * @author Vincent
 *
 */

@RestController
@PreAuthorize("hasRole('GUEST') or hasRole('CUSTOMER') or hasRole('SUPPLIER')")
public class BaseController {
	
	final static Logger logger = LogManager.getLogger(BaseController.class);
	
	public void setLog(Exception ex) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		logger.error(sw.toString());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<BaseDto> handleUserNotFoundException(MethodArgumentNotValidException ex, WebRequest request) {

		BaseDto response = new BaseDto();
		
		List<String> errors = ex.getBindingResult().getFieldErrors()
				.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
		
		response.setData(errors);
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<BaseDto> handleMissingParams(MissingServletRequestParameterException ex) {
		// Actual exception handling
		BaseDto response = new BaseDto();
		response.setData(null);

		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	

	public NeediiUser getCurrentUser(HttpServletRequest request) {
		try {
			String authorizeHeader = request.getHeader("Authorization");
			String token = authorizeHeader.replace("Bearer ", "");
			ObjectMapper mapper = new ObjectMapper();
			TokenDto data = mapper.readValue(Utils.decodeJWTToken(token), TokenDto.class);
			List<GrantedAuthority> authorities = new ArrayList<>();
			for (int i = 0; i < data.getAuthorities().length; i++) {
				authorities.add(new SimpleGrantedAuthority(data.getAuthorities()[i]));
			}
			return new NeediiUser(
					data.getPhone(), 
					data.getPhone(), 
					data.getId(), 
					data.getPhone(), 
					data.getEmail(), 
					new ArrayList<>(), 
					new ArrayList<>(), 
					new ArrayList<>(), 
					new ArrayList<>(),
					new ArrayList<>(),
					data.isIs_supplier(), 
					authorities);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
	
}
