package net.needii.jpa.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import util.Constants;


/**
 * The persistent class for the categories database table.
 * 
 */
@Entity
@Table(name="categories")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="category_parent_id")
	private int parentId;
	
	@Column(name="category_parent_ids")
	private String parentIds;
	
	private int level;
	
	@Column(name="icon_url")
	private String iconUrl;

	@Column(name="cashback_percent")
	private float cashbackPercent;
	
	@Column(name="sort")
	private int order;
	
	private boolean status;

	//bi-directional many-to-one association to CategoryData
	@OneToMany(mappedBy="category")
	private Set<CategoryData> categoryData = new HashSet<CategoryData>();
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "category_product_attribute_maps", 
      joinColumns = 
        @JoinColumn(name = "category_id", referencedColumnName = "id"),
      inverseJoinColumns = 
        @JoinColumn(name = "product_attribute_id", referencedColumnName = "id")) 
	@JsonIgnore
    private Set<ProductAttribute> productAttributes = new HashSet<ProductAttribute>();

	public Category() {
		
	}
	

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIconUrl() {
		return this.iconUrl == null ? "" : this.iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<CategoryData> getCategoryData() {
		List<CategoryData> list = new ArrayList<CategoryData>(this.categoryData);
		return list;
	}
	
	public List<CategoryData> getCategoryDataList() {
		List<CategoryData> list = new ArrayList<CategoryData>(this.categoryData);
		return list;
	}

	public void setCategoryData(List<CategoryData> categoryDataSet) {
		if(categoryDataSet != null) {
			Set<CategoryData> set = new HashSet<CategoryData>(categoryDataSet);
			this.categoryData = set;
		}
		
	}

	public CategoryData addCategoryData(CategoryData categoryData) {
		getCategoryDataList().add(categoryData);
		categoryData.setCategory(this);

		return categoryData;
	}

	public CategoryData removeCategoryData(CategoryData categoryData) {
		getCategoryDataList().remove(categoryData);
		categoryData.setCategory(null);

		return categoryData;
	}

	public String getStatusHtml() {
		return this.status ? "<div class=\"tag tag-success\">Hoạt động</div>" : "<div class=\"tag tag-danger\">Ngừng hoạt động</div>";
	}
	
	public CategoryData getDataByLanguage() {
		return this.getDataByLanguage(Constants.DEFAULT_VIETNAMESE_LANGUAGE_ID, true);
	}
	public CategoryData getDataByLanguage(int languageId) {
		return this.getDataByLanguage(languageId, true);
	}
	public CategoryData getDataByLanguage(int languageId, boolean getDefault) {
		
		CategoryData data = this.getCategoryDataList()
										.stream()
										.filter(x -> x.getLanguageId() == languageId)
										.findFirst()
									    .orElse(null);
		if(data == null) {
			if(!getDefault) return new CategoryData();
			else {
				data = this.getCategoryDataList()
						.stream()
						.filter(x -> x.getLanguageId() == Constants.DEFAULT_VIETNAMESE_LANGUAGE_ID)
						.findFirst().orElse(new CategoryData());
			}
		}
		return data;
	}
	
	public int languageExsist(int languageId) {
		return this.checkLanguageExsist(languageId);
	}
	
	private int checkLanguageExsist(int languageId) 
	{
		CategoryData data = this.getCategoryDataList()
				.stream()
				.filter(x -> x.getLanguageId() == languageId)
				.findFirst()
			    .orElse(null);
		if(data == null) return 0;
		return 1;
	}
	
	public boolean isLanguageExsist(int languageId) {
		CategoryData data = this.getCategoryDataList()
				.stream()
				.filter(x -> x.getLanguageId() == languageId)
				.findFirst()
			    .orElse(null);
		
		return data != null;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getParentIds() {
		return this.parentIds == null ? "" : this.parentIds;
	}
	
	public List<String> getCategoryParentListId() {
		if(this.getParentIds().isEmpty()) {
			return new ArrayList<String>();
		} else {
			return new ArrayList<String>(Arrays.asList(this.getParentIds().split(",")));
		}
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	public void setParentIds(List<String> categoryParentIdList) {
		this.parentIds = String.join(",", categoryParentIdList);
	}

	public float getCashbackPercent() {
		return cashbackPercent;
	}

	public void setCashbackPercent(float cashbackPercent) {
		this.cashbackPercent = cashbackPercent;
	}
	
	public List<ProductAttribute> getProductAttributes() {
		List<ProductAttribute> productAttributes = new ArrayList<ProductAttribute>(this.productAttributes);
		return productAttributes;
	}

	public void setProductAttributes(Set<ProductAttribute> productAttributes) {
		this.productAttributes = productAttributes;
	}
	
	public void setProductAttributes(List<ProductAttribute> productAttributes) {
		Set<ProductAttribute> productAttributeSet = new HashSet<ProductAttribute>(productAttributes);
		this.productAttributes = productAttributeSet;
	}
	
	public List<String> getProductAttributeIds() {
		List<String> ids = this.getProductAttributes()
				.stream()
				.map(ProductAttribute::getId)
				.map(Object::toString)
                .collect(Collectors.toList());
		return ids;
	}
	
	public String getProductAttributeStringIds() {
		String ids = StringUtils.join(this.getProductAttributeIds()
				.stream()
				.collect(Collectors.toList()), ',');
		return ids;
	}
	
	public String markStringByLevel() {
		String mark = "";
		for(int i = 0; i < this.level; i++) {
			mark += "--";
		}
		return mark;
	}
}