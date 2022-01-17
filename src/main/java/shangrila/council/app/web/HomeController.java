package shangrila.council.app.web;


import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.WriterException;

import shangrila.council.app.model.Options;
import shangrila.council.app.model.ResidentUser;
import shangrila.council.app.service.OptionService;
import shangrila.council.app.service.QuestionService;
import shangrila.council.app.service.ResidentUserService;
import shangrila.council.app.service.ResponseService;


@Controller
public class HomeController {
	  @Autowired
	  QuestionService qs;

	  @Autowired
	  OptionService os;
	    
	  @Autowired
	  ResponseService rs;
	@GetMapping(value = {"/login"})
	public String login() {
		return "login";
	}	
	
	@GetMapping(value = {"/","/home"}) 
	public String shangriLaHome() {
		//model.addAttribute("title", "E-Survey Shangri-La City Council"); 
		return "home"; 
	}
	
	  @RequestMapping(value = {"/GetAllQuestions"})
	    public @ResponseBody Object listAllJson(Model model){
	        Object o=qs.getAllQuestions();
	        return o;
	    }
	  
	  @RequestMapping(value = {"/GetQuestionOptions"})
	    public @ResponseBody List<Options> listAllJson(@RequestParam Integer id){
	        System.out.println("listAllJson " + id);
		 // Object o=qs.findById(id);
		  List<Options> opt = qs.findById(id).get().getOptions();
	        return opt;
	    }
	  
	  @RequestMapping(value = { "/GetQuestionResponse"})
	    public @ResponseBody List<Options> listAllJsonResponse(@RequestParam Integer id){
	        System.out.println("listAllJson " + id);
		 // Object o=qs.findById(id);
		  List<Options> opt = qs.findById(id).get().getOptions();;
	        return opt;
	    }
	  
	@GetMapping(value = {"/user"}) 
	public String userDashboard(Principal principal, Model model) {

		model.addAttribute("userName", principal.getName());
		String userName = principal.getName();
		System.out.println("Auth Controller : userDashboard  " + principal.getName());
		return "user";
	}

	
	@GetMapping(value = {"/admin"}) 
	public String adminDashboard(Principal principal, Model model) {
		
		model.addAttribute("userName", principal.getName());
		String userName = principal.getName();
		//model.addAttribute("title", "E-Survey Shangri-La City Council"); 
		return "admin"; 
	}
	
    
    @Autowired 
    private ResidentUserService residentService;
    
    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String getQRCode(Model model) throws Exception{
    	System.out.println("Controller: getQRCode 1\n");
    	/*response.setContentType("image/png");
    	
    	OutputStream outputStream = response.getOutputStream();
    	outputStream.write(qrcode.getQRCodeImage(email, 200, 200));
    	outputStream.flush();
    	outputStream.close(); */
    	System.out.println("Controller: getQRCode 2\n");
    	return "index";
    }
    
	
}
