/**
 * 
 */
package net.needii.dto.request;

import util.validate.PhoneValidatorConstraint;


/**
 * @author longnguyen
 *
 */
public class TransferNeediiCashRequest extends BaseRequest {
	
	@PhoneValidatorConstraint
	private String phone;
	
	private float amount;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
}
