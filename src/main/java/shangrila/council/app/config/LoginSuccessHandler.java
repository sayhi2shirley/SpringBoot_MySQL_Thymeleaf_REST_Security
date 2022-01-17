package shangrila.council.app.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {

		UserSurveyService userDetails = (UserSurveyService) authentication.getPrincipal();

		String redirectURL = request.getContextPath();

		/*
 	     The below code was used to verify the authorisation of the logged in user. 
 	      Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
 	      authorities.forEach(auth-> System.out.println(auth.getAuthority())); 

 	      super.onAuthenticationSuccess(request, response, authentication);
		 */	

		if (userDetails.hasRole(1)) {
			redirectURL = "admin";
		} else if (userDetails.hasRole(0)) {
			redirectURL = "user";
		}

		response.sendRedirect(redirectURL);

	}

}
