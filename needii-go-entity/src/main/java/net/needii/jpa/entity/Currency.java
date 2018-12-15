package net.needii.jpa.entity;

import javax.persistence.*;


/**
 * The persistent class for the currency database table.
 * 
 */
@Entity
@NamedQuery(name="Currency.findAll", query="SELECT c FROM Currency c")
public class Currency extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	private float rate;

	private String unit;

	@Column(name="unit_symbol")
	private String unitSymbol;

	//bi-directional many-to-one association to Language
	@ManyToOne
	private Language language;

	public Currency() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getRate() {
		return this.rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUnitSymbol() {
		return this.unitSymbol;
	}

	public void setUnitSymbol(String unitSymbol) {
		this.unitSymbol = unitSymbol;
	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

}