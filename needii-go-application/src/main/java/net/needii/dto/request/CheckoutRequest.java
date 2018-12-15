/**
 * 
 */
package net.needii.dto.request;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.needii.jpa.entity.PaymentMethodTypeEnum;


/**
 * @author longnguyen
 *
 */
public class CheckoutRequest extends BaseRequest {
	
	@JsonProperty("shipping_address_id")
	@Min(value = 1, message="Checkout.ShippingAddressId.Invalid")
	private long shippingAddressId;
	
	@JsonProperty("payment_method_type")
	@Min(value = 1, message="Checkout.PaymentMethodType.Invalid")
	private int paymentMethodType;
	
	@JsonProperty("payment_card_id")
	@Min(value = 0, message="Checkout.PaymentCartId.Invalid")
	private int paymentCardId;
	
	private String note;

	/**
	 * @return the shippingAddressId
	 */
	public long getShippingAddressId() {
		return shippingAddressId;
	}

	/**
	 * @param shippingAddressId the shippingAddressId to set
	 */
	public void setShippingAddressId(long shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}

	/**
	 * @return the paymentCardId
	 */
	public int getPaymentCardId() {
		return paymentCardId;
	}

	/**
	 * @param paymentCardId the paymentCardId to set
	 */
	public void setPaymentCardId(int paymentCardId) {
		this.paymentCardId = paymentCardId;
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

	public int getPaymentMethodType() {
		return paymentMethodType;
	}
	
	public PaymentMethodTypeEnum getPaymentMethodTypeEnum() {
		switch(this.paymentMethodType) {
		case 1:
			return PaymentMethodTypeEnum.NEEDII_CASH;
		case 2:
			return PaymentMethodTypeEnum.CREDIT_CARD;
		case 3:
			return PaymentMethodTypeEnum.COD;
		default:
			return PaymentMethodTypeEnum.COD;
		}
	}

	public void setPaymentMethodType(int paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
	}
}
