package shangrila.council.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import shangrila.council.app.model.Questions;
import shangrila.council.app.model.Options;
import shangrila.council.app.service.OptionService;
import shangrila.council.app.service.QuestionService;
import shangrila.council.app.service.ResponseService;

@Controller
@RequestMapping(value = {"/questions"})
public class AdminController {
	@Autowired
	QuestionService qs;

	@Autowired
	OptionService os;

	@Autowired
	ResponseService rs;

	@RequestMapping(value = {"/viewresponse"})
	public String viewAllResponse(Model model) {
		model.addAttribute("listResponse", rs.getAllResponses());
		model.addAttribute("listAllOptions", os.getAllOptions());
		return "viewresponse";
	} 

	@RequestMapping(value = {"/listoptions"})
	public String listAllOptions(Model model) {
		model.addAttribute("listOptions", os.getAllOptions());
		return "listoptions";
	} 

	@RequestMapping(value = "/deleteOpt/{id}")
	public String deleteOpt(@PathVariable(value="id") Integer optionId){
		os.deleteById(optionId);
		return "redirect:../listoptions";	
	}

	@RequestMapping(value = "/addoption/{id}")
	public String addopt(@PathVariable(value="id") Integer optionId, Model model) {
		model.addAttribute("listAllQuestions", qs.getAllQuestions());
		model.addAttribute("options", os.findById(optionId));
		return "newoption";    
	} 

	@RequestMapping(value = "/editoption/{id}")
	public String editopt(@PathVariable(value="id") Integer optionId, Model model) {
		model.addAttribute("options", os.findById(optionId));
		model.addAttribute("listAllQuestions", qs.getAllQuestions());
		return "option_form";    
	} 

	@RequestMapping(value = "/create")
	public String create(Model model) {	
		return "create";    
	} 

	@RequestMapping(value = "/optsave", method = RequestMethod.POST)
	public String saveoption(Options options, Model model) {
		model.addAttribute("questionid", options.getQuestion().getQuestionID());
		model.addAttribute("listOptions", options.getQuestion().getOptions());
		os.save(options); 
		model.addAttribute("listAllOptions", os.getAllOptions());
		return "listoptions";
	}

	@RequestMapping(value = "/getqoptions/{id}")
	public String getQuestionOptions(@PathVariable(value="id") Integer questionId, 
			Model model) {
		model.addAttribute("listOptions", qs.findById(questionId).get().getOptions());
		return "getqoptions";
	} 

	@RequestMapping(value = {"/listallquestions"})
	public String listAll(Model model){
		model.addAttribute("listAllQuestions", qs.getAllQuestions());
		return "listallquestions";
	} 

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Questions questions, Model model) {
		qs.save(questions); 
		model.addAttribute("listAllQuestions", qs.getAllQuestions());
		return "listallquestions";
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable(value="id") Integer questionId, Model model) {
		model.addAttribute("questions", qs.findById(questionId));
		return "update";
	}  
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable(value="id") Integer questionId) {
		qs.deleteById(questionId);
		return "redirect:../listallquestions";	
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Questions acc, Model model){
		qs.save(acc);
		model.addAttribute("listAllQuestions", qs.getAllQuestions());
		return "listallquestions";
	}
}
