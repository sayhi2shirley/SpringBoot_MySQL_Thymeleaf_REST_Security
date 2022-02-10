package shangrila.council.app.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import shangrila.council.app.model.ResidentUser;
import shangrila.council.app.web.dto.ResidentUserRegDTO;

public interface ResidentUserService extends UserDetailsService {
    /* Save() method will save the Resident User's registration data. */ 
	ResidentUser save(ResidentUserRegDTO registrationDTO);

	Collection<? extends GrantedAuthority> getAuthorities();
	
}
