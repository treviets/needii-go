/**
 * 
 */
package net.needii.jpa.entity.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class NeediiUser extends User {

   private static final long serialVersionUID = -3531439484732724601L;
   
   private Long id;
   private String phone;
   private String email;
   private List<Integer> categoryIds;
   private List<Long> subscribeSupplierIds;
   private List<Long> likeSupplierIds;
   private List<Long> supplierIds;
   private List<Long> likeProductIds;
   private boolean isSupplier;
   
@SuppressWarnings("unchecked")
public NeediiUser(
		   String username, 
		   String password, 
		   Long id, 
		   String phone, 
		   String email, 
		   List<Integer> categoryIds, 
		   List<Long> subscribeSupplierIds, 
		   List<Long> likeSupplierIds, 
		   List<Long> supplierIds,
		   List<Long> likeProductIds, 
		   boolean isSupplier, 
		   @SuppressWarnings("rawtypes") Collection authorities){
	   super(username, password, true, true, true, true, authorities);
	   
	   this.setId(id);
	   this.phone = phone;
	   this.email = email;
	   this.setCategoryIds(categoryIds);
	   this.setSubscribeSupplierIds(subscribeSupplierIds);
	   this.setLikeSupplierIds(likeSupplierIds);
	   this.setSupplierIds(supplierIds);
	   this.setLikeProductIds(likeProductIds);
	   this.isSupplier = isSupplier;
   }

   public Long getId() {
	   return id;
   }
   
   public void setId(Long id) {
	   this.id = id;
   }
	
   public static long getSerialversionuid() {
      return serialVersionUID;
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

	public boolean isSupplier() {
		return isSupplier;
	}

	public void setSupplier(boolean isSupplier) {
		this.isSupplier = isSupplier;
	}

	public List<Integer> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<Integer> categoryIds) {
		this.categoryIds = categoryIds;
	}

	public List<Long> getSubscribeSupplierIds() {
		return subscribeSupplierIds;
	}

	public void setSubscribeSupplierIds(List<Long> subscribeSupplierIds) {
		this.subscribeSupplierIds = subscribeSupplierIds;
	}

	public List<Long> getLikeSupplierIds() {
		return likeSupplierIds;
	}

	public void setLikeSupplierIds(List<Long> likeSupplierIds) {
		this.likeSupplierIds = likeSupplierIds;
	}

	public List<Long> getLikeProductIds() {
		return likeProductIds;
	}

	public void setLikeProductIds(List<Long> likeProductIds) {
		this.likeProductIds = likeProductIds;
	}

	public boolean isGuest(){
        AuthRoleEnum role = this.getAuthRoleEnum();
        return role == AuthRoleEnum.GUEST;
    }
    public boolean isCustomer(){
        AuthRoleEnum role = this.getAuthRoleEnum();
        return role == AuthRoleEnum.CUSTOMER;
    }
    public boolean isNeediiSupplier(){
        AuthRoleEnum role = this.getAuthRoleEnum();
        return role == AuthRoleEnum.SUPPLIER;
    }

    public AuthRoleEnum getAuthRoleEnum(){
        List<GrantedAuthority> roles = new ArrayList<>(this.getAuthorities());
        switch(roles.get(0).toString()) {
            case "ROLE_GUEST":
                return AuthRoleEnum.GUEST;
            case "ROLE_CUSTOMER":
                return AuthRoleEnum.CUSTOMER;
            case "ROLE_SUPPLIER":
                return AuthRoleEnum.SUPPLIER;
            case "ROLE_NEEDIIGO":
                return AuthRoleEnum.NEEDIIGO;
            default:
                return AuthRoleEnum.GUEST;
        }
    }

	public List<Long> getSupplierIds() {
		return supplierIds;
	}

	public void setSupplierIds(List<Long> supplierIds) {
		this.supplierIds = supplierIds;
	}
}