package shangrila.council.app.web;

import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import shangrila.council.app.service.ResidentUserService;
import shangrila.council.app.web.dto	.ResidentUserRegDTO;

@Controller
public class ResidentUserRegController {
	private ResidentUserService residentUserService;

	public ResidentUserRegController(ResidentUserService residentUserService) {
		super();
		this.residentUserService = residentUserService;
	}

	@ModelAttribute("residentUser")
	public ResidentUserRegDTO residentUserRegDTO() {
		return new ResidentUserRegDTO();
	}

	@GetMapping ("/registration")
	public String displayRegistrationForm(Model model) {
		model.addAttribute("residentUser", new ResidentUserRegDTO()); 
		/* Display the "ResidentUserRegistration.html" page */
		return "ResidentUserRegistration"; 
	}

	@RequestMapping(value="/process", method=RequestMethod.POST)
	public String registerResidentUserAcc(@Valid @ModelAttribute("residentUser") 
	ResidentUserRegDTO residentUserRegDTO,
	BindingResult result, Model model) {
		if(residentUserRegDTO.getEmail().equals("admin@shangrila.gov.uk")) {
			residentUserRegDTO.setAdmin(1);
		} else {
			residentUserRegDTO.setAdmin(0);
		}
		try 
		{

			if (result.hasErrors()) {
				List<FieldError> err=result.getFieldErrors();

				for(FieldError e:err){
					System.out.println("Error on object ---> " + 
							e.getObjectName()+ " on field ---> "
							+ e.getField()+". Message ---> "+ e.getDefaultMessage()); 
				}
				return "ResidentUserRegistration";
			}

			/* This will save the Client(URL) entered details 
			 * to the server (MySQL) by transfering via DTO Object */
			residentUserService.save(residentUserRegDTO);
			/*
			 * Returning a view which will be executed
			 * by the thymeleaf attribute param.acccreationsuccess
			 */
			return "redirect:/registration?acccreationsuccess";
		} 

		catch (Exception e) {
			//e.printStackTrace();
			//model.addAttribute("residentUser", new ResidentUserRegDTO());
			//session.setAttribute("message", new Message(""));
			List<FieldError> err=result.getFieldErrors();

			for(FieldError er:err){
				System.out.println("Error on object ---> "+ 
						er.getObjectName()+" on field ---> " + er.getField()
						+ ". Message ---> "+ er.getDefaultMessage());
			}

			return "ResidentUserRegistration";
		}
	}

}
