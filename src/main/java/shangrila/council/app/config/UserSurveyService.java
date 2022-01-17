package shangrila.council.app.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import shangrila.council.app.model.ResidentUser;


public class UserSurveyService implements UserDetails{
	private static final long serialVersionUID=1L	;
	private ResidentUser user;

	public UserSurveyService(ResidentUser user) {
		System.out.println("UserSurveyService: Constructor\n");
		this.user = user;
	}


	public boolean hasRole(int roleName) {

		System.out.println("UserSurveyService: hasRole\n");
		return user.hasRole(roleName);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		System.out.println("UserSurveyService: getAuthorities\n");
		/* All users will have only one Role (either Admin or USER) */
		if (this.hasRole(0)) {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		} else {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		return authorities;
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getFullName();
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
