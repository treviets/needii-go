/**
 * 
 */
package net.needii.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.needii.configuration.ApplicationProperties;
import net.needii.dto.BaseDto;
import net.needii.dto.CartDto;
import net.needii.dto.CustomerDto;
import net.needii.dto.ResponseStatusEnum;
import net.needii.dto.SupplierDto;
import net.needii.dto.TokenDto;
import net.needii.dto.request.CustomerRegisterRequest;
import net.needii.dto.request.CustomerVerifyRequest;
import net.needii.dto.request.LoginRequest;
import net.needii.dto.request.LogoutRequest;
import net.needii.dto.request.RefreshTokenRequest;
import net.needii.dto.request.RegisterDeviceRequest;
import net.needii.jpa.entity.Customer;
import net.needii.jpa.entity.Device;
import net.needii.jpa.entity.TokenModel;
import net.needii.repository.CustomerRepository;
import net.needii.service.CartService;
import net.needii.service.CustomerService;
import net.needii.service.DeviceService;
import net.needii.service.SupplierService;
import util.SmsService;
import util.Utils;

/**
 * @author kelvin
 *
 */
@RestController
@RequestMapping("")
public class ConfigController {
	
	@Autowired
	private ApplicationProperties config;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private DeviceService deviceService;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<BaseDto> handleUserNotFoundException(MethodArgumentNotValidException ex, WebRequest request) {

		BaseDto response = new BaseDto();
		response.setStatus(ResponseStatusEnum.FAIL);
		response.setMessageError("Validation Error");
		
		List<String> errors = ex.getBindingResult().getFieldErrors()
				.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
		
		response.setData(errors);
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/configs", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> configs(
			HttpServletRequest request) {
		BaseDto response = new BaseDto();
		try {
			byte[] encodedBytes = Base64.encodeBase64(String.format("%s:%s", "needii_guest", "bmVlZGlpX2d1ZXN0").getBytes());
			String apiKey = new String(encodedBytes);
			Utils.encryptOrderId(123);
			HttpResponse httpResponse =
				      Request.Post(config.getOauthEndpoint()).bodyForm(
				        Form.form()
				        .add("grant_type", "password")
				        .add("username", String.format("%s,%s,%s", "needii_guest", "needii_guest", "bmVlZGlpX2d1ZXN0"))
				        .add("password", "jwtpass").build())//
				      	.addHeader("Authorization", String.format("Basic %s", apiKey))
				        .execute().returnResponse();

			ObjectMapper mapper = new ObjectMapper();
			TokenDto tokenData = mapper.readValue(EntityUtils.toString(httpResponse.getEntity()), TokenDto.class);
			Object data = new Object() {
				public final String api_key = tokenData.getAccess_token();
				public final String type = "Bearer";
				public final int ads_step = 3;
                public final int version = 1;
				public final String firebase = "https://needii-1519459840839.firebaseio.com";
			};
			response.setData(data);

		} catch(Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('GUEST')")
	@RequestMapping(value = "/api/customers/login", method = RequestMethod.POST, consumes = {MediaType.ALL_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> loginCustomer(HttpServletRequest request, @RequestBody LoginRequest wrapper) {
		BaseDto response = new BaseDto();
		
//		try {
			TokenDto data = customerLogin(wrapper.getPhone(), wrapper.getPassword(), wrapper.getDeviceUID());
			if(data == null) {
				response.setStatus(ResponseStatusEnum.FAIL);
				response.setMessageError("customer.login.fail");
			} else {
				response.setData(data);
			}
//		} catch(Exception ex) {
//			response.setStatus(ResponseStatusEnum.TOKEN_EXPIRED);
//			response.setMessage(ResponseStatusEnum.TOKEN_EXPIRED);
//			ex.printStackTrace();
//		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('GUEST')")
	@RequestMapping(value = "/api/customers/register", method = RequestMethod.POST, consumes = {MediaType.ALL_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> registerCustomer(HttpServletRequest request, @RequestBody CustomerRegisterRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customerDto = customerService.findByPhone(wrapper.getPhone());
			if(customerDto == null) {
				customerDto = new CustomerDto();
				customerDto.setPhone(wrapper.getPhone());
				customerDto.setFullName(wrapper.getPhone());
				customerDto.setEmail(String.format("%s@needii.com", wrapper.getPhone()));
				customerService.create(customerDto);
				SmsService.sendVerifyMessage(wrapper.getPhone(), customerDto.getVerifyCode());
				response.setData(customerDto.getVerifyCode());
			} else {
				if(customerDto.isAuthenticated()) {
					response.setStatus(ResponseStatusEnum.FAIL);
					response.setMessageError("customer.existed");
				} else {
					String verifyCode = Utils.createRandomNumber(6);
					customerDto.setVerifyCode(verifyCode);
					customerService.update(customerDto);
					SmsService.sendVerifyMessage(customerDto.getPhone(), customerDto.getVerifyCode());
				}
			}
		} catch(Exception ex) {
			response.setStatus(ResponseStatusEnum.TOKEN_EXPIRED);
			response.setMessage(ResponseStatusEnum.TOKEN_EXPIRED);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('GUEST')")
	@RequestMapping(value = "/api/customers/verify", method = RequestMethod.POST, consumes = {MediaType.ALL_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> verifyCustomer(HttpServletRequest request, @RequestBody CustomerVerifyRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customerDto = customerService.findByPhone(wrapper.getPhone());
			if(customerDto == null) {
				response.setStatus(ResponseStatusEnum.FAIL);
				response.setMessageError("customer.notfound");
			} else {
				if(customerDto.isAuthenticated()) {
					response.setStatus(ResponseStatusEnum.FAIL);
					response.setMessageError("customer.existed");
				} else {
					if(customerDto.getVerifyCode().equals(wrapper.getVerifyCode())) {
						customerDto.setPassword(Utils.encodeShaPassword(customerDto.getVerifyCode()));
						customerDto.setAuthenticated(true);
						customerService.update(customerDto);
						TokenDto data = customerLogin(wrapper.getPhone(), customerDto.getVerifyCode(), wrapper.getDeviceUID());
						if(data == null) {
							response.setStatus(ResponseStatusEnum.DEVICE_NOT_FOUND);
							response.setMessage(ResponseStatusEnum.DEVICE_NOT_FOUND);
						} else {
							response.setData(data);
						}
					} else {
						response.setStatus(ResponseStatusEnum.FAIL);
						response.setMessageError("customer.verifycode.wrong");
					}
				}
			}
		} catch(Exception ex) {
			response.setStatus(ResponseStatusEnum.TOKEN_EXPIRED);
			response.setMessage(ResponseStatusEnum.TOKEN_EXPIRED);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('GUEST')")
	@RequestMapping(value = "/api/customers/reset-password", method = RequestMethod.POST, consumes = {MediaType.ALL_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> resetPasswordCustomer(HttpServletRequest request, @RequestBody CustomerRegisterRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customerDto = customerService.findByPhone(wrapper.getPhone());
			if(customerDto == null) {
				response.setStatus(ResponseStatusEnum.FAIL);
				response.setMessageError("customer.notfound");
			} else {
				String newVerifyCode = Utils.createRandomNumber(6);
				customerDto.setVerifyCode(newVerifyCode);
				customerDto.setPassword(Utils.encodeShaPassword(newVerifyCode));
				customerService.update(customerDto);
				SmsService.sendVerifyMessage(customerDto.getPhone(), newVerifyCode);
			}
		} catch(Exception ex) {
			response.setStatus(ResponseStatusEnum.TOKEN_EXPIRED);
			response.setMessage(ResponseStatusEnum.TOKEN_EXPIRED);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('GUEST')")
	@RequestMapping(value = "/api/register-device", method = RequestMethod.POST, consumes = {MediaType.ALL_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> registerDevice(
			HttpServletRequest request,
			@RequestBody RegisterDeviceRequest wrapper) {
		BaseDto response = new BaseDto();
		
		try {
			Device entity = deviceService.findOne(wrapper.getDeviceUID());
			if(entity == null) {
				entity = new Device();
				entity.setDeviceUID(wrapper.getDeviceUID());
				entity.setOsName(wrapper.getOsName());
			}
			entity.setPushToken(wrapper.getPushToken());
			deviceService.createOrUpdate(entity);
			
		} catch(Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/refresh-token", method = RequestMethod.POST, consumes = {MediaType.ALL_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> refreshToken(HttpServletRequest request, @RequestBody RefreshTokenRequest wrapper) {
		BaseDto response = new BaseDto();
		
		try {
			byte[] encodedBytes = Base64.encodeBase64(String.format("%s:%s", config.getClientId(), config.getClientSecret()).getBytes());
			String apiKey = new String(encodedBytes);
			
			HttpResponse httpResponse = 
				      Request.Post(config.getOauthEndpoint()).bodyForm(
				        Form.form()
				        .add("grant_type", "refresh_token")
				        .add("refresh_token", wrapper.getRefreshToken()).build())
				      	.addHeader("Authorization", String.format("Basic %s", apiKey))
				        .execute().returnResponse();
				
			ObjectMapper mapper = new ObjectMapper();
			TokenDto data = mapper.readValue(EntityUtils.toString(httpResponse.getEntity()), TokenDto.class);
			
			TokenModel tokenModel = mapper.readValue(Utils.decodeJWTToken(data.getAccess_token()), TokenModel.class);
			
			CustomerDto user;
			if(tokenModel.isIs_supplier()) {
				user = new CustomerDto();
				SupplierDto supplierResponse = supplierService.findByPhone(tokenModel.getUser_name());
				user.setId(supplierResponse.getId());
				user.setPhone(supplierResponse.getPhone());
				user.setFullName(supplierResponse.getFullName());
				user.setEmail(user.getEmail());
			} else {
				user = customerService.findByPhone(tokenModel.getUser_name());
			}
			data.setId(user.getId());
			data.setName(user.getFullName());
			data.setEmail(user.getEmail());
			data.setPhone(user.getPhone());
			data.setToken_type("Bearer");
			response.setData(data);
		} catch(Exception ex) {
			response.setStatus(ResponseStatusEnum.TOKEN_EXPIRED);
			response.setMessage(ResponseStatusEnum.TOKEN_EXPIRED);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('GUEST') or hasRole('CUSTOMER') or hasRole('SUPPLIER')")
	@RequestMapping(value = "/api/logout", method = RequestMethod.POST, consumes = {MediaType.ALL_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> loginSupplier(HttpServletRequest request, @RequestBody LogoutRequest wrapper) {
		BaseDto response = new BaseDto();
		
		try {
			Device entity = deviceService.findOne(wrapper.getDeviceUID());
			if(entity != null) {
				entity.setUserId(0);
				entity.setLastLoginAt(new Date());
				deviceService.update(entity);
				updateCartAfterLogout(wrapper.getDeviceUID());
			}
		} catch(Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
	
	private TokenDto getTokenInfo(String phone, String password) {
		try {
			byte[] encodedBytes = Base64.encodeBase64(String.format("%s:%s", config.getClientId(), config.getClientSecret()).getBytes());
			String apiKey = new String(encodedBytes);
			
			HttpResponse httpResponse = 
				      Request.Post(config.getOauthEndpoint()).bodyForm(
				        Form.form()
				        .add("grant_type", "password")
				        .add("username", phone)
				        .add("password", password).build())//
				      	.addHeader("Authorization", String.format("Basic %s", apiKey))
				        .execute().returnResponse();
				
			ObjectMapper mapper = new ObjectMapper();
			TokenDto data = mapper.readValue(EntityUtils.toString(httpResponse.getEntity()), TokenDto.class);
		
			return data;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	private TokenDto customerLogin(String phone, String password, String deviceUID) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			TokenDto data = getTokenInfo(phone, password);
			
			TokenModel tokenModel = mapper.readValue(Utils.decodeJWTToken(data.getAccess_token()), TokenModel.class);
			Customer user = customerRepository.findByPhone(tokenModel.getUser_name());
			CustomerDto userDto = customerService.findByPhone(tokenModel.getUser_name());
			
			Device device = deviceService.findOne(deviceUID);
			if(device == null) {
				return null;
			} else {
				device.setUserId(user.getId());
				device.setSupplier(false);
				device.setLastLoginAt(new Date());
				deviceService.update(device);
				
				updateCartAfterLogin(userDto, deviceUID);
				
				data.setId(user.getId());
				data.setName(user.getFullName());
				data.setAvatar(user.getAvatar());
				data.setEmail(user.getEmail());
				data.setPhone(user.getPhone());
				data.setToken_type("Bearer");
				user.setLastLoginAt(new Date());
				customerRepository.save(user);
				
				return data;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	private void updateCartAfterLogin(CustomerDto customer, String deviceUID) {
		CartDto cart = cartService.findByDeviceUID(deviceUID);
		if(cart == null) {
			// trường hợp cart trên thiết bị không có, kiểm tra xem user đã có cart chưa, nếu có, gán cart cho thiết bị
			cart = cartService.findByCustomerId(customer.getId());
			if(cart != null) {
				cart.setDeviceUID(deviceUID);
				cartService.update(cart);
			}
		} else {
			// trường hợp đã có cart trên thiết bị, tiến hành xoá cart cũ của user, cập nhật cart mới
			// kiểm tra xem cart có thuộc về user khác không, nếu có, xoá thông tin thiết bị của cart đó để tránh lấy nhầm
			if(cart.getCustomer() == null) {
				CartDto oldCart = cartService.findByCustomerId(customer.getId());
				if(oldCart != null) {
					cartService.delete(oldCart);
				}
				cart.setCustomer(customer);
			} else {
				cart.setDeviceUID("");
			}
			cartService.update(cart);
		}
	}
	
	private void updateCartAfterLogout(String deviceUID) {
		CartDto cart = cartService.findByDeviceUID(deviceUID);
		if(cart != null) {
			cart.setDeviceUID("");
			cartService.update(cart);
		}
	}
}
