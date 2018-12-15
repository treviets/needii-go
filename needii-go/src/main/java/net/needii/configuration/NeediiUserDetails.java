/**
 * 
 */
package net.needii.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import net.needii.jpa.entity.BaseUser;
import net.needii.jpa.entity.security.NeediiUser;
import net.needii.repository.ClientRepository;
import net.needii.repository.CustomerRepository;

/**
 * @author Vincent
 *
 */
@Component
public class NeediiUserDetails implements UserDetailsService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
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
			authorities.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
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