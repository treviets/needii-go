/**
 * 
 */
package net.needii.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import net.needii.jpa.entity.BaseUser;
import net.needii.jpa.entity.Category;
import net.needii.jpa.entity.Customer;
import net.needii.jpa.entity.Product;
import net.needii.jpa.entity.Supplier;
import net.needii.jpa.entity.security.NeediiUser;
import net.needii.repository.ClientRepository;
import net.needii.repository.CustomerRepository;
import net.needii.repository.SupplierRepository;

/**
 * @author kelvin
 *
 */
@Component
public class NeediiUserDetails implements UserDetailsService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public NeediiUser loadUserByUsername(String s) throws UsernameNotFoundException {
		NeediiUser userDetails;
		String[] sTemp = s.split(",");
		BaseUser user = null; 
		boolean isSupplier = false;
		List<GrantedAuthority> authorities = new ArrayList<>();
		List<Integer> categoryIds = new ArrayList<Integer>();
		List<Long> subscribeSupplierIds = new ArrayList<>();
		List<Long> likeSupplierIds = new ArrayList<>();
		List<Long> likeProductIds = new ArrayList<Long>();
		List<Long> supplierIds = new ArrayList<>();
		if(sTemp.length < 2) {
			user = customerRepository.findByPhone(s);
			Customer customer = customerRepository.findByPhone(s);
			authorities.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
			if(supplierRepository.findByPhone(s) != null) {
				authorities.add(new SimpleGrantedAuthority("ROLE_SUPPLIER"));
				supplierIds = customer.getSuppliers().stream().map(Supplier::getId).collect(Collectors.toList());
				isSupplier = true;
			}
			categoryIds = customer.getCategories().stream().map(Category::getId).collect(Collectors.toList());
			subscribeSupplierIds = customer.getSubscribeSuppliers().stream().map(Supplier::getId).collect(Collectors.toList());
			likeSupplierIds = customer.getLikeSuppliers().stream().map(Supplier::getId).collect(Collectors.toList());
			likeProductIds = customer.getLikeProducts().stream().map(Product::getId).collect(Collectors.toList());
		} else {
			user = clientRepository.findByUsername(sTemp[0]);
			authorities.add(new SimpleGrantedAuthority("ROLE_GUEST"));
		}
		
		if(user == null) {
            throw new UsernameNotFoundException(String.format("The user doesn't exist"));
        }
		
		userDetails = new NeediiUser(s, user.getPassword(), user.getId(), user.getPhone(), user.getEmail(), categoryIds, subscribeSupplierIds, likeSupplierIds, supplierIds, likeProductIds, isSupplier, authorities);

		return userDetails;
	}
}