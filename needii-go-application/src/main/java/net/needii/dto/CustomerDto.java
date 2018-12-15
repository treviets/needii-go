/**
 * 
 */
package net.needii.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.needii.jpa.entity.PaymentCard;
import util.Utils;

/**
 * @author kelvin
 *
 */
public class CustomerDto {
	
	private Long id;
	
	private String fullName;

	@JsonIgnore
	private String password;

	private String phone;
	
	private String email;
	
	@JsonProperty("auth_type")
	private int authType;

	private Date birthday;

	private String avatar;

	@JsonProperty("favorite_product_ids")
	private String favoriteProductIds;

	@JsonProperty("needii_cash")
	private float neediiCash;

	private int gender;

	@JsonProperty("last_login_at")
	private Date lastLoginAt;

	@JsonProperty("reference_at")
	private Date referenceAt;

	@JsonProperty("verify_code")
	private String verifyCode;

	@JsonProperty("is_authenticated")
	private boolean isAuthenticated;

	@JsonProperty("reference_code")
	private String referenceCode;

	@JsonProperty("reference_by")
	private long referenceBy;

	@JsonProperty("facebook_uid")
	private String facebookUID;

	@JsonProperty("google_uid")
	private String googleUID;

	private boolean status;

	
	private Set<CustomerAddressDto> addresses;

	private Set<OrderDto> orders;

	private Set<CustomerBalanceHistoryDto> balanceHistories;

	private Set<PaymentCard> paymentCards;;

	private Set<CategoryDto> categories;

	private Set<ProductDto> favoriteProducts;

	private Set<ProductDto> likeProducts;

	private Set<SupplierDto> subscribeSuppliers;

	private Set<SupplierDto> likeSuppliers;

	private Set<SupplierDto> suppliers;

	
	public List<CustomerAddressDto> getCustomerAddresses() {
		List<CustomerAddressDto> addresses = new ArrayList<CustomerAddressDto>(this.addresses);
		return addresses.stream().filter(x -> !x.getIsDeleted()).collect(Collectors.toList());
	}

	public List<CustomerAddressDto> getCustomerAddresses(int type, int isDefault) {
		List<CustomerAddressDto> addresses = this.getCustomerAddresses();
		if (type > 0) {
			addresses = addresses.stream().filter(x -> x.getAddressType() == type).collect(Collectors.toList());
		}
		if (isDefault == 1) {
			addresses = addresses.stream().filter(x -> x.getIsDefault()).collect(Collectors.toList());
		}

		return addresses;
	}

	
	public void setCustomerAddresses(List<CustomerAddressDto> addresses) {
		if (addresses != null) {
			Set<CustomerAddressDto> addressesSet = new HashSet<CustomerAddressDto>(addresses);
			this.addresses = addressesSet;
		}
	}

	
	public List<PaymentCard> getPaymentCards() {
		List<PaymentCard> paymentCards = new ArrayList<PaymentCard>(this.paymentCards);
		return paymentCards;
	}

	public void setPaymentCards(Set<PaymentCard> paymentCards) {
		this.paymentCards = paymentCards;
	}

	public List<CategoryDto> getCategories() {
		List<CategoryDto> categories = new ArrayList<CategoryDto>(this.categories);
		return categories;
	}

	public void setCategories(Set<CategoryDto> categories) {
		this.categories = categories;
	}

	public void setCategories(List<CategoryDto> categories) {
		Set<CategoryDto> set = new HashSet<CategoryDto>(categories);
		this.categories = set;
	}

	public List<ProductDto> getFavoriteProducts() {
		List<ProductDto> favoriteProducts = new ArrayList<ProductDto>(this.favoriteProducts);
		return favoriteProducts;
	}

	public void setFavoriteProducts(Set<ProductDto> favoriteProducts) {
		this.favoriteProducts = favoriteProducts;
	}

	public void setFavoriteProducts(List<ProductDto> favoriteProducts) {
		if (favoriteProducts != null) {
			Set<ProductDto> set = new HashSet<ProductDto>(favoriteProducts);
			this.favoriteProducts = set;
		}
	}

