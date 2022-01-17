package shangrila.council.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shangrila.council.app.model.Options;
import shangrila.council.app.model.Questions;
import shangrila.council.app.repository.OptionsRepository;
import shangrila.council.app.repository.QuestionRepository;

@Service
public class OptionService {

	@Autowired
	private OptionsRepository optionRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	public Object getAllOptions(){
		System.out.println("OptionService: getAllOptions");
		return optionRepository.findAll();
	}
	
	public Optional<Options> findById(Integer optionId){	
		System.out.println("OptionService: findById "+ 
				optionId);
		//return  questionRepository.findById(questionId).get();
		return  optionRepository.findById(optionId);
	}
	
	public void deleteById(Integer id){
		System.out.println("OptionService: deleteById "+ id);
		optionRepository.deleteById(id);;
	}
	
	public void save(Options options){
		System.out.println("OptionService: save" + 
	       options.getOptionText() + "  " + options.getOptionID());
		
		optionRepository.save(options);
	}
}
