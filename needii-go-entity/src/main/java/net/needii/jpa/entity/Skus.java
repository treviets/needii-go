package net.needii.jpa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import util.Constants;


/**
 * The persistent class for the skus database table.
 * 
 */
@Entity
@Table(name="skus")
@NamedQuery(name="Skus.findAll", query="SELECT s FROM Skus s")
public class Skus implements Serializable {
	private static final long serialVersionUID = 1L;
   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;


	@Column(name="discount_percent")
	private float discountPercent;

	@Column(name="cashback_percent")
	private float cashbackPercent;

	@Lob
	@Column(name="image_slide")
	private String imageSlide;

	@Column(name="is_default")
	private boolean isDefault;

	@Column(name="is_deleted")
	private boolean isDeleted;

	@Column(name="last_price")
	private float lastPrice;

	private float price;

	private int quantity;

	@Column(name="sku_code")
	private String skuCode;

	private boolean status;

	//bi-directional many-to-one association to SkuData
	@OneToMany(mappedBy="skus", cascade = CascadeType.ALL)
	private List<SkuData> skuData;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	public Skus() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getDiscountPercent() {
		return this.discountPercent;
	}

	public void setDiscountPercent(float discountPercent) {
		this.discountPercent = discountPercent;
	}

    public float getCashbackPercent() {
        return this.cashbackPercent;
    }

    public void setCashbackPercent(float cashbackPercent) {
        this.cashbackPercent = cashbackPercent;
    }

	public String getImageSlide() {
		return this.imageSlide;
	}

	public void setImageSlide(String imageSlide) {
		this.imageSlide = imageSlide;
	}
	
	@JsonIgnore
	public List<String> getImageSlideAsListString() {
		if(this.imageSlide == null || this.imageSlide.isEmpty()) {
			return new ArrayList<>();
		} else {
			return new ArrayList<>(Arrays.asList(this.imageSlide.split(";")));
		}
	}
	
	@JsonIgnore
	public Set<String> getImageWithPathAsListString() {
		if(this.imageSlide == null || this.imageSlide.isEmpty()) {
			return new HashSet<>();
		} else {
			Set<String> images = new HashSet<>(Arrays.asList(this.imageSlide.split(";")));
			Set<String> result = new HashSet<>();
			for (String image : images) {
				if(image != null && !image.contains("http")) {
					result.add(Constants.RESOURCE_DOMAIN + image);
				} else {
					result.add(image);
				}
				
			}
			return result;
		}
	}

	public boolean getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public float getLastPrice() {
		return this.lastPrice;
	}

	public void setLastPrice(float lastPrice) {
		this.lastPrice = lastPrice;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSkuCode() {
		return this.skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public List<SkuData> getSkuData() {
		return this.skuData;
	}

	public void setSkuData(List<SkuData> skuData) {
		this.skuData = skuData;
	}

	@JsonIgnore
	public SkuData addSkuData(SkuData skuData) {
		getSkuData().add(skuData);
		skuData.setSkus(this);

		return skuData;
	}

	@JsonIgnore
	public SkuData removeSkuData(SkuData skuData) {
		getSkuData().remove(skuData);
		skuData.setSkus(null);

		return skuData;
	}

	@JsonIgnore
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@JsonIgnore
    public SkuData getData() {
        return this.getDataByLanguage(Constants.DEFAULT_VIETNAMESE_LANGUAGE_ID, true);
    }

	@JsonIgnore
    public SkuData getData(int languageId) {
        return this.getDataByLanguage(languageId, false);
    }

	@JsonIgnore
    private SkuData getDataByLanguage(int languageId, boolean getDefault) {
        SkuData bannerData = this.getSkuData()
                .stream()
                .filter(x -> x.getLanguageId() == languageId)
                .findFirst()
                .orElse(null);
        if(bannerData == null) {
            if(!getDefault) return new SkuData();
            else {
                bannerData = this.getSkuData().stream()
                        .filter(x -> x.getLanguageId() == Constants.DEFAULT_VIETNAMESE_LANGUAGE_ID)
                        .findFirst() .orElse(new SkuData());
            }
        }
        return bannerData;
    }
    
    public boolean validToSell() {
    		return true;
    }
}