	public void addFavoriteProducts(List<ProductDto> favoriteProducts) {
		List<ProductDto> currentFavoriteProduct = this.getFavoriteProducts();
		currentFavoriteProduct.addAll(favoriteProducts);
		currentFavoriteProduct = currentFavoriteProduct.stream().filter(Utils.distinctByKey(ProductDto::getId))
				.collect(Collectors.toList());

		this.setFavoriteProducts(currentFavoriteProduct);

	}

	public void addFavoriteProduct(ProductDto product) {
		List<ProductDto> currentFavoriteProduct = this.getFavoriteProducts();
		currentFavoriteProduct.add(product);
		currentFavoriteProduct = currentFavoriteProduct.stream().filter(Utils.distinctByKey(ProductDto::getId))
				.collect(Collectors.toList());

		this.setFavoriteProducts(currentFavoriteProduct);

	}

	public void removeFavoriteProduct(ProductDto product) {
		List<ProductDto> currentFavoriteProduct = this.getFavoriteProducts().stream()
				.filter(x -> x.getId() != product.getId()).collect(Collectors.toList());

		this.setFavoriteProducts(currentFavoriteProduct);

	}

	public void removeFavoriteProducts(List<Long> productIds) {
		List<ProductDto> currentFavoriteProduct = this.getFavoriteProducts().stream()
				.filter(x -> !productIds.contains(x.getId())).collect(Collectors.toList());

		this.setFavoriteProducts(currentFavoriteProduct);

	}

	public float getNeediiCash() {
		return neediiCash;
	}

	public void setNeediiCash(float neediiCash) {
		this.neediiCash = neediiCash;
	}

	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

	public long getReferenceBy() {
		return referenceBy;
	}

	public void setReferenceBy(long referenceBy) {
		this.referenceBy = referenceBy;
	}

	public String getFacebookUID() {
		return facebookUID == null ? "" : this.facebookUID;
	}

	public void setFacebookUID(String facebookUID) {
		this.facebookUID = facebookUID;
	}

	public String getGoogleUID() {
		return googleUID == null ? "" : this.googleUID;
	}

	public void setGoogleUID(String googleUID) {
		this.googleUID = googleUID;
	}

	public List<SupplierDto> getSubscribeSuppliers() {
		List<SupplierDto> subscribeSuppliers = new ArrayList<SupplierDto>(this.subscribeSuppliers);
		return subscribeSuppliers;
	}

	public void setSubscribeSuppliers(Set<SupplierDto> subscribeSuppliers) {
		this.subscribeSuppliers = subscribeSuppliers;
	}

	public void setSubscribeSuppliers(List<SupplierDto> subscribeSuppliers) {
		Set<SupplierDto> set = new HashSet<SupplierDto>(subscribeSuppliers);
		this.subscribeSuppliers = set;
	}

	public void addSubscribeSuppliers(List<SupplierDto> subscribeSuppliers) {
		List<SupplierDto> currentSuppliers = this.getSubscribeSuppliers();
		currentSuppliers.addAll(subscribeSuppliers);
		currentSuppliers = currentSuppliers.stream().filter(Utils.distinctByKey(SupplierDto::getId))
				.collect(Collectors.toList());

		this.setSubscribeSuppliers(currentSuppliers);
	}

	public void addSubscribeSupplier(SupplierDto supplier) {
		List<SupplierDto> currentSuppliers = this.getSubscribeSuppliers();
		currentSuppliers.add(supplier);
		currentSuppliers = currentSuppliers.stream().filter(Utils.distinctByKey(SupplierDto::getId))
				.collect(Collectors.toList());

		this.setSubscribeSuppliers(currentSuppliers);
	}

	public void removeSubscribeSupplier(SupplierDto supplier) {
		List<SupplierDto> currentSubscribeSupplier = this.getSubscribeSuppliers().stream()
				.filter(x -> x.getId() != supplier.getId()).collect(Collectors.toList());

		this.setSubscribeSuppliers(currentSubscribeSupplier);
	}

	public List<SupplierDto> getLikeSuppliers() {
		List<SupplierDto> subscribeSuppliers = new ArrayList<SupplierDto>(this.likeSuppliers);
		return subscribeSuppliers;
	}

