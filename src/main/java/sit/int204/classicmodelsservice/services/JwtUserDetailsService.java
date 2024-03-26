package sit.int204.classicmodelsservice.services;

import com.sun.tools.jconsole.JConsoleContext;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int204.classicmodelsservice.entities.Customer;
import sit.int204.classicmodelsservice.model.AuthUser;
import sit.int204.classicmodelsservice.repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findCustomerByFullName(username);
        if (customer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, username+ " does not exist !!");
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        GrantedAuthority grantedAuthority = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return customer.getRole();
            }
        };
        roles.add(grantedAuthority);
        return new AuthUser(username, customer.getPassword(), roles);
    }
}
