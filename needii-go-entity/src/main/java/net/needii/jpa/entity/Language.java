package net.needii.jpa.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the languages database table.
 * 
 */
@Entity
@Table(name="languages")
@NamedQuery(name="Language.findAll", query="SELECT l FROM Language l")
public class Language extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String code;

	private String flag;

	private String name;

	private int order;

	private boolean status;

	@Column(name="unit_symbol")
	private String unitSymbol;

	//bi-directional many-to-one association to Currency
	@OneToMany(mappedBy="language")
	private List<Currency> currencies;

	public Language() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrder() {
		return this.order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getUnitSymbol() {
		return this.unitSymbol;
	}

	public void setUnitSymbol(String unitSymbol) {
		this.unitSymbol = unitSymbol;
	}

	public List<Currency> getCurrencies() {
		return this.currencies;
	}

	public void setCurrencies(List<Currency> currencies) {
		this.currencies = currencies;
	}

	public Currency addCurrency(Currency currency) {
		getCurrencies().add(currency);
		currency.setLanguage(this);

		return currency;
	}

	public Currency removeCurrency(Currency currency) {
		getCurrencies().remove(currency);
		currency.setLanguage(null);

		return currency;
	}
}