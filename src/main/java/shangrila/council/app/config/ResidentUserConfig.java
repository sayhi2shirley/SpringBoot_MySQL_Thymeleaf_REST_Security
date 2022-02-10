package shangrila.council.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import shangrila.council.app.service.ResidentUserService;

/*
 * Enables Spring Security Web security support and 
 * provides MVC Integration.
 */
@Configuration
@EnableWebSecurity
public class ResidentUserConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ResidentUserService residentUserService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(residentUserService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}


	@Override
	protected void configure(HttpSecurity security) throws Exception {
		/* Default Configurations Disabled. */
		security.httpBasic().disable();

		/* Provide Access to the different URLs, 
		 * by starting more restricted to less restricted.
		 */
		security.csrf().disable()
		.authorizeRequests()
		.antMatchers("/registration").permitAll()
		.antMatchers("/process").permitAll()
		.antMatchers("/").permitAll()
		.antMatchers("/user/**", "/user/survey").hasAuthority("ROLE_USER")
		.antMatchers("/admin", "/questions", "/questions/create", 
				"/questions/listallquestions", "/questions/edit/**",
				"/questions/save", "/questions/addoption/**", 
				"/questions/optsave", "/questions/getqoptions/**",
				"questions/editoption/**", "/questions/listoptions",
				"/questions/viewresponse")
		.hasAuthority("ROLE_ADMIN")
		.and().formLogin()
		.loginPage("/login")
		.usernameParameter("email")
		.successHandler(loginSuccessHandler)
		.permitAll()
		.and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
		.permitAll();

	}

	@Autowired private LoginSuccessHandler loginSuccessHandler;
}

