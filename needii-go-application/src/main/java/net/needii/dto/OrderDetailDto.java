/**
 * 
 */
package net.needii.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.needii.jpa.entity.OrderDetailStatusEnum;
import util.Utils;

public class OrderDetailDto extends BaseModelResponseImpl {

	private long id;
	
	@JsonProperty("unit_price")
	private float unitPrice;
	
	@JsonProperty("promotion_price")
	private float promotionPrice;
	
	@JsonProperty("shipping_fee")
	private float shippingFee;
	
	private int quantity;
	
	@JsonProperty("total_price")
	private float totalPrice;
	
	private float cashback;
	
	@JsonProperty("shipper_id")
	private int shipperId;
	
	@JsonProperty("complete_by_supplier_id")
	private int completeBySupplierId;
	
	@JsonProperty("order_detail_status")
	private int orderDetailStatus;

	private String note;
	
	@JsonProperty("history_logs")
	private String historyLog;
	
	
	private SupplierDto supplier;
	
	private OrderDto order;
	
	private ProductDto product;
	
	private SkuDto sku;

	public OrderDetailDto() {
		this.shipperId = 0;
		this.completeBySupplierId = 0;
		this.note = "";
		this.historyLog = "";
		this.promotionPrice = 0;
		this.cashback = 0;
		this.setOrderDetailStatus(OrderDetailStatusEnum.PENDING);
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}


	/**
	 * @return the unitPrice
	 */
	public float getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the promotionPrice
	 */
	public float getPromotionPrice() {
		return promotionPrice;
	}

	/**
	 * @param promotionPrice the promotionPrice to set
	 */
	public void setPromotionPrice(float promotionPrice) {
		this.promotionPrice = promotionPrice;
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
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the totalPrice
	 */
	public float getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the shipperId
	 */
	public int getShipperId() {
		return shipperId;
	}

	/**
	 * @param shipperId the shipperId to set
	 */
	public void setShipperId(int shipperId) {
		this.shipperId = shipperId;
	}

	/**
	 * @return the completeBySupplierId
	 */
	public int getCompleteBySupplierId() {
		return completeBySupplierId;
	}

	/**
	 * @param completeBySupplierId the completeBySupplierId to set
	 */
	public void setCompleteBySupplierId(int completeBySupplierId) {
		this.completeBySupplierId = completeBySupplierId;
	}

	/**
	 * @return the orderDetailStatus
	 */
	public int getOrderDetailStatus() {
		return orderDetailStatus;
	}
	
	/**
	 * @param orderDetailStatus the orderDetailStatus to set
	 */
	public void setOrderDetailStatus(int orderDetailStatus) {
		this.orderDetailStatus = orderDetailStatus;
	}
	
	public void setOrderDetailStatus(OrderDetailStatusEnum orderDetailStatusEnum) {
		this.orderDetailStatus = orderDetailStatusEnum.getValue();
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
	 * @return the supplier
	 */
	public SupplierDto getSupplier() {
		return supplier;
	}

	/**
	 * @param supplier the supplier to set
	 */
	public void setSupplier(SupplierDto supplier) {
		this.supplier = supplier;
	}

	/**
	 * @return the order
	 */
	public OrderDto getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(OrderDto order) {
		this.order = order;
	}

	/**
	 * @return the product
	 */
	public ProductDto getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(ProductDto product) {
		this.product = product;
	}

	/**
	 * @return the sku
	 */
	public SkuDto getSku() {
		return sku;
	}

	/**
	 * @param sku the sku to set
	 */
	public void setSku(SkuDto sku) {
		this.sku = sku;
	}

	public float getCashback() {
		return cashback;
	}

	public void setCashback(float cashback) {
		this.cashback = cashback;
	}
	
}