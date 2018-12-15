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
import net.needii.jpa.entity.security.AuthenticationUser;
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
	public AuthenticationUser loadUserByUsername(String s) throws UsernameNotFoundException {
		AuthenticationUser userDetails;
		String[] sTemp = s.split(",");
		BaseUser user = null; 
		List<GrantedAuthority> authorities = new ArrayList<>();
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
		
		userDetails = new AuthenticationUser(s, user.getPassword(), true, true, true, true, authorities);

		return userDetails;
	}
}