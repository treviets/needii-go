package net.needii.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import util.Utils;


/**
 * The persistent class for the carts database table.
 * 
 */
@Entity
@Table(name="order_details")
@NamedQuery(name="OrderDetail.findAll", query="SELECT od FROM OrderDetail od")
public class OrderDetail extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	
	@Column(name="sku_name")
	private String skuName;
	
	@Column(name="sku_image")
	private String skuImage;
	
	@Column(name="unit_price")
	private float unitPrice;
	
	@Column(name="promotion_price")
	private float promotionPrice;
	
	@Column(name="shipping_fee")
	private float shippingFee;
	
	private int quantity;
	
	@Column(name="total_price")
	private float totalPrice;
	
	private float cashback;
	
	@Column(name="shipper_id")
	private int shipperId;
	
	@Column(name="complete_by_supplier_id")
	private int completeBySupplierId;
	
	@Column(name="order_detail_status")
	private int orderDetailStatus;

	private String note;
	
	@Column(name="history_logs")
	private String historyLog;
	
	
	@ManyToOne
	private Supplier supplier;
	
	@ManyToOne
	private Order order;
	
	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@OneToOne
	@JoinColumn(name = "sku_id")
	private Skus sku;

	public OrderDetail() {
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
	 * @return the skuName
	 */
	public String getSkuName() {
		return skuName;
	}

	/**
	 * @param skuName the skuName to set
	 */
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	/**
	 * @return the skuImage
	 */
	public String getSkuImage() {
		return skuImage;
	}

	/**
	 * @param skuImage the skuImage to set
	 */
	public void setSkuImage(String skuImage) {
		this.skuImage = skuImage;
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
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * @param supplier the supplier to set
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the sku
	 */
	public Skus getSku() {
		return sku;
	}

	/**
	 * @param sku the sku to set
	 */
	public void setSku(Skus sku) {
		this.sku = sku;
	}

	public float getCashback() {
		return cashback;
	}

	public void setCashback(float cashback) {
		this.cashback = cashback;
	}
	
}