/**
 * 
 */
package net.needii.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.icu.text.SimpleDateFormat;

import net.needii.dto.BaseDto;
import net.needii.dto.BaseListDataDto;
import net.needii.dto.CartDto;
import net.needii.dto.CartItemDto;
import net.needii.dto.CategoryDto;
import net.needii.dto.CityDto;
import net.needii.dto.CustomerAddressDto;
import net.needii.dto.CustomerBalanceHistoryDto;
import net.needii.dto.CustomerDto;
import net.needii.dto.DistrictDto;
import net.needii.dto.OrderDetailDto;
import net.needii.dto.OrderDto;
import net.needii.dto.ProductDto;
import net.needii.dto.ResponseStatusEnum;
import net.needii.dto.SkuDto;
import net.needii.dto.SupplierDto;
import net.needii.dto.WardDto;
import net.needii.dto.request.AddToCartRequest;
import net.needii.dto.request.ApplyReferenceRequest;
import net.needii.dto.request.CancelOrderDetailRequest;
import net.needii.dto.request.CancelOrderRequest;
import net.needii.dto.request.ConnectSocialRequest;
import net.needii.dto.request.CustomerAddressRequest;
import net.needii.dto.request.CustomerChangePasswordRequest;
import net.needii.dto.request.CustomerProfileRequest;
import net.needii.dto.request.DeleteAddressRequest;
import net.needii.dto.request.DeleteCartItemRequest;
import net.needii.dto.request.FavoriteProductRequest;
import net.needii.dto.request.LikeProductRequest;
import net.needii.dto.request.LikeSupplierRequest;
import net.needii.dto.request.SaveFavoriteCategoryRequest;
import net.needii.dto.request.SetDefaultAddressRequest;
import net.needii.dto.request.ShareProductRequest;
import net.needii.dto.request.ShareSupplierRequest;
import net.needii.dto.request.SubscribleSupplierRequest;
import net.needii.dto.request.TransferNeediiCashRequest;
import net.needii.dto.request.UpdateProfileRequest;
import net.needii.jpa.entity.AuthTypeEnum;
import net.needii.jpa.entity.Language;
import net.needii.jpa.entity.OrderDetailStatusEnum;
import net.needii.jpa.entity.OrderStatusEnum;
import net.needii.service.CartItemService;
import net.needii.service.CartService;
import net.needii.service.CategoryService;
import net.needii.service.CityService;
import net.needii.service.CustomerAddressService;
import net.needii.service.CustomerBalanceHistoryService;
import net.needii.service.CustomerService;
import net.needii.service.DistrictService;
import net.needii.service.OrderDetailService;
import net.needii.service.OrderService;
import net.needii.service.ProductService;
import net.needii.service.SupplierService;
import net.needii.service.WardService;
import util.Constants;
import util.Utils;

/**
 * @author kelvin
 *
 */
@RestController
@RequestMapping("api/customers")
public class CustomerController extends BaseController {
	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerAddressService customerAddressService;

	@Autowired
	private CustomerBalanceHistoryService customerBalanceHistoryService;

	@Autowired
	private CityService cityService;

	@Autowired
	private DistrictService districtService;

	@Autowired
	private WardService wardService;

