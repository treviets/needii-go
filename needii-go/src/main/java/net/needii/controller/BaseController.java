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
import org.springframework.beans.factory.annotation.Autowired;
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
import net.needii.dto.CustomerBalanceHistoryDto;
import net.needii.dto.CustomerDto;
import net.needii.dto.OrderDetailDto;
import net.needii.dto.OrderDto;
import net.needii.dto.ResponseStatusEnum;
import net.needii.dto.SkuDto;
import net.needii.dto.TokenDto;
import net.needii.jpa.entity.Language;
import net.needii.jpa.entity.OrderDetailStatusEnum;
import net.needii.jpa.entity.OrderStatusEnum;
import net.needii.jpa.entity.security.NeediiUser;
import net.needii.service.CustomerBalanceHistoryService;
import net.needii.service.CustomerService;
import net.needii.service.LanguageService;
import net.needii.service.OrderDetailService;
import net.needii.service.OrderService;
import net.needii.service.SkuService;
import util.Constants;
import util.Utils;

/**
 * @author kelvin
 *
 */

@RestController
@PreAuthorize("hasRole('GUEST') or hasRole('CUSTOMER') or hasRole('SUPPLIER')")
public class BaseController {

	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private SkuService skuService;
	
	@Autowired
	private CustomerBalanceHistoryService customerBalanceHistoryService;

	final static Logger logger = LogManager.getLogger(BaseController.class);
	
	public void setLog(Exception ex) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		logger.error(sw.toString());
	}

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
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<BaseDto> handleMissingParams(MissingServletRequestParameterException ex) {
		// Actual exception handling
		BaseDto response = new BaseDto();
		response.setStatus(ResponseStatusEnum.MISSING_PARAMS);
		response.setMessageError(String.format("%s is required!", ex.getParameterName()));
		response.setData(null);

		return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
	}

	public Language getLanguage(HttpServletRequest request) {

		String languageCode = request.getHeader("Language");
		if (languageCode == null)
			languageCode = Constants.DEFAULT_VIETNAMESE_LANGUAGE;

		Language language = languageService.findOne(languageCode);
		language = language == null ? languageService.findOne(Constants.DEFAULT_VIETNAMESE_LANGUAGE) : language;

		return language;
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
	
	public void updateOrderStatus(long orderId, String historyLog, boolean isCalculateAllPrice) {
		OrderDto order = orderService.findOne(orderId);
		if(order != null && order.getOrderStatusEnum() != OrderStatusEnum.COMPLETED && order.getOrderStatusEnum() != OrderStatusEnum.CANCEL) {
			List<OrderDetailDto> orderDetails = orderDetailService.findByOrderId(order.getId());
			List<OrderDetailDto> orderDetailPending = orderDetails.stream().filter(x -> x.getOrderDetailStatus() == OrderDetailStatusEnum.PENDING.getValue()).collect(Collectors.toList());
			List<OrderDetailDto> orderDetailProcess = orderDetails.stream().filter(x -> x.getOrderDetailStatus() == OrderDetailStatusEnum.PROCESSING.getValue()).collect(Collectors.toList());
			List<OrderDetailDto> orderDetailCompleted = orderDetails.stream().filter(x -> x.getOrderDetailStatus() == OrderDetailStatusEnum.COMPLETED.getValue()).collect(Collectors.toList());
			List<OrderDetailDto> orderDetailCancel = orderDetails.stream().filter(x -> x.getOrderDetailStatus() == OrderDetailStatusEnum.CANCEL.getValue()).collect(Collectors.toList());
			if (orderDetailPending.size() == 0 && orderDetailProcess.size() == 0 && orderDetailCompleted.size() == 0) {
				order.setOrderStatus(OrderStatusEnum.CANCEL.getValue());
			} else if(orderDetailPending.size() > 0 && orderDetailProcess.size() == 0 && orderDetailCompleted.size() == 0) {
				order.setOrderStatus(OrderStatusEnum.PENDING.getValue());
			} else if(orderDetailPending.size() == 0 && orderDetailProcess.size() == 0 && orderDetailCancel.size() == 0) {
				order.setOrderStatus(OrderStatusEnum.COMPLETED.getValue());
				
				// tính cashback cho customer
				float totalCashback = (float) orderDetailCompleted.stream().mapToDouble(x -> x.getCashback()).sum();
				if (totalCashback > 0) {
					CustomerDto customer = order.getCustomer();
					customer.setNeediiCash(totalCashback);
					customerService.update(customer);
					
					CustomerBalanceHistoryDto history = new CustomerBalanceHistoryDto();
					history.setAmount(totalCashback);
					history.setCustomer(customer);
					history.setNote(String.format("Nhận %d Needii Cash từ đơn hàng #%s", totalCashback, order.getOrderCode()));
					customerBalanceHistoryService.create(history);
				}
			} else {
				order.setOrderStatus(OrderStatusEnum.PROCESSING.getValue());
			}
			
			if(isCalculateAllPrice) {
				List<OrderDetailDto> orderDetailNotCancel = orderDetails.stream().filter(x -> !(x.getOrderDetailStatus() == OrderDetailStatusEnum.CANCEL.getValue())).collect(Collectors.toList());
				
				float totalPrice = (float)orderDetailNotCancel.stream().mapToDouble(x -> x.getTotalPrice()).sum();
				float totalShippingFee = (float)orderDetailNotCancel.stream().mapToDouble(x -> x.getShippingFee()).sum();
				float totalPromotionPrice = (float)orderDetailNotCancel.stream().mapToDouble(x -> x.getPromotionPrice()).sum();
				float totalCashback = (float)orderDetailNotCancel.stream().mapToDouble(x -> x.getCashback()).sum();
				float totalAmount = totalPrice + totalShippingFee - totalPromotionPrice;
						
				order.setAmount(totalPrice);
				order.setShippingFee(totalShippingFee);
				order.setPromotionAmount(totalPromotionPrice);
				order.setTotalCashback(totalCashback);
				order.setTotalAmount(totalAmount);
				
			}
			order.setHistoryLog(historyLog);
			orderService.update(order);
		}
	}
	
	public void returnSkuQuantity(long orderDetailId) {
		OrderDetailDto orderDetail = orderDetailService.findOne(orderDetailId);
		SkuDto sku = orderDetail.getSku();
		if(sku.getQuantity() >= 0) {
			sku.setQuantity(sku.getQuantity() + orderDetail.getQuantity());
			skuService.update(sku);
		}
	}
}