	public void setLikeSuppliers(Set<SupplierDto> likeSuppliers) {
		this.likeSuppliers = likeSuppliers;
	}

	public void setLikeSuppliers(List<SupplierDto> likeSuppliers) {
		Set<SupplierDto> set = new HashSet<SupplierDto>(likeSuppliers);
		this.likeSuppliers = set;
	}

	public void likeSupplier(SupplierDto supplier) {
		List<SupplierDto> currentSuppliers = this.getLikeSuppliers();
		currentSuppliers.add(supplier);
		currentSuppliers = currentSuppliers.stream().filter(Utils.distinctByKey(SupplierDto::getId))
				.collect(Collectors.toList());

		this.setLikeSuppliers(currentSuppliers);
	}

	public void unlikeSupplier(SupplierDto supplier) {
		List<SupplierDto> currentSuppliers = this.getLikeSuppliers().stream().filter(x -> x.getId() != supplier.getId())
				.collect(Collectors.toList());

		this.setLikeSuppliers(currentSuppliers);
	}

	public List<ProductDto> getLikeProducts() {
		List<ProductDto> likeProducts = new ArrayList<ProductDto>(this.likeProducts);
		return likeProducts;
	}

	public void setLikeProducts(Set<ProductDto> likeProducts) {
		this.likeProducts = likeProducts;
	}

	public void setLikeProducts(List<ProductDto> likeProducts) {
		Set<ProductDto> set = new HashSet<ProductDto>(likeProducts);
		this.likeProducts = set;
	}

	public void likeProduct(ProductDto product) {
		List<ProductDto> currentProducts = this.getLikeProducts();
		currentProducts.add(product);
		currentProducts = currentProducts.stream().filter(Utils.distinctByKey(ProductDto::getId))
				.collect(Collectors.toList());

		this.setLikeProducts(currentProducts);
	}

	public void unlikeProduct(ProductDto product) {
		List<ProductDto> currentProducts = this.getLikeProducts().stream().filter(x -> x.getId() != product.getId())
				.collect(Collectors.toList());

		this.setLikeProducts(currentProducts);
	}

	public List<CustomerBalanceHistoryDto> getBalanceHistories() {
		List<CustomerBalanceHistoryDto> balanceHistories = new ArrayList<CustomerBalanceHistoryDto>(this.balanceHistories);
		return balanceHistories;
	}

	public void setBalanceHistories(Set<CustomerBalanceHistoryDto> balanceHistories) {
		this.balanceHistories = balanceHistories;
	}

	public void setBalanceHistories(List<CustomerBalanceHistoryDto> balanceHistories) {
		if (balanceHistories != null) {
			Set<CustomerBalanceHistoryDto> set = new HashSet<CustomerBalanceHistoryDto>(balanceHistories);
			this.balanceHistories = set;
		}
	}

	public void addBalanceHistory(CustomerBalanceHistoryDto history) {
		List<CustomerBalanceHistoryDto> histories = this.getBalanceHistories();
		histories.add(history);

		this.setBalanceHistories(histories);
	}

	public Set<SupplierDto> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Set<SupplierDto> suppliers) {
		this.suppliers = suppliers;
	}

	public boolean isSupplier() {
		return !this.getSuppliers().isEmpty();
	}

	public Date getReferenceAt() {
		return referenceAt;
	}

	public String getReferenceAtStringFormat() {
		return Utils.getDateFormatVNEmptyIfNull(this.referenceAt);
	}

	public void setReferenceAt(Date referenceAt) {
		this.referenceAt = referenceAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAuthType() {
		return authType;
	}

	public void setAuthType(int authType) {
		this.authType = authType;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getFavoriteProductIds() {
		return favoriteProductIds;
	}

	public void setFavoriteProductIds(String favoriteProductIds) {
		this.favoriteProductIds = favoriteProductIds;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getLastLoginAt() {
		return lastLoginAt;
	}

	public void setLastLoginAt(Date lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<CustomerAddressDto> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<CustomerAddressDto> addresses) {
		this.addresses = addresses;
	}

	public Set<OrderDto> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderDto> orders) {
		this.orders = orders;
	}
	
}
