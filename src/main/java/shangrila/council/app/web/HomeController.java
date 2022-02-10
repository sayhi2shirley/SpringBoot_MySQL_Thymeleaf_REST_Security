package shangrila.council.app.web;


import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import shangrila.council.app.model.Options;
import shangrila.council.app.service.OptionService;
import shangrila.council.app.service.QuestionService;
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
		return "home"; 
	}

	@RequestMapping(value = {"/GetAllQuestions"})
	public @ResponseBody Object listAllJson(Model model){
		Object o=qs.getAllQuestions();
		return o;
	}

	@RequestMapping(value = {"/GetQuestionOptions"})
	public @ResponseBody List<Options> listAllJson(@RequestParam Integer id){
		List<Options> opt = qs.findById(id).get().getOptions();
		return opt;
	}

	@RequestMapping(value = { "/GetQuestionResponse"})
	public @ResponseBody List<Options> listAllJsonResponse(@RequestParam Integer id){
		List<Options> opt = qs.findById(id).get().getOptions();;
		return opt;
	}

	@GetMapping(value = {"/user"}) 
	public String userDashboard(Principal principal, Model model) {
		model.addAttribute("userName", principal.getName());
		return "user";
	}


	@GetMapping(value = {"/admin"}) 
	public String adminDashboard(Principal principal, Model model) {
		model.addAttribute("userName", principal.getName()); 
		return "admin"; 
	}

}
