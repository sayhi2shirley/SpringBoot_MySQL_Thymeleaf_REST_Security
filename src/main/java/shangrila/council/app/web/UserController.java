package shangrila.council.app.web;

import java.security.Principal;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import shangrila.council.app.model.Options;
import shangrila.council.app.model.ResidentUser;
import shangrila.council.app.model.Response;
import shangrila.council.app.service.OptionService;
import shangrila.council.app.service.QuestionService;
import shangrila.council.app.service.ResponseService;

@Controller
@RequestMapping(value = {"/user"})
public class UserController {

	  @Autowired
	  QuestionService qs;

	  @Autowired
	  OptionService os;
	    
	  @Autowired 
	  ResponseService rs;
	  
	  @RequestMapping(value = {"/survey"})
	    public String takeSurvey(Principal principal, Model model){
		  model.addAttribute("userName", principal.getName());
		  model.addAttribute("listAllQuestions", qs.getAllQuestions());
		  model.addAttribute("listOptions", os.getAllOptions());
		  
	        return "survey";
	    } 
	
	  @RequestMapping(value = {"/save-survey"})
	    public String saveSurvey(Response response, Model model){
		  model.addAttribute("listOptions", os.getAllOptions());
		 
		  Options opt = os.findById(response.getChosenOptionID()).get();
		  rs.getAllResponses();
		  if (opt.getOptionID() == response.getChosenOptionID()) {
			  int count = response.getCount();
			  count++;
			  response.setCount(count);
		  }
		 
		  rs.save(response);
		  System.out.println(" saveSurvey : userID" + response.getUserID());
	        return "redirect:/user?thankyou";
	    } 
	
	  /*
		private int ResponseID;

		@Column(name="userid", nullable=false)
		private String UserID;
		
		@Column(name="optionid", nullable=false)
	    private int ChosenOptionID;
		
		@Column(name="questionid", nullable=false)
	    private int QuestionID;
	    */
}
