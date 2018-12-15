/**
 * 
 */
package net.needii.jpa.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import util.Utils;

/**
 * @author kelvin
 *
 */
@Entity
@Table(name = "customers")
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
public class Customer extends BaseUser {
	private static final long serialVersionUID = 1L;

	@Column(name = "auth_type")
	private int authType;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	private String avatar;

	@Column(name = "favorite_product_ids")
	private String favoriteProductIds;

	@Column(name = "needii_cash")
	private float neediiCash;

	private int gender;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login_at")
	private Date lastLoginAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reference_at")
	private Date referenceAt;

	@Column(name = "verify_code")
	private String verifyCode;

	@Column(name = "is_authenticated")
	private boolean isAuthenticated;

	@Column(name = "reference_code")
	private String referenceCode;

	@Column(name = "reference_by")
	private long referenceBy;

	@Column(name = "facebook_uid")
	private String facebookUID;

	@Column(name = "google_uid")
	private String googleUID;

	private boolean status;

	@OneToMany(mappedBy = "customer")
	private Set<CustomerAddress> addresses = new HashSet<CustomerAddress>();

	@OneToMany(mappedBy = "customer")
	private Set<Order> orders;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	private Set<CustomerBalanceHistory> balanceHistories;

	@OneToMany(mappedBy = "customer")
	private Set<PaymentCard> paymentCards;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "customer_category_maps", joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
	private Set<Category> categories;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "customer_favorite_product_maps", joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
	private Set<Product> favoriteProducts;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "customer_product_likes", joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
	private Set<Product> likeProducts;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "customer_supplier_subscribes", joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "supplier_id", referencedColumnName = "id"))
	private Set<Supplier> subscribeSuppliers;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "customer_supplier_likes", joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "supplier_id", referencedColumnName = "id"))
	private Set<Supplier> likeSuppliers;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "customer_supplier_maps", joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "supplier_id", referencedColumnName = "id"))
	private Set<Supplier> suppliers;

	public int getAuthType() {
		return this.authType;
	}

	public void setAuthType(int authType) {
		this.authType = authType;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public String getBirthdayFormatVN() {
		if (this.birthday == null) {
			return "";
		} else {
			return Utils.getDateFormatVN(this.birthday);
		}

	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getFavoriteProductIds() {
		return this.favoriteProductIds;
	}

	public void setFavoriteProductIds(String favoriteProductIds) {
		this.favoriteProductIds = favoriteProductIds;
	}

	public int getGender() {
		return this.gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getLastLoginAt() {
		return this.lastLoginAt;
	}

	public void setLastLoginAt(Date lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getAvatar() {
		return avatar == null ? "" : this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<CustomerAddress> getCustomerAddresses() {
		List<CustomerAddress> addresses = new ArrayList<CustomerAddress>(this.addresses);
		return addresses.stream().filter(x -> !x.getIsDeleted()).collect(Collectors.toList());
	}

	public List<CustomerAddress> getCustomerAddresses(int type, int isDefault) {
		List<CustomerAddress> addresses = this.getCustomerAddresses();
		if (type > 0) {
			addresses = addresses.stream().filter(x -> x.getAddressType() == type).collect(Collectors.toList());
		}
		if (isDefault == 1) {
			addresses = addresses.stream().filter(x -> x.getIsDefault()).collect(Collectors.toList());
		}

		return addresses;
	}

	public void setCustomerAddresses(Set<CustomerAddress> addresses) {
		this.addresses = addresses;
	}

	public void setCustomerAddresses(List<CustomerAddress> addresses) {
		if (addresses != null) {
			Set<CustomerAddress> addressesSet = new HashSet<CustomerAddress>(addresses);
			this.addresses = addressesSet;
		}
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public List<Order> getOrders() {
		List<Order> orders = new ArrayList<Order>(this.orders);
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		if (orders != null) {
			Set<Order> ordersSet = new HashSet<Order>(orders);
			this.orders = ordersSet;
		}
	}

	public List<PaymentCard> getPaymentCards() {
		List<PaymentCard> paymentCards = new ArrayList<PaymentCard>(this.paymentCards);
		return paymentCards;
	}

	public void setPaymentCards(Set<PaymentCard> paymentCards) {
		this.paymentCards = paymentCards;
	}

	public List<Category> getCategories() {
		List<Category> categories = new ArrayList<Category>(this.categories);
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public void setCategories(List<Category> categories) {
		if (categories != null) {
			Set<Category> set = new HashSet<Category>(categories);
			this.categories = set;
		}
	}

	public List<Product> getFavoriteProducts() {
		List<Product> favoriteProducts = new ArrayList<Product>(this.favoriteProducts);
		return favoriteProducts;
	}

	public void setFavoriteProducts(Set<Product> favoriteProducts) {
		this.favoriteProducts = favoriteProducts;
	}

	public void setFavoriteProducts(List<Product> favoriteProducts) {
		if (favoriteProducts != null) {
			Set<Product> set = new HashSet<Product>(favoriteProducts);
			this.favoriteProducts = set;
		}
	}

	public void addFavoriteProducts(List<Product> favoriteProducts) {
		List<Product> currentFavoriteProduct = this.getFavoriteProducts();
		currentFavoriteProduct.addAll(favoriteProducts);
		currentFavoriteProduct = currentFavoriteProduct.stream().filter(Utils.distinctByKey(Product::getId))
				.collect(Collectors.toList());

		this.setFavoriteProducts(currentFavoriteProduct);

	}

	public void addFavoriteProduct(Product product) {
		List<Product> currentFavoriteProduct = this.getFavoriteProducts();
		currentFavoriteProduct.add(product);
		currentFavoriteProduct = currentFavoriteProduct.stream().filter(Utils.distinctByKey(Product::getId))
				.collect(Collectors.toList());

		this.setFavoriteProducts(currentFavoriteProduct);

	}

	public void removeFavoriteProduct(Product product) {
		List<Product> currentFavoriteProduct = this.getFavoriteProducts().stream()
				.filter(x -> x.getId() != product.getId()).collect(Collectors.toList());

		this.setFavoriteProducts(currentFavoriteProduct);

	}

	public void removeFavoriteProducts(List<Long> productIds) {
		List<Product> currentFavoriteProduct = this.getFavoriteProducts().stream()
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

	public List<Supplier> getSubscribeSuppliers() {
		List<Supplier> subscribeSuppliers = new ArrayList<Supplier>(this.subscribeSuppliers);
		return subscribeSuppliers;
	}

	public void setSubscribeSuppliers(Set<Supplier> subscribeSuppliers) {
		this.subscribeSuppliers = subscribeSuppliers;
	}

	public void setSubscribeSuppliers(List<Supplier> subscribeSuppliers) {
		Set<Supplier> set = new HashSet<Supplier>(subscribeSuppliers);
		this.subscribeSuppliers = set;
	}

	public void addSubscribeSuppliers(List<Supplier> subscribeSuppliers) {
		List<Supplier> currentSuppliers = this.getSubscribeSuppliers();
		currentSuppliers.addAll(subscribeSuppliers);
		currentSuppliers = currentSuppliers.stream().filter(Utils.distinctByKey(Supplier::getId))
				.collect(Collectors.toList());

		this.setSubscribeSuppliers(currentSuppliers);
	}

	public void addSubscribeSupplier(Supplier supplier) {
		List<Supplier> currentSuppliers = this.getSubscribeSuppliers();
		currentSuppliers.add(supplier);
		currentSuppliers = currentSuppliers.stream().filter(Utils.distinctByKey(Supplier::getId))
				.collect(Collectors.toList());

		this.setSubscribeSuppliers(currentSuppliers);
	}

	public void removeSubscribeSupplier(Supplier supplier) {
		List<Supplier> currentSubscribeSupplier = this.getSubscribeSuppliers().stream()
				.filter(x -> x.getId() != supplier.getId()).collect(Collectors.toList());

		this.setSubscribeSuppliers(currentSubscribeSupplier);
	}

	public List<Supplier> getLikeSuppliers() {
		List<Supplier> subscribeSuppliers = new ArrayList<Supplier>(this.likeSuppliers);
		return subscribeSuppliers;
	}

	public void setLikeSuppliers(Set<Supplier> likeSuppliers) {
		this.likeSuppliers = likeSuppliers;
	}

	public void setLikeSuppliers(List<Supplier> likeSuppliers) {
		Set<Supplier> set = new HashSet<Supplier>(likeSuppliers);
		this.likeSuppliers = set;
	}

	public void likeSupplier(Supplier supplier) {
		List<Supplier> currentSuppliers = this.getLikeSuppliers();
		currentSuppliers.add(supplier);
		currentSuppliers = currentSuppliers.stream().filter(Utils.distinctByKey(Supplier::getId))
				.collect(Collectors.toList());

		this.setLikeSuppliers(currentSuppliers);
	}

	public void unlikeSupplier(Supplier supplier) {
		List<Supplier> currentSuppliers = this.getLikeSuppliers().stream().filter(x -> x.getId() != supplier.getId())
				.collect(Collectors.toList());

		this.setLikeSuppliers(currentSuppliers);
	}

	public List<Product> getLikeProducts() {
		List<Product> likeProducts = new ArrayList<Product>(this.likeProducts);
		return likeProducts;
	}

	public void setLikeProducts(Set<Product> likeProducts) {
		this.likeProducts = likeProducts;
	}

	public void setLikeProducts(List<Product> likeProducts) {
		Set<Product> set = new HashSet<Product>(likeProducts);
		this.likeProducts = set;
	}

	public void likeProduct(Product product) {
		List<Product> currentProducts = this.getLikeProducts();
		currentProducts.add(product);
		currentProducts = currentProducts.stream().filter(Utils.distinctByKey(Product::getId))
				.collect(Collectors.toList());

		this.setLikeProducts(currentProducts);
	}

	public void unlikeProduct(Product product) {
		List<Product> currentProducts = this.getLikeProducts().stream().filter(x -> x.getId() != product.getId())
				.collect(Collectors.toList());

		this.setLikeProducts(currentProducts);
	}

	public List<CustomerBalanceHistory> getBalanceHistories() {
		List<CustomerBalanceHistory> balanceHistories = new ArrayList<CustomerBalanceHistory>(this.balanceHistories);
		return balanceHistories;
	}

	public void setBalanceHistories(Set<CustomerBalanceHistory> balanceHistories) {
		this.balanceHistories = balanceHistories;
	}

	public void setBalanceHistories(List<CustomerBalanceHistory> balanceHistories) {
		if (balanceHistories != null) {
			Set<CustomerBalanceHistory> set = new HashSet<CustomerBalanceHistory>(balanceHistories);
			this.balanceHistories = set;
		}
	}

	public void addBalanceHistory(CustomerBalanceHistory history) {
		List<CustomerBalanceHistory> histories = this.getBalanceHistories();
		histories.add(history);

		this.setBalanceHistories(histories);
	}

	public Set<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Set<Supplier> suppliers) {
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
}
