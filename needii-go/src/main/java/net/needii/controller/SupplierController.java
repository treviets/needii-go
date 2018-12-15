/**
 * 
 */
package net.needii.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.needii.dto.BaseDto;
import net.needii.dto.BaseListDataDto;
import net.needii.dto.CategoryDto;
import net.needii.dto.CustomerDto;
import net.needii.dto.OrderDetailDto;
import net.needii.dto.OrderDto;
import net.needii.dto.OrderSupplierDto;
import net.needii.dto.ResponseStatusEnum;
import net.needii.dto.SupplierBankInfoDto;
import net.needii.dto.SupplierCompanyProfileDto;
import net.needii.dto.SupplierDto;
import net.needii.dto.SupplierReviewDto;
import net.needii.dto.SupplierRoleDto;
import net.needii.dto.request.OrderDetailStatusRequest;
import net.needii.dto.request.PostReviewSupplierRequest;
import net.needii.dto.request.ReviewSupplierRequest;
import net.needii.dto.request.SupplierCompanyRegisterRequest;
import net.needii.dto.request.SupplierPersonalRegisterRequest;
import net.needii.jpa.entity.OrderDetailStatusEnum;
import net.needii.jpa.entity.security.NeediiUser;
import net.needii.service.CategoryService;
import net.needii.service.CustomerService;
import net.needii.service.OrderDetailService;
import net.needii.service.OrderService;
import net.needii.service.SupplierReviewService;
import net.needii.service.SupplierRoleService;
import net.needii.service.SupplierService;
import util.Constants;
import util.Option;
import util.Pagination;
import util.Utils;

/**
 * @author kelvin
 *
 */
