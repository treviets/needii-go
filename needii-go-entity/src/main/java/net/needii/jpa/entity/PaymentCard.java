package net.needii.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the admins database table.
 * 
 */
@Entity
@Table(name="payment_cards")
@NamedQuery(name="PaymentCard.findAll", query="SELECT pc FROM PaymentCard pc")
public class PaymentCard extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	@Column(name="full_name")
	private String fullName;
	
	@Column(name="card_number")
	private String cardNumber;
	
	@Column(name="expiration_day")
	private int expirationDay;
	
	@Column(name="expiration_year")
	private int expirationYear;
	
	private int ccv;
	
	@Column(name="card_type")
	private int cardType;
	
	private boolean status;
	
	@ManyToOne
	private Customer customer;
	
	public PaymentCard() {
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @return the expirationDay
	 */
	public int getExpirationDay() {
		return expirationDay;
	}

	/**
	 * @param expirationDay the expirationDay to set
	 */
	public void setExpirationDay(int expirationDay) {
		this.expirationDay = expirationDay;
	}

	/**
	 * @return the expirationYear
	 */
	public int getExpirationYear() {
		return expirationYear;
	}

	/**
	 * @param expirationYear the expirationYear to set
	 */
	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}

	/**
	 * @return the ccv
	 */
	public int getCcv() {
		return ccv;
	}

	/**
	 * @param ccv the ccv to set
	 */
	public void setCcv(int ccv) {
		this.ccv = ccv;
	}

	/**
	 * @return the cardType
	 */
	public int getCardType() {
		return cardType;
	}
	
	public CardTypeEnum getCardTypeEnum() {
		switch(this.cardType) {
		case 1:
			return CardTypeEnum.VISA;
		case 2:
			return CardTypeEnum.MASTERCARD;
		case 3:
			return CardTypeEnum.AMERICAN_EXPRESS;
		case 4:
			return CardTypeEnum.DISCOVER;
		case 5:
			return CardTypeEnum.MAESTRO;
		case 6:
			return CardTypeEnum.JCB;
		default:
			return CardTypeEnum.OTHER;
		}
	}

	/**
	 * @param cardType the cardType to set
	 */
	public void setCardType(int cardType) {
		this.cardType = cardType;
	}
	
	public void setCardType(CardTypeEnum typeEnum) {
		this.cardType = typeEnum.getValue();
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}