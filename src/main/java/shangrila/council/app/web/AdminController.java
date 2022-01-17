package shangrila.council.app.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	    public String viewAllResponse(Model model){
		  model.addAttribute("listResponse", rs.getAllResponses());
		  model.addAttribute("listAllOptions", os.getAllOptions());
	        return "viewresponse";
	    } 
	  
	  @RequestMapping(value = {"/listoptions"})
	    public String listAllOptions(Model model){
		  model.addAttribute("listOptions", os.getAllOptions());
		  //System.out.println("listAllOptions" + os.getAllOptions());
	        return "listoptions";
	    } 
	  
	    @RequestMapping(value = "/deleteOpt/{id}")
	    public String deleteOpt(@PathVariable(value="id") Integer optionId){
	    	System.out.println("AdminController: deleteOption  :  " + optionId );
	        os.deleteById(optionId);
	        return "redirect:../listoptions";	
	    }
	  


	
	    @RequestMapping(value = "/addoption/{id}")
        public String addopt(@PathVariable(value="id") Integer optionId, Model model) {
	    	System.out.println("AddOption :  " + optionId );
	    	 model.addAttribute("listAllQuestions", qs.getAllQuestions());
	    	model.addAttribute("options", os.findById(optionId));
	    	System.out.println("AddOption :  2 " + os.findById(optionId));
	    	//model.addAttribute("option", new Options());
	    	  return "newoption";    
	    } 
	  
	    @RequestMapping(value = "/editoption/{id}")
        public String editopt(@PathVariable(value="id") Integer optionId, Model model) {
	    	System.out.println("EditOption :  " + optionId );
	        //model.addAttribute("listAllQuestions", qs.getAllQuestions());
	    	model.addAttribute("options", os.findById(optionId));
	    	model.addAttribute("listAllQuestions", qs.getAllQuestions());
	    	//model.addAttribute("option", new Options());
	    	  return "option_form";    
	    } 
	    
	    @RequestMapping(value = "/create")
	    public String create(Model model){	
	    	System.out.println("admin/create");
	    	//model.addAttribute("option", new Options());
	    	  return "create";    
	    } 
	    
	    @RequestMapping(value = "/optsave", method = RequestMethod.POST)
	   public String saveoption(Options options, Model model) {
			System.out.println("AdminController: saveoption" + 
				       options.getOptionText());
			model.addAttribute("questionid", options.getQuestion().getQuestionID());
			model.addAttribute("listOptions", options.getQuestion().getOptions());
	        os.save(options); 
	        model.addAttribute("listAllOptions", os.getAllOptions());
	        return "listoptions";

	    }
	    
	    @RequestMapping(value = "/getqoptions/{id}")
	    public String getQuestionOptions(@PathVariable(value="id") Integer questionId, 
	    		Model model) {
			System.out.println("getQuestionOptions:  " + questionId );
			model.addAttribute("listOptions", qs.findById(questionId).get().getOptions());
			return "getqoptions";
	    } 
	    
	    
		  //http://localhost:8080/questions/listAll
		  @RequestMapping(value = {"/listallquestions"})
		    public String listAll(Model model){
		        System.out.println(qs.getAllQuestions());
		        //System.out.println(new ModelAndView("listAll","accounts",as.findAllAccounts()));
		        model.addAttribute("listAllQuestions", qs.getAllQuestions());
		        return "listallquestions";
		    } 
		  
		  
	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	   public String save(Questions questions, Model model) {
			System.out.println("AdminController: save" + 
				       questions.getQuestionText() + "  " + questions.getQuestionID());
			
	        qs.save(questions); 
	        model.addAttribute("listAllQuestions", qs.getAllQuestions());
	        return "listallquestions";

	    }
	    @RequestMapping(value = "/edit/{id}")
	    public String edit(@PathVariable(value="id") Integer questionId, Model model) {
			System.out.println("AdminController: edit  :  " + questionId );
			Integer questionID = questionId;
			/* The first argument is the object of the form in update. */
			model.addAttribute("questions", qs.findById(questionId));
			System.out.println("AdminController: edit  :  " + questionId );
	        //return new ModelAndView("edit", "question", qs.findById(id));
	        return "update";
	    }  
	    @RequestMapping(value = "/delete/{id}")
	    public String delete(@PathVariable(value="id") Integer questionId){
	    	System.out.println("AdminController: delete  :  " + questionId );
	        qs.deleteById(questionId);
	        return "redirect:../listallquestions";	
	    }
	    @RequestMapping(value = "/update", method = RequestMethod.POST)
	    public String update(Questions acc, Model model){
			System.out.println("AdminController: update1" + 
				       acc.getQuestionText() + "  " + acc.getQuestionID());
	        qs.save(acc);
			System.out.println("AdminController: update" + 
				       acc.getQuestionText() + "  " + acc.getQuestionID());
	        model.addAttribute("listAllQuestions", qs.getAllQuestions());
			return "listallquestions";
	    }
}
