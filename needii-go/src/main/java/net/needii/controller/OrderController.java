/**
 * 
 */
package net.needii.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.needii.dto.BaseDto;
import net.needii.dto.OrderDto;
import net.needii.dto.PaymentMethodDto;
import net.needii.dto.ResponseStatusEnum;
import net.needii.dto.request.CheckoutRequest;
import net.needii.jpa.entity.PaymentMethodTypeEnum;
import net.needii.jpa.entity.PaymentStatusEnum;
import net.needii.service.CustomerAddressService;
import net.needii.service.CustomerService;
import net.needii.service.OrderService;
import net.needii.service.PaymentMethodService;

/**
 * @author kelvin
 *
 */
@RestController
@RequestMapping("api/orders")
@PreAuthorize("hasRole('CUSTOMER')")
public class OrderController extends BaseController {


	@Autowired
	private OrderService orderService;


	@Autowired
	private PaymentMethodService paymentMethodService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerAddressService customerAddress;

	@RequestMapping(value = "/payment-methods", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseDto> get(HttpServletRequest request) {
		BaseDto response = new BaseDto();
		try {
			getCurrentUser(request);
			List<PaymentMethodDto> entities = paymentMethodService.findAll();
			response.setData((new PaymentMethodDto()).mapToListResponse(entities));

		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseDto> checkout(HttpServletRequest request,
			@Valid @RequestBody CheckoutRequest wrapper) {
		BaseDto response = new BaseDto();
		try {
			long customerId = this.getCurrentUser(request).getId();

			PaymentMethodDto paymentMethod = paymentMethodService.findByType(wrapper.getPaymentMethodTypeEnum());

			PaymentStatusEnum paymentStatus = PaymentStatusEnum.WAITING;
			
			if(paymentMethod.getType() == PaymentMethodTypeEnum.NEEDII_CASH.getValue()) {
				paymentStatus = PaymentStatusEnum.PAID;
			}
			
			OrderDto orderRequest = new OrderDto();
			orderRequest.setCustomer(customerService.findOne(customerId));
			orderRequest.setShippingAddress(customerAddress.findOne(wrapper.getShippingAddressId()));
			orderRequest.setPaymentMethod(paymentMethod);
			orderRequest.setShippingOptionId(0);
			orderRequest.setPaymentCardId(wrapper.getPaymentCardId());
			orderRequest.setPaymentStatus(paymentStatus.getValue());
			orderRequest.setNote(wrapper.getNote());
			OrderDto order = orderService.create(orderRequest);

			if (order == null) {
				response.setStatus(ResponseStatusEnum.FAIL);
				response.setMessageError("Order.CreateFail");
			} else {
				order.setOrderCode(String.valueOf(order.getId()));
				orderService.update(order);
				response.setData(order);
			}
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessage(ResponseStatusEnum.FAIL);
			ex.printStackTrace();
		}
		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}
}
