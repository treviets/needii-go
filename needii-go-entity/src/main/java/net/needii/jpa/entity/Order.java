package net.needii.jpa.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import util.Utils;


/**
 * The persistent class for the carts database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	
	@Column(name="order_code")
	private String orderCode;
	
	@Column(name="promotion_id")
	private int promotionCodeId;
	
	@Column(name="promotion_amount")
	private float promotionAmount;
	
	@Column(name="amount")
	private float amount;
	
	@Column(name="shipping_fee")
	private float shippingFee;
	
	@Column(name="total_amount")
	private float totalAmount;
	
	@Column(name="total_cashback")
	private float totalCashback;
	
	private String note;
	
	@Column(name="history_logs")
	private String historyLog;
	
	@Column(name="order_status")
	private int orderStatus;
	
	@Column(name="payment_status")
	private int paymentStatus;
	
	@Column(name="shipping_option_id")
	private int shippingOptionId;
	
	@Column(name="payment_card_id")
	private int paymentCardId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="delivery_at")
	@CreationTimestamp
	private Date deliveryAt;
	
	@OneToMany(mappedBy="order")
	private Set<OrderDetail> orderDetails;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	private Customer customer;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "payment_method_id")
	private PaymentMethod paymentMethod;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "shipping_address_id")
	private CustomerAddress shippingAddress;

	public Order() {
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
	public List<OrderDetail> getOrderDetails() {
		List<OrderDetail> list = new ArrayList<OrderDetail>(this.orderDetails);
		return list;
	}

	/**
	 * @param orderDetails the orderDetails to set
	 */
	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the paymentMethod
	 */
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * @return the shippingAddress
	 */
	public CustomerAddress getShippingAddress() {
		return shippingAddress;
	}

	/**
	 * @param shippingAddress the shippingAddress to set
	 */
	public void setShippingAddress(CustomerAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	/**
	 * @return the orderStatus
	 */
	public int getOrderStatus() {
		return orderStatus;
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

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
}