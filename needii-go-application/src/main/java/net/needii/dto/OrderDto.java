/**
 * 
 */
package net.needii.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.needii.jpa.entity.OrderStatusEnum;
import net.needii.jpa.entity.PaymentStatusEnum;
import util.Utils;

public class OrderDto extends BaseModelResponseImpl {
	
private long id;
	
	@JsonProperty("order_code")
	private String orderCode;
	
	@JsonProperty("promotion_id")
	private int promotionCodeId;
	
	@JsonProperty("promotion_amount")
	private float promotionAmount;
	
	@JsonProperty("amount")
	private float amount;
	
	@JsonProperty("shipping_fee")
	private float shippingFee;
	
	@JsonProperty("total_amount")
	private float totalAmount;
	
	@JsonProperty("total_cashback")
	private float totalCashback;
	
	private String note;
	
	@JsonProperty("history_logs")
	private String historyLog;
	
	@JsonProperty("order_status")
	private int orderStatus;
	
	@JsonProperty("payment_status")
	private int paymentStatus;
	
	@JsonProperty("shipping_option_id")
	private int shippingOptionId;
	
	@JsonProperty("payment_card_id")
	private int paymentCardId;
	
	@JsonProperty("delivery_at")
	private Date deliveryAt;
	
	@JsonProperty("created_at")
	private Date createdAt;
	
	private Set<OrderDetailDto> orderDetails;

	private CustomerDto customer;
	
	private PaymentMethodDto paymentMethod;
	
	private CustomerAddressDto shippingAddress;

	public OrderDto() {
		this.promotionCodeId = 0;
		this.promotionAmount = 0;
		this.shippingFee = 0;
		this.totalCashback = 0;
		this.note = "";
		this.historyLog = "";
		this.setPaymentStatus(PaymentStatusEnum.WAITING.getValue());
		this.setOrderStatus(OrderStatusEnum.PENDING.getValue());
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	public String getEncryptId() {
		return Utils.encryptOrderId(this.id);
	}
	
	public long getDecryptId(String encryptId) {
		return Utils.decryptOrderId(encryptId);
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the promotionCodeId
	 */
	public int getPromotionCodeId() {
		return promotionCodeId;
	}

	/**
	 * @param promotionCodeId the promotionCodeId to set
	 */
	public void setPromotionCodeId(int promotionCodeId) {
		this.promotionCodeId = promotionCodeId;
	}

	/**
	 * @return the promotionAmount
	 */
	public float getPromotionAmount() {
		return promotionAmount;
	}

	/**
	 * @param promotionAmount the promotionAmount to set
	 */
	public void setPromotionAmount(float promotionAmount) {
		this.promotionAmount = promotionAmount;
	}

	/**
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 * @return the shippingFee
	 */
	public float getShippingFee() {
		return shippingFee;
	}

	/**
	 * @param shippingFee the shippingFee to set
	 */
	public void setShippingFee(float shippingFee) {
		this.shippingFee = shippingFee;
	}

	/**
	 * @return the totalAmount
	 */
	public float getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the historyNote
	 */
	public String getHistoryLog() {
		return historyLog;
	}

	/**
	 * @param historyNote the historyNote to set
	 */
	public void setHistoryLog(String historyLog) {
		if(this.historyLog == null || this.historyLog.isEmpty()) {
			this.historyLog = "";
		} else {
			this.historyLog = this.historyLog + "\n";
		}
		historyLog = String.format("%s: %s", Utils.getDatetimeFormatVN(new Date()), historyLog);
		this.historyLog = this.historyLog + historyLog;
	}

	/**
	 * @return the orderDetails
	 */
	public List<OrderDetailDto> getOrderDetails() {
		if(orderDetails != null) {
			List<OrderDetailDto> list = new ArrayList<OrderDetailDto>(this.orderDetails);
			return list;
		}
		return null;
	}

	/**
	 * @param orderDetails the orderDetails to set
	 */
	public void setOrderDetails(Set<OrderDetailDto> orderDetails) {
		this.orderDetails = orderDetails;
	}

	/**
	 * @return the customer
	 */
	public CustomerDto getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

	/**
	 * @return the paymentMethod
	 */
	public PaymentMethodDto getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(PaymentMethodDto paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * @return the shippingAddress
	 */
	public CustomerAddressDto getShippingAddress() {
		return shippingAddress;
	}

	/**
	 * @param shippingAddress the shippingAddress to set
	 */
	public void setShippingAddress(CustomerAddressDto shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	/**
	 * @return the orderStatus
	 */
	public int getOrderStatus() {
		return orderStatus;
	}
	
	public OrderStatusEnum getOrderStatusEnum() {
		switch(this.orderStatus) {
		case 0:
			return OrderStatusEnum.PENDING;
		case 1:
			return OrderStatusEnum.PROCESSING;
		case 2:
			return OrderStatusEnum.COMPLETED;
		case 3:
			return OrderStatusEnum.CANCEL;
		default:
			return OrderStatusEnum.UNKNOW;
		}
	}
	
	public String getOrderStatusString() {
		switch(this.getOrderStatusEnum()) {
		case PENDING:
			return "Order.Status.Pending";
		case PROCESSING:
			return "Order.Status.Processing";
		case COMPLETED:
			return "Order.Status.Completed";
		case CANCEL:
			return "Order.Status.Cancel";
		default:
			return "";
		}
	}

	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	

	/**
	 * @return the paymentStatus
	 */
	public int getPaymentStatus() {
		return paymentStatus;
	}
	
	public PaymentStatusEnum getPaymentStatusEnum() {
		switch(this.paymentStatus) {
		case 0:
			return PaymentStatusEnum.WAITING;
		case 1:
			return PaymentStatusEnum.PROCESSING;
		case 2:
			return PaymentStatusEnum.PAID;
		default:
			return PaymentStatusEnum.UNKNOW;
		}
	}

	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	/**
	 * @return the shippingOptionId
	 */
	public int getShippingOptionId() {
		return shippingOptionId;
	}

	/**
	 * @param shippingOptionId the shippingOptionId to set
	 */
	public void setShippingOptionId(int shippingOptionId) {
		this.shippingOptionId = shippingOptionId;
	}

	public Date getDeliveryAt() {
		return deliveryAt;
	}

	public void setDeliveryAt(Date deliveryAt) {
		this.deliveryAt = deliveryAt;
	}

	public int getPaymentCardId() {
		return paymentCardId;
	}

	public void setPaymentCardId(int paymentCardId) {
		this.paymentCardId = paymentCardId;
	}

	public float getTotalCashback() {
		return totalCashback;
	}

	public void setTotalCashback(float totalCashback) {
		this.totalCashback = totalCashback;
	}

	public boolean isAllowSupplierProccess() {
		switch(this.getOrderStatusEnum()) {
		case PENDING:
		case PROCESSING:
			return true;
		default:
			return false;
		}
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
}