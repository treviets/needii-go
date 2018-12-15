/**
 * 
 */
package net.needii.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.needii.jpa.entity.OrderDetailStatusEnum;


/**
 * @author longnguyen
 *
 */
public class OrderDetailStatusRequest extends BaseRequest {
	
	@JsonProperty("id")
	private long id;
	
	@JsonProperty("order_detail_status")
	private int orderDetailStatus;
	
	private String note;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public int getOrderDetailStatus() {
		return orderDetailStatus;
	}
	
	public OrderDetailStatusEnum getOrderDetailStatusEnum() {
		switch(orderDetailStatus) {
		case 1:
			return OrderDetailStatusEnum.PROCESSING;
		case 2:
			return OrderDetailStatusEnum.PACKAGED;
		case 3:
			return OrderDetailStatusEnum.DELIVERING;
		case 4:
			return OrderDetailStatusEnum.COMPLETED;
		case 5:
			return OrderDetailStatusEnum.CANCEL;
		default:
			return OrderDetailStatusEnum.CANCEL;
		}
	}

	public void setOrderDetailStatus(int orderDetailStatus) {
		this.orderDetailStatus = orderDetailStatus;
	}

	public String getNote() {
		return (note == null || note.isEmpty()) ? this.getOrderDetailStatusEnum().toString() : note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