@RestController
@Api(value = "Supplier APIs Endpoint")
@RequestMapping("api/suppliers")
public class SupplierController extends BaseController {

	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private SupplierRoleService supplierRoleService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private SupplierReviewService supplierReviewService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation("Fetch all suppliers base on categories")
	public ResponseEntity<BaseDto> get(HttpServletRequest request,
			@RequestParam(name = "category_ids", required = false) String categoryIds,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "limit", required = false, defaultValue = Constants.PAGE_NUMBER_REQUEST) int limit) {
		BaseDto response = new BaseDto();
		try {
			Option option = new Option();
			Map<String, Object> queryString = new HashMap<>();
			queryString.put("category_ids", categoryIds);
			option.setQueryString(queryString);

			Pagination pagination = new Pagination(page, limit);
			option.setPagination(pagination);
			List<SupplierDto> dtos = supplierService.findAll(option);

			NeediiUser neediiUser = this.getCurrentUser(request);
			
			BaseListDataDto data = new BaseListDataDto();
			for(SupplierDto dto: dtos) {
				if(neediiUser.getLikeSupplierIds().contains(dto.getId())){
					dto.setIsLike(true);
				}
				if(neediiUser.getSubscribeSupplierIds().contains(dto.getId())) {
					dto.setIsSubscribe(true);
				}
			}
			data.setData(dtos);
			data.setTotalRecord(supplierService.count(option));
			data.setLimit(pagination.getLimit());
			response.setData(data);
		} catch (Exception ex) {
			this.setLog(ex);
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation("get suppliers detail by id")
	public ResponseEntity<BaseDto> get(HttpServletRequest request, @PathVariable int id) {
		BaseDto response = new BaseDto();
		try {
			SupplierDto supplierDto = supplierService.findOne(id);
			response.setData(supplierDto);
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
			ex.printStackTrace();
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/{phoneNumber}/get/by/phone/", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation("get suppliers detail by phone")
	public ResponseEntity<BaseDto> getByPhone(HttpServletRequest request, @PathVariable String phoneNumber) {
		BaseDto response = new BaseDto();
		try {
			SupplierDto supplierResponse = supplierService.findByPhone(phoneNumber);
			response.setData(supplierResponse);
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
			ex.printStackTrace();
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/personal/register/", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	@ApiOperation("Personal supplier register")	
	public ResponseEntity<BaseDto> personalRegister(HttpServletRequest request,
			@RequestBody SupplierPersonalRegisterRequest personalRegister) {
		BaseDto response = new BaseDto();
		SupplierDto personalSupplierRegister = new SupplierDto();
		try {
			if (personalRegister != null) {
				personalSupplierRegister.setEmail(personalRegister.getEmail());
				personalSupplierRegister.setFullName(personalRegister.getFullName());
				personalSupplierRegister.setPhone(personalRegister.getPhone());
				personalSupplierRegister.setFullTextAddress(personalRegister.getFullTextAddress());
				personalSupplierRegister.setLat(personalRegister.getLatitude());
				personalSupplierRegister.setLng(personalRegister.getLongitute());
				personalSupplierRegister.setIdentifyDate(personalRegister.getIdentifyDate());
				personalSupplierRegister.setIdentifyNo(personalRegister.getIdentifyNo());
				personalSupplierRegister.setIdentifyPlace(personalRegister.getIdentifyPlace());
				personalSupplierRegister.setIdentifyImage(personalRegister.getIdentifyImage().replace(Constants.RESOURCE_DOMAIN, ""));
				SupplierBankInfoDto bankInfor = new SupplierBankInfoDto();
				bankInfor.setBankAccount(personalRegister.getBankAccount());
				bankInfor.setBankAccountName(personalRegister.getBankAccountName());
				bankInfor.setBankBranch(personalRegister.getBankBranch());
				bankInfor.setBankName(personalRegister.getBankName());
				bankInfor.setSupplier(personalSupplierRegister);
				personalSupplierRegister.setBankInfo(bankInfor);

				SupplierRoleDto supplierRole = supplierRoleService.findOne(Constants.SUPPLIER_DEFAULT_ROLE_ID);
				personalSupplierRegister.setSupplierRole(supplierRole);
				
				response.setData(supplierService.create(personalSupplierRegister));
			}

		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}

		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@ApiOperation("Company supplier register")	
	@RequestMapping(value = "/company/register/", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<BaseDto> companyRegister(HttpServletRequest request,
			@RequestBody SupplierCompanyRegisterRequest companyRegister) {
		BaseDto response = new BaseDto();
		SupplierDto companySupplierRegister = new SupplierDto();
		try {
			if (companyRegister != null) {
				companySupplierRegister.setPhone(companyRegister.getPhone());

				SupplierCompanyProfileDto companyProfile = new SupplierCompanyProfileDto();

				companyProfile.setCompanyLegalRepresentative(companyRegister.getCompanyLegalPresetativeName());
				companySupplierRegister.setFullName(companyRegister.getCompanyLegalPresetativeName());

				companyProfile.setTitle(companyRegister.getTitle());
				companyProfile.setAddress(companyRegister.getFullTextAddress());
				companySupplierRegister.setFullTextAddress(companyRegister.getFullTextAddress());
				companySupplierRegister.setLat(companyRegister.getLatitude());
				companySupplierRegister.setLng(companyRegister.getLongitute());
				companySupplierRegister.setEmail(companyRegister.getEmail());
				companyProfile.setCompanyEmail(companyRegister.getEmail());

				companyProfile.setCompanyName(companyRegister.getCompanyName());
				companyProfile.setLincenseImage(companyRegister.getCompanyImage().replace(Constants.RESOURCE_DOMAIN, ""));
				companyProfile.setLincenseNumber(companyRegister.getLicenceNo());
				companyProfile.setRegisterPlace(companyRegister.getLicencePlace());
				companyProfile.setRegisterDate(companyRegister.getLicenceDate());
				companyProfile.setSupplier(companySupplierRegister);
				companySupplierRegister.setCompanyProfile(companyProfile);

				SupplierBankInfoDto bankInfor = new SupplierBankInfoDto();
				bankInfor.setBankAccount(companyRegister.getBankAccount());
				bankInfor.setBankAccountName(companyRegister.getBankAccountName());
				bankInfor.setBankName(companyRegister.getBankName());
				bankInfor.setBankBranch(companyRegister.getBankBranch());
				bankInfor.setSupplier(companySupplierRegister);
				companySupplierRegister.setBankInfo(bankInfor);
				SupplierRoleDto supplierRole = supplierRoleService.findOne(Constants.SUPPLIER_DEFAULT_ROLE_ID);
				companySupplierRegister.setSupplierRole(supplierRole);

				response.setData(supplierService.create(companySupplierRegister));
			}
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{supplierId}/{categoryId}/category/chosen/", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	@ApiOperation("Assign category to supplier")	
	public ResponseEntity<BaseDto> chooseCategory(HttpServletRequest request,
			@PathVariable("supplierId") int supplierId, @PathVariable("categoryId") int categoryId) {
		BaseDto response = new BaseDto();
		try {
			SupplierDto supplierDto = supplierService.findOne(supplierId);
			CategoryDto category = categoryService.findOne(categoryId);
			if (supplierDto != null) {
				if (category != null) {
					supplierDto.setCategory(category);
					supplierService.create(supplierDto);
				}
			}
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/set/avatar/", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	@ApiOperation("Set supplier avatar")	
	public ResponseEntity<BaseDto> setAvatar(@RequestBody SupplierPersonalRegisterRequest supplierRequest) {
		BaseDto response = new BaseDto();
		try {
			SupplierDto supplierInDB = supplierService.findOne(supplierRequest.getId());
			if (supplierInDB != null) {
				supplierInDB.setLogoUrl(supplierRequest.getLogoURL());
				supplierService.create(supplierInDB);
			}
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{supplierId}/wallet/", method = RequestMethod.GET, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> getWallet(HttpServletRequest request,
			@PathVariable("supplierId") int supplierId) {
		BaseDto response = new BaseDto();
		try {
			response.setData(new Integer(1936));
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{supplierId}/orders/list/", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation("Get supplier's orders")
	public ResponseEntity<BaseDto> getOrdersBySupplierId(HttpServletRequest request,
			@PathVariable("supplierId") int supplierId) {
		BaseDto response = new BaseDto();
		try {
			List<OrderDetailDto> orderDetails = orderDetailService.findBySupplierId(supplierId);
			response.setData(orderDetails);

		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{orderId}/order/detail/", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation("get supplier's order's detail")	
	public ResponseEntity<BaseDto> getOrdersByDetail(HttpServletRequest request,
			@PathVariable("orderId") Integer orderId) {
		BaseDto response = new BaseDto();
		try {
			OrderDetailDto orderDetails = orderDetailService.findOne(orderId);
			response.setData(orderDetails);

		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/upload-image/", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation("Supplier upload image")	
	public ResponseEntity<BaseDto> retainAsset(@RequestParam("file") MultipartFile file)
			throws IOException, NoSuchAlgorithmException {
		BaseDto response = new BaseDto();
		response.setData(processRetainAsset(file));
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	private String processRetainAsset(MultipartFile file) throws IOException, NoSuchAlgorithmException {
		String rootPath = "/opt/tomcat/webapps/needii";
		String fileName = "/static/upload/suppliers/"+ new SimpleDateFormat("yyyyMMddmmmssss").format(new Date()) + file.getOriginalFilename();
		File uploadFile = new File(rootPath+fileName);
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
		FileCopyUtils.copy(file.getInputStream(), stream);
		stream.close();

		return Constants.RESOURCE_DOMAIN+fileName;
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/post-review", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	@ApiOperation("supplier post review")	
	public ResponseEntity<BaseDto> likeProduct(HttpServletRequest request,
			@Valid @RequestBody PostReviewSupplierRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			CustomerDto customer = customerService.findOne(getCurrentUser(request).getId());
			for (ReviewSupplierRequest review : wrapper.getReviews()) {
				SupplierDto supplier = supplierService.findOne(review.getSupplierId());
				if (supplier != null && supplier.getStatus() == 1) {
					SupplierReviewDto entity = new SupplierReviewDto();
					entity.setCustomerName(review.getCustomerName());
					entity.setContent(review.getContent());
					entity.setStarRate(review.getStarRate());
					entity.setSupplier(supplier);
					entity.setCustomer(customer);
					supplierReviewService.create(entity);
				}
			}

		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/reviews", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation("supplier get review")	
	public ResponseEntity<BaseDto> getListLocations(HttpServletRequest request, @PathVariable int id,
			@RequestParam(name = "offset") int offset) {
		BaseDto response = new BaseDto();
		try {
			SupplierDto supplier = supplierService.findOne(id);
			if (supplier == null || !(supplier.getStatus() == 1)) {
				response.setStatus(ResponseStatusEnum.NOT_FOUND);
				response.setMessage(ResponseStatusEnum.NOT_FOUND);
			} else {
				offset = offset < 1 ? 0 : offset - 1;
				int limit = Constants.PAGE_NUMBER;
				List<SupplierReviewDto> reviews = supplierReviewService.findBySupplierId(id, limit, offset);
				BaseListDataDto data = new BaseListDataDto();
				data.setData((new SupplierReviewDto()).mapToListResponse(reviews));
				data.setTotalRecord(supplierReviewService.countBySupplierId(id));
				data.setLimit(limit);
				response.setData(data);
			}

		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/locations", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation("get suppliers by location")	
	public ResponseEntity<BaseDto> getListLocations(HttpServletRequest request,
			@RequestParam(name = "lat") double lat, @RequestParam(name = "lng") double lng) {
		BaseDto response = new BaseDto();
		try {

			List<SupplierDto> entities = supplierService.findByLocation(lat, lng, Constants.GOOGLE_MAP_DISTANCE_KILOMETER);
			response.setData(entities);
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('SUPPLIER')")
	@RequestMapping(value = "/orders", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> orders(
			HttpServletRequest request,
			@RequestParam(name="offset") int offset) {
		BaseDto response = new BaseDto();
		try {
			offset = offset < 1 ? 0 : offset - 1;
			int limit = Constants.PAGE_NUMBER;
			SupplierDto supplier = supplierService.findByPhone(getCurrentUser(request).getPhone());
			//List<OrderDetail> orderDetails = orderDetailService.findBySupplierId(supplier.getId(), limit, offset);
			List<OrderDetailDto> orders = orderDetailService.findBySupplierId(supplier.getId(), limit, offset);
			BaseListDataDto data = new BaseListDataDto();
			data.setData((new OrderSupplierDto()).mapToListResponse(orders, supplier.getId()));
			data.setTotalRecord(orderDetailService.count(supplier.getId()));
			data.setLimit(limit);
			response.setData(data);
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('SUPPLIER')")
	@RequestMapping(value = "/{id}/order-detail", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> orderDetail(
			HttpServletRequest request,
			@PathVariable int id,
			@RequestParam(name="order_id") String orderId) {
		BaseDto response = new BaseDto();
		try {
			long orderIdDecrypt = Utils.decryptOrderId(orderId);
			SupplierDto supplier = supplierService.findByPhone(getCurrentUser(request).getPhone());
			OrderDto order = orderService.findOne(orderIdDecrypt);
			if(order == null) {
				response.setStatus(ResponseStatusEnum.NOT_FOUND);
				response.setMessage(ResponseStatusEnum.NOT_FOUND);
			} else {
				response.setData(new OrderSupplierDto(order, supplier.getId()));
			}
			
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('SUPPLIER')")
	@RequestMapping(value = "/{id}/change-order-detail-status", method = RequestMethod.POST, consumes = {MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> changeOrderStatus(
			HttpServletRequest request,
			@PathVariable int id,
			@Valid @RequestBody OrderDetailStatusRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			boolean isValid = false;
			
			SupplierDto supplier = supplierService.findByPhone(getCurrentUser(request).getPhone());
			OrderDetailDto orderDetail = orderDetailService.findOne(wrapper.getId());
			OrderDto order = orderDetail.getOrder();
			
			if(order.isAllowSupplierProccess() && orderDetail.getSupplier().getId() != supplier.getId()) {
				response.setStatus(ResponseStatusEnum.NOT_FOUND);
				response.setMessage(ResponseStatusEnum.NOT_FOUND);
			} else {
				switch(wrapper.getOrderDetailStatusEnum()) {
				case PROCESSING:
				case CANCEL:
					if(orderDetail.getOrderDetailStatus() == OrderDetailStatusEnum.PENDING.getValue()) {
						isValid = true;
					} else {
						isValid = false;
					}
					break;
				case PACKAGED:
					if(orderDetail.getOrderDetailStatus() == OrderDetailStatusEnum.PROCESSING.getValue()) {
						isValid = true;
					} else {
						isValid = false;
					}
					break;
				case DELIVERING:
					if(orderDetail.getOrderDetailStatus() == OrderDetailStatusEnum.PACKAGED.getValue()) {
						isValid = true;
					} else {
						isValid = false;
					}
					break;
				case COMPLETED:
					if(orderDetail.getOrderDetailStatus() == OrderDetailStatusEnum.DELIVERING.getValue()) {
						isValid = true;
					} else {
						isValid = false;
					}
					break;
				default:
					isValid = false;
					break;
				}
				if(!isValid) {
					response.setStatus(ResponseStatusEnum.NOT_FOUND);
					response.setMessage(ResponseStatusEnum.NOT_FOUND);
				} else {
					String historyLog = String.format("Supplier chuyển [%s] sang trạng thái [%s], lý do: [%s].", orderDetail.getSku().getName(), orderDetail.getOrderDetailStatus(), wrapper.getNote());
					orderDetail.setOrderDetailStatus(wrapper.getOrderDetailStatusEnum().getValue());
					orderDetail.setHistoryLog(historyLog);
					orderDetailService.update(orderDetail);
					boolean isCancel = wrapper.getOrderDetailStatusEnum() == OrderDetailStatusEnum.CANCEL;
					this.updateOrderStatus(order.getId(), historyLog, isCancel);
					if(isCancel) {
						this.returnSkuQuantity(orderDetail.getId());
					}
					order = orderService.findOne(order.getId());
					response.setData(new OrderSupplierDto(order, supplier.getId()));
				}
			}
			
			
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
	
//	private void calculateAndUpdateCashbackForCustomer(long orderId) {
//		Order order = orderService.findOne(orderId);
//		if (order.getOrderStatusEnum() == OrderStatusEnum.COMPLETED) {
//			List<OrderDetail> orderDetails = order.getOrderDetails().stream()
//					.filter(x -> x.getOrderDetailStatusEnum() == OrderDetailStatusEnum.COMPLETED)
//					.collect(Collectors.toList());
//			float totalCashback = (float) orderDetails.stream().mapToDouble(x -> x.getCashback()).sum();
//			if (totalCashback > 0) {
//				Customer customer = order.getCustomer();
//				customer.setNeediiCash(totalCashback);
//			}
//		}
//	}
}