	@Autowired
	private CartService cartService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private SupplierService supplierService;

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> profile(HttpServletRequest request, @PathVariable int id) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customerDto = customerService.findOne(getCurrentUser(request).getId());
			response.setData(customerDto);
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/upload-avatar", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> uploadAvatar(HttpServletRequest request, @PathVariable int id,
			@RequestParam("file") MultipartFile file) {
		BaseDto response = new BaseDto();
		try {
			if (file.isEmpty()) {
				response.setStatus(ResponseStatusEnum.FAIL);
				response.setMessageError("Customer.Avatar.Required");
			} else {
				String fileName = "/static/upload/customers/"
						+ new SimpleDateFormat("yyyyMMddmmmssss").format(new Date()) + file.getOriginalFilename();
				File uploadFile = new File(Constants.RESOURCE_PHYSIC_PATH + fileName);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
				FileCopyUtils.copy(file.getInputStream(), stream);
				stream.close();
				CustomerDto customerDto = customerService.findOne(getCurrentUser(request).getId());
				customerDto.setAvatar(fileName);
				customerService.update(customerDto);
				response.setData(Constants.RESOURCE_DOMAIN + fileName);
			}

		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/edit-profile", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> editProfile(HttpServletRequest request, @PathVariable int id,
			@Valid @RequestBody CustomerProfileRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customer = customerService.findOne(getCurrentUser(request).getId());
			if (wrapper.getBirthday() != null && !wrapper.getBirthday().isEmpty()) {
				DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
				DateTime birthday = formatter.parseDateTime(wrapper.getBirthday());
				customer.setBirthday(birthday.toDate());
			}
			customer.setFullName(wrapper.getFullName());
			customer.setEmail(wrapper.getEmail());
			customerService.update(customer);
			response.setData(customer);
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/connect-social", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> connectSocial(HttpServletRequest request, @PathVariable int id,
			@Valid @RequestBody ConnectSocialRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customer = customerService.findOne(getCurrentUser(request).getId());
			customer.setFullName(wrapper.getFullName());
			customer.setEmail(wrapper.getEmail());
			if (!wrapper.getAvatar().isEmpty()) {
				customer.setAvatar(wrapper.getAvatar());
			}
			switch (wrapper.getSocialTypeEnum()) {
			case FACEBOOK:
				customer.setFacebookUID(wrapper.getSocialUID());
				break;
			case GOOGLE:
				customer.setGoogleUID(wrapper.getSocialUID());
				break;
			default:
				break;
			}
			customerService.update(customer);
			response.setData(customer);
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/change-password", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> registerCustomer(HttpServletRequest request,
			@Valid @RequestBody CustomerChangePasswordRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto entity = customerService.findOne(getCurrentUser(request).getId());
			if (entity == null) {
				response.setStatus(ResponseStatusEnum.FAIL);
				response.setMessageError("customer.notfound");
			} else {
				if (entity.getVerifyCode().equals(wrapper.getOldPassword())) {
					if (wrapper.getNewPassword().length() == 6) {
						entity.setVerifyCode(wrapper.getNewPassword());
						entity.setPassword(Utils.encodeShaPassword(wrapper.getNewPassword()));
						entity.setAuthType(AuthTypeEnum.NEEDII.getValue());
						customerService.update(entity);
					} else {
						response.setStatus(ResponseStatusEnum.FAIL);
						response.setMessageError("customer.newpassword.wrong");
					}
				} else {
					response.setStatus(ResponseStatusEnum.FAIL);
					response.setMessageError("customer.oldpassword.wrong");
				}
			}
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.TOKEN_EXPIRED);
			response.setMessage(ResponseStatusEnum.TOKEN_EXPIRED);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> profile(HttpServletRequest request, @PathVariable int id,
			@RequestBody UpdateProfileRequest wrapper) {
		BaseDto response = new BaseDto();
		try {

		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/addresses", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> addresses(HttpServletRequest request, @PathVariable int id,
			@RequestParam(name = "type") int type, @RequestParam(name = "is_default") int isDefault) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customer = customerService.findOne(getCurrentUser(request).getId());
			response.setData(
					(new CustomerAddressDto()).mapToListResponse(customer.getCustomerAddresses(type, isDefault)));
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/edit-address", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> editAddress(HttpServletRequest request, @PathVariable int id,
			@Valid @RequestBody CustomerAddressRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customer = customerService.findOne(getCurrentUser(request).getId());
			CustomerAddressDto address = customer.getCustomerAddresses().stream()
					.filter(x -> x.getId() == wrapper.getId()).findFirst().orElse(null);
			CityDto city = cityService.findOne(wrapper.getCityId());
			DistrictDto district = districtService.findOne(wrapper.getDistrictId());
			WardDto ward = wardService.findOne(wrapper.getWardId());
			if (address == null || city == null || district == null || ward == null) {
				response.setStatus(ResponseStatusEnum.FAIL);
				response.setMessageError("CustomerAddress.NotFound");
			} else {

				address.setCity(city);
				address.setDistrict(district);
				address.setWard(ward);
				address.setStreetName(wrapper.getStreetName());
				address.setStreetNumber(wrapper.getStreetNumber());
				address.setPhone(wrapper.getPhone());
				address.setReceiverName(wrapper.getReceiverName());

				customerAddressService.update(address);
			}

		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/create-address", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> createAddress(HttpServletRequest request, @PathVariable int id,
			@Valid @RequestBody CustomerAddressRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customer = customerService.findOne(getCurrentUser(request).getId());
			CityDto city = cityService.findOne(wrapper.getCityId());
			DistrictDto district = districtService.findOne(wrapper.getDistrictId());
			WardDto ward = wardService.findOne(wrapper.getWardId());
			if (city == null || district == null || ward == null) {
				response.setStatus(ResponseStatusEnum.FAIL);
				response.setMessageError("CustomerAddress.NotFound");
			} else {
				CustomerAddressDto address = new CustomerAddressDto();
				address.setStreetName(wrapper.getStreetName());
				address.setStreetNumber(wrapper.getStreetNumber());
				address.setPhone(wrapper.getPhone());
				address.setReceiverName(wrapper.getReceiverName());
				address.setIsDefault(false);
				address.setAddressType(wrapper.getAddressType());
				address.setStatus(true);
				address.setFullTextAddress(ward.getName() + ", " + district.getName() + ", " + city.getName());
				address.setCustomer(customer);
				address.setCity(city);
				address.setDistrict(district);
				address.setWard(ward);
				customerAddressService.create(address);
			}
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/set-address-default", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> setAddressDefault(HttpServletRequest request, @PathVariable int id,
			@RequestBody SetDefaultAddressRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customerDto = customerService.findOne(getCurrentUser(request).getId());
			List<CustomerAddressDto> addresses = customerDto.getCustomerAddresses();
			CustomerAddressDto selectAddress = addresses.stream()
					.filter(x -> x.getId() == wrapper.getCustomerAddressId()).findFirst().orElse(null);
			if (selectAddress == null || selectAddress.getAddressType() != 0) {
				response.setStatus(ResponseStatusEnum.FAIL);
				response.setMessageError("customer.address.notfound");
			} else {
				for (CustomerAddressDto address : addresses) {
					address.setIsDefault(false);
					customerAddressService.update(address);
				}
				selectAddress.setIsDefault(true);
				customerAddressService.update(selectAddress);
				response.setData((new CustomerAddressDto()).mapToListResponse(customerDto.getCustomerAddresses(0, 0)));
			}
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/delete-address", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> deleteAddress(HttpServletRequest request, @PathVariable int id,
			@RequestBody DeleteAddressRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto entity = customerService.findOne(getCurrentUser(request).getId());
			List<CustomerAddressDto> addresses = entity.getCustomerAddresses();
			CustomerAddressDto selectAddress = addresses.stream()
					.filter(x -> x.getId() == wrapper.getCustomerAddressId()).findFirst().orElse(null);
			if (selectAddress == null) {
				response.setStatus(ResponseStatusEnum.FAIL);
				response.setMessageError("customer.address.notfound");
			} else {
				selectAddress.setIsDeleted(true);
				customerAddressService.update(selectAddress);
				response.setData((new CustomerAddressDto()).mapToListResponse(entity.getCustomerAddresses(0, 0)));
			}
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> cart(HttpServletRequest request,
			@RequestParam(name = "customer_id", required = false) int customerId,
			@RequestParam(name = "device_uid", required = false) String deviceUID) {
		BaseDto response = new BaseDto();
		try {
			CartDto cartDto;
			if (customerId > 0) {
				cartDto = cartService.findByCustomerId(getCurrentUser(request).getId());
			} else {
				cartDto = cartService.findByDeviceUID(deviceUID);
			}
			if (cartDto != null) {
				response.setData(cartDto);
			}
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/cart-quantity", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> cartQuantity(HttpServletRequest request,
			@RequestParam(name = "customer_id", required = false) int customerId,
			@RequestParam(name = "device_uid", required = false) String deviceUID) {
		BaseDto response = new BaseDto();
		try {
			CartDto cartDto;
			if (customerId > 0) {
				cartDto = cartService.findByCustomerId(getCurrentUser(request).getId());
			} else {
				cartDto = cartService.findByDeviceUID(deviceUID);
			}
			if (cartDto != null) {
				response.setData(cartDto.getCartItems().stream().mapToInt(x -> x.getQuantity()).sum());
			} else {
				response.setData(0);
			}
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/add-to-cart", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> addToCart(HttpServletRequest request, @Valid @RequestBody AddToCartRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			ProductDto product = productService.findOne(wrapper.getProductId());
			if (product == null || !product.validToSell()) {
				response.setStatus(ResponseStatusEnum.FAIL);
				response.setMessageError("product.invalid");
			} else {

				SkuDto skuDto = product.getSkuses().stream()
						.filter(x -> x.getId() == wrapper.getSkuId() && x.validToSell()).findFirst().orElse(null);
				if (skuDto == null) {
					response.setStatus(ResponseStatusEnum.FAIL);
					response.setMessageError("product.sku.invalid");
				} else {
					CartDto cartDto = null;
					if (wrapper.getCustomerId() > 0) {
						cartDto = cartService.findByCustomerId(getCurrentUser(request).getId());
					} else {
						cartDto = cartService.findByDeviceUID(wrapper.getDeviceUID());
					}
					if (cartDto == null) {
						cartDto = new CartDto();
						cartDto.setDeviceUID(wrapper.getDeviceUID());
						if (wrapper.getCustomerId() > 0) {
							CustomerDto customer = customerService.findOne(getCurrentUser(request).getId());
							cartDto.setCustomer(customer);
						}
						cartService.create(cartDto);
						cartDto = cartService.findByDeviceUID(wrapper.getDeviceUID());
					}
					CartItemDto existCartItem = cartItemService.findByCartId(cartDto.getId()).stream()
							.filter(x -> x.getSku().getId() == skuDto.getId()).findFirst().orElse(null);
					if (existCartItem == null) {
						existCartItem = new CartItemDto();
						existCartItem.setCart(cartDto);
						existCartItem.setProductId(product.getId());

						existCartItem.setSku(skuDto);
						existCartItem.setUnitPrice(skuDto.getPrice());
					}
					int newQuantity = existCartItem.getQuantity() + wrapper.getQuantity();
					if (newQuantity > skuDto.getQuantity() || newQuantity <= 0) {
						response.setStatus(ResponseStatusEnum.FAIL);
						response.setMessageError("Product.Sku.QuantityInvalid");
					} else {
						float totalPrice = skuDto.getPrice() * newQuantity;
						existCartItem.setSkuName(skuDto.getName());
						existCartItem.setSkuImage(skuDto.getImageSlideAsListString().get(0));
						existCartItem.setQuantity(newQuantity);
						existCartItem.setTotalPrice(totalPrice);
						existCartItem.calculateAndSetCashback(skuDto.getCashbackPercent());
						cartItemService.createOrUpdate(existCartItem);
						updateCart(cartDto.getId());
						response.setData(cartDto);
					}

				}
			}
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete-cart-item", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> deleteCartItem(HttpServletRequest request,
			@RequestBody DeleteCartItemRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			CartDto cart;
			if (wrapper.getCustomerId() > 0) {
				cart = cartService.findByCustomerId(getCurrentUser(request).getId());
			} else {
				cart = cartService.findByDeviceUID(wrapper.getDeviceUID());
			}
			if (cart == null) {
				response.setStatus(ResponseStatusEnum.FAIL);
				response.setMessageError("cart.notfound");
			} else {
				CartItemDto existCartItem = cart.getCartItems().stream()
						.filter(x -> x.getId() == wrapper.getCartItemId()).findFirst().orElse(null);
				;
				if (existCartItem == null) {
					response.setStatus(ResponseStatusEnum.FAIL);
					response.setMessageError("cart.cartitem.notfound");
				} else {
					cartItemService.delete(existCartItem);
					updateCart(cart.getId());
					cart = cartService.findOne(cart.getId());
					response.setData(cart);
				}
			}
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/orders", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> orders(HttpServletRequest request, @RequestParam(name = "offset") int offset) {
		BaseDto response = new BaseDto();
		try {
			offset = offset < 1 ? 0 : offset - 1;
			int limit = Constants.PAGE_NUMBER;
			long customerId = getCurrentUser(request).getId();
			List<OrderDto> orders = orderService.findByCustomerId(customerId, limit, offset);
			BaseListDataDto data = new BaseListDataDto();
			data.setData((new OrderDto()).mapToListResponse(orders));
			data.setTotalRecord(orderService.count(customerId));
			data.setLimit(limit);
			response.setData(data);
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/needii-cash-histories", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> neediiCashHistories(HttpServletRequest request,
			@RequestParam(name = "offset") int offset) {
		BaseDto response = new BaseDto();
		try {
			offset = offset < 1 ? 0 : offset - 1;
			int limit = Constants.PAGE_NUMBER;
			long customerId = getCurrentUser(request).getId();
			List<CustomerBalanceHistoryDto> histories = customerBalanceHistoryService.findByCustomerId(customerId,
					limit, offset);
			BaseListDataDto data = new BaseListDataDto();
			data.setData((new CustomerBalanceHistoryDto()).mapToListResponse(histories));
			data.setTotalRecord(customerBalanceHistoryService.count(customerId));
			data.setLimit(limit);
			response.setData(data);
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/needii-cash", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> getNeediiCash(HttpServletRequest request) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customer = customerService.findOne(getCurrentUser(request).getId());
			response.setData(customer.getNeediiCash());
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/transfer-needii-cash", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> saveFavoriteCategory(HttpServletRequest request,
			@RequestBody TransferNeediiCashRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customer = customerService.findOne(getCurrentUser(request).getId());
			CustomerDto targetCustomer = customerService.findByPhone(wrapper.getPhone());
			if (targetCustomer == null || !targetCustomer.isAuthenticated()) {
				response.setStatus(ResponseStatusEnum.FAIL);
				response.setMessageError("Customer.Phone.NotFound");
			} else {
				if (customer.getNeediiCash() < wrapper.getAmount()) {
					response.setStatus(ResponseStatusEnum.FAIL);
					response.setMessageError("Customer.NeediiCash.AmountInvalid");
				} else {
					float newCashAmount = customer.getNeediiCash() - wrapper.getAmount();
					customer.setNeediiCash(newCashAmount);
					targetCustomer.setNeediiCash(targetCustomer.getNeediiCash() + wrapper.getAmount());
					customerService.update(customer);
					customerService.update(targetCustomer);

					CustomerBalanceHistoryDto history = new CustomerBalanceHistoryDto();
					history.setAmount(wrapper.getAmount() * -1);
					history.setCustomer(customer);
					history.setNote(String.format("Chuyển %d Needii Cash tới số điện thoại %s",
							(long) wrapper.getAmount(), wrapper.getPhone()));
					customerBalanceHistoryService.create(history);

					CustomerBalanceHistoryDto targetHistory = new CustomerBalanceHistoryDto();
					targetHistory.setAmount(wrapper.getAmount());
					targetHistory.setCustomer(targetCustomer);
					targetHistory.setNote(String.format("Nhận %d Needii Cash từ số điện thoại %s",
							(long) wrapper.getAmount(), customer.getPhone()));
					customerBalanceHistoryService.create(targetHistory);

					response.setData(customer.getNeediiCash());
				}
			}

		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/save-favorite-category", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> saveFavoriteCategory(HttpServletRequest request,
			@RequestBody SaveFavoriteCategoryRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customer = customerService.findOne(getCurrentUser(request).getId());
			List<CategoryDto> categories = categoryService.findByIds((Long[]) wrapper.getCategoryIds().toArray());
			customer.setCategories(categories);
			customerService.update(customer);
			response.setData(customer.getCategories().stream().map(CategoryDto::getId).collect(Collectors.toList()));
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/add-favorite-product", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> addFavoriteProduct(HttpServletRequest request,
			@RequestBody FavoriteProductRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customer = customerService.findOne(getCurrentUser(request).getId());
			List<ProductDto> productDtos = productService.findByIds((Long[]) wrapper.getProductIds().toArray());
			customer.addFavoriteProducts(productDtos);
			customerService.update(customer);
			response.setData(
					customer.getFavoriteProducts().stream().map(ProductDto::getId).collect(Collectors.toList()));
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/remove-favorite-product", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> removeFavoriteProduct(HttpServletRequest request,
			@RequestBody FavoriteProductRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customer = customerService.findOne(getCurrentUser(request).getId());
			customer.removeFavoriteProducts(wrapper.getProductIds());
			customerService.update(customer);
			response.setData(
					customer.getFavoriteProducts().stream().map(ProductDto::getId).collect(Collectors.toList()));
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/favorite-products", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> getFavoriteProducts(HttpServletRequest request) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customer = customerService.findOne(getCurrentUser(request).getId());
			response.setData(customer.getFavoriteProducts());
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/add-subscribe-supplier", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> addSubscribeSupplier(HttpServletRequest request,
			@RequestBody SubscribleSupplierRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customer = customerService.findOne(getCurrentUser(request).getId());
			SupplierDto supplier = supplierService.findOne(wrapper.getSupplierId());
			supplier.setSubscribeCount(supplier.getSubscribeCount() + 1);
			customer.addSubscribeSupplier(supplier);
			customerService.update(customer);
			supplierService.update(supplier);
			response.setData(supplier.getSubscribeCount());
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/remove-subscribe-supplier", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> removeSubscribeSupplier(HttpServletRequest request,
			@RequestBody SubscribleSupplierRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customer = customerService.findOne(getCurrentUser(request).getId());
			SupplierDto supplier = supplierService.findOne(wrapper.getSupplierId());
			supplier.setSubscribeCount(supplier.getSubscribeCount() - 1);
			customer.removeSubscribeSupplier(supplier);
			customerService.update(customer);
			supplierService.update(supplier);
			response.setData(supplier.getSubscribeCount());
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/subscribe-suppliers", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> getSubscribleSuppliers(HttpServletRequest request) {
		BaseDto response = new BaseDto();
		try {
			Language language = this.getLanguage(request);
			CustomerDto customer = customerService.findOne(getCurrentUser(request).getId());
			// bo sung response sau
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/like-supplier", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> likeSupplier(HttpServletRequest request, @RequestBody LikeSupplierRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customer = customerService.findOne(getCurrentUser(request).getId());
			SupplierDto supplier = supplierService.findOne(wrapper.getSupplierId());
			customer.likeSupplier(supplier);
			customerService.update(customer);
			supplier.setLikeCount(supplier.getLikeCount() + 1);
			supplierService.update(supplier);
			response.setData(supplier.getLikeCount());
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/unlike-supplier", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> unlikeSupplier(HttpServletRequest request,
			@RequestBody LikeSupplierRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customer = customerService.findOne(getCurrentUser(request).getId());
			SupplierDto supplier = supplierService.findOne(wrapper.getSupplierId());
			customer.unlikeSupplier(supplier);
			supplier.setLikeCount(supplier.getLikeCount() - 1);
			customerService.update(customer);
			supplierService.update(supplier);
			response.setData(supplier.getLikeCount());
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/like-product", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> likeProduct(HttpServletRequest request, @RequestBody LikeProductRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customer = customerService.findOne(getCurrentUser(request).getId());
			ProductDto product = new ProductDto();
			productService.findOne(wrapper.getProductId());
			customer.likeProduct(product);
			customerService.update(customer);
			product.setLikeCount(product.getLikeCount() + 1);
			productService.update(product);
			response.setData(product.getLikeCount());
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/unlike-product", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> unlikeProduct(HttpServletRequest request, @RequestBody LikeProductRequest wrapper) {
		BaseDto response = new BaseDto();
		try {

			CustomerDto customer = customerService.findOne(getCurrentUser(request).getId());
			ModelMapper mapper = new ModelMapper();

			ProductDto product = new ProductDto();
			mapper.map(productService.findOne(wrapper.getProductId()), product);
			customer.unlikeProduct(product);
			product.setLikeCount(product.getLikeCount() - 1);
			customerService.update(customer);
			productService.update(product);
			response.setData(product.getLikeCount());
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/set-share-count-supplier", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> setShareCountSupplier(HttpServletRequest request,
			@RequestBody ShareSupplierRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			SupplierDto supplier = supplierService.findOne(wrapper.getSupplierId());
			supplier.setShareCount(supplier.getShareCount() + 1);
			supplierService.update(supplier);
			response.setData(supplier.getShareCount());
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/set-share-count-product", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> setShareCountProduct(HttpServletRequest request,
			@RequestBody ShareProductRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			ModelMapper mapper = new ModelMapper();
			ProductDto product = new ProductDto();
			mapper.map(productService.findOne(wrapper.getProductId()), product);
			product.setShareCount(product.getShareCount() + 1);
			productService.update(product);
			response.setData(product.getShareCount());
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/cancel-order", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> cancelOrder(HttpServletRequest request, @PathVariable int id,
			@RequestBody CancelOrderRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			OrderDto order = orderService.findByOrderCode(wrapper.getId());
			if (order == null || order.getCustomer().getId() != getCurrentUser(request).getId()
					|| order.getOrderStatusEnum() != OrderStatusEnum.PENDING) {
				response.setStatus(ResponseStatusEnum.NOT_FOUND);
				response.setMessage(ResponseStatusEnum.NOT_FOUND);
			} else {
				String historyLog = String.format("Khách hàng huỷ đơn hàng mã #%s.", order.getOrderCode());
				order.setOrderStatus(OrderStatusEnum.CANCEL.getValue());
				order.setHistoryLog(historyLog);
				;
				orderService.update(order);
				List<OrderDetailDto> orderDetails = orderDetailService.findByOrderId(order.getId()).stream()
						.filter(x -> x.getOrderDetailStatus() == OrderDetailStatusEnum.PENDING.getValue()).collect(Collectors.toList());

				for (OrderDetailDto orderDetail : orderDetails) {
					this.returnSkuQuantity(orderDetail.getId());
				}
			}
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/cancel-order-detail", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> cancelOrderDetail(HttpServletRequest request, @PathVariable int id,
			@RequestBody CancelOrderDetailRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			OrderDetailDto orderDetail = orderDetailService.findOne(wrapper.getId());
			OrderDto order = orderDetail.getOrder();
			if (order == null || order.getCustomer().getId() != getCurrentUser(request).getId()
					|| !(orderDetail.getOrderDetailStatus() == OrderDetailStatusEnum.PENDING.getValue())) {
				response.setStatus(ResponseStatusEnum.NOT_FOUND);
				response.setMessage(ResponseStatusEnum.NOT_FOUND);
			} else {
				String historyLog = String.format("Khách hàng huỷ sản phẩm [%s].", orderDetail.getSku().getName());
				orderDetail.setOrderDetailStatus(OrderDetailStatusEnum.CANCEL.getValue());
				orderDetail.setHistoryLog(historyLog);
				orderDetailService.update(orderDetail);
				this.updateOrderStatus(order.getId(), historyLog, true);
				this.returnSkuQuantity(orderDetail.getId());
			}
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/apply-reference", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> cancelOrderDetail(HttpServletRequest request, @PathVariable int id,
			@RequestBody ApplyReferenceRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customer = customerService.findOne(getCurrentUser(request).getId());
			if (customer.getReferenceBy() > 0) {
				response.setStatus(ResponseStatusEnum.FAIL);
				response.setMessageError("Customer.Reference.Used");
			} else {
				CustomerDto referenceCustomer = customerService.findByReferenceCode(wrapper.getReferenceCode());
				if (referenceCustomer == null) {
					response.setStatus(ResponseStatusEnum.NOT_FOUND);
					response.setMessage(ResponseStatusEnum.NOT_FOUND);
				} else {
					customer.setReferenceBy(referenceCustomer.getId());
					customer.setReferenceAt(new Date());
					customerService.update(customer);
				}
			}
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	private void updateCart(long cartId) {
		CartDto cart = cartService.findOne(cartId);
		List<CartItemDto> cartItems = cart.getCartItems();
		float price = (float) cartItems.stream().mapToDouble(x -> x.getTotalPrice()).sum();
		float shippingFee = (float) cartItems.stream().mapToDouble(x -> x.getShippingFee()).sum();
		float totalCashback = (float) cartItems.stream().mapToDouble(x -> x.getCashback()).sum();
		cart.setPrice(price);
		cart.setShippingFee(shippingFee);
		cart.setTotalPrice(price + shippingFee);
		cart.setTotalCashback(totalCashback);
		cartService.update(cart);
	}
}
