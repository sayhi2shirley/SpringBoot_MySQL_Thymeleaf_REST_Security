package shangrila.council.app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import shangrila.council.app.config.UserSurveyService;
import shangrila.council.app.model.ResidentUser;
import shangrila.council.app.repository.ResidentUserRepository;
import shangrila.council.app.web.dto.ResidentUserRegDTO;

@Service
public class ResidentUserServiceImpl implements ResidentUserService{

	private ResidentUserRepository residentUserRepository;	
	
	/* SHA-1 algorithm will be used internally to encode the password. */
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public ResidentUserServiceImpl(ResidentUserRepository residentUserRepository) {
		super();
		System.out.println("Service: ResidentUserServiceImpl\n");
		this.residentUserRepository = residentUserRepository;
	}

	@Override
	public ResidentUser save(ResidentUserRegDTO registrationDTO) {
		System.out.println("Service: save\n");
		ResidentUser residentUser = new ResidentUser(registrationDTO.getFullName(),
				registrationDTO.getDateOfBirth(), registrationDTO.getHomeAddress(),
				registrationDTO.getEmail(), 
				passwordEncoder.encode(registrationDTO.getPassword()), 
				registrationDTO.getSniNumber(),registrationDTO.getAdmin());

		return residentUserRepository.save(residentUser);
	}

	/* Backend Implementation for Login */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println(username);
		ResidentUser residentUser = residentUserRepository.findByEmail(username);
	
		if (residentUser == null) {
			throw new UsernameNotFoundException("Invalid Username or Password");
		}
		
		System.out.println("Service 1: loadUserByUsername\n" + residentUser.getAdmin() + " " +
				residentUser.getEmail());
		/* For the interface UserDetails, return the User object */
		//return new org.springframework.security.core.userdetails.User(residentUser.getEmail(),
			//	residentUser.getPassword(), getAuthorities());
		return new UserSurveyService(residentUser);
	} 
	
	/* Adding/Setting the authorities for the Resident Users*/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

	    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	    ResidentUser residentUser = residentUserRepository.findByEmail("admin@shangrila.gov.uk");
	    System.out.println("Service: getAuthorities\n");
	    
	    /* All users will have only one Role (either Admin or USER) */
	    if (residentUser == null) {
	    	 authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        
	    return authorities;
	}
	

}
