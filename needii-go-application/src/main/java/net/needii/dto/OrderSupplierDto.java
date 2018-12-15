/**
 * 
 */
package net.needii.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("unchecked")
public class OrderSupplierDto extends BaseModelResponseImpl {
	
	private String id;
	
	private String note;
	
	@JsonProperty("customer_name")
	private String customerName;
	
	@JsonProperty("customer_phone")
	private String customerPhone;
	
	@JsonProperty("customer_address")
	private String customerAddress;
	
	@JsonProperty("order_status")
	private int orderStatus;
	
	@JsonProperty("order_status_name")
	private String orderStatusName;
	
	@JsonProperty("shipping_method")
	private String shippingMethod;
	
	@JsonProperty("payment_method")
	private String paymentMethod;
	
	@JsonProperty("total_amount")
	private float totalAmount;
	
	@JsonProperty("total_cashback")
	private float totalCashback;
	
	@JsonProperty("delivery_at")
	private String deliveryAt;
	
	@JsonProperty("created_at")
	private String createdAt;
	
	@JsonProperty("order_details")
	private List<OrderDetailDto> orderDetails;
	
	public OrderSupplierDto() {
		
	}
	
	public OrderSupplierDto(OrderDto entity, Long supplierId) {
		List<OrderDetailDto> orderDetails = entity.getOrderDetails()
				.stream()
				.filter(x -> x.getSupplier().getId() == supplierId)
				.collect(Collectors.toList());
		CustomerAddressDto address = entity.getShippingAddress();
		this.id = entity.getEncryptId();
		this.customerName = address.getReceiverName();
		this.customerPhone = address.getPhone();
		this.customerAddress = address.getFullTextAddress();
		this.orderStatus = entity.getOrderStatus();
		this.orderStatusName = entity.getOrderStatusString();
		this.setShippingMethod("Tiêu chuẩn");
		this.paymentMethod = entity.getPaymentMethod().getName();
		
		this.totalAmount = (float)orderDetails.stream().mapToDouble(x -> x.getTotalPrice()).sum();
		this.totalCashback = (float)orderDetails.stream().mapToDouble(x -> x.getCashback()).sum();
		
		this.deliveryAt = String.valueOf(entity.getDeliveryAt());
		this.createdAt = String.valueOf(entity.getCreatedAt());
		this.setOrderDetails((List<OrderDetailDto>) ((new OrderDetailDto()).mapToListResponse(orderDetails)));
	}
	
	@Override
	public List<?> mapToListResponse(List<?> baseEntities, Long optionId) {
		List<OrderDto> entities = (List<OrderDto>) baseEntities;
		
		List<OrderSupplierDto> list =  new ArrayList<OrderSupplierDto>(); 
		for (OrderDto entity : entities) {
			list.add(new OrderSupplierDto(entity, optionId));
		}
		return list;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public List<OrderDetailDto> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailDto> orderDetails) {
		this.orderDetails = orderDetails;
	}


	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public float getTotalCashback() {
		return totalCashback;
	}

	public void setTotalCashback(float totalCashback) {
		this.totalCashback = totalCashback;
	}
}