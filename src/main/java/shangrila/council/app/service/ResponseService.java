package shangrila.council.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shangrila.council.app.model.Options;
import shangrila.council.app.model.Response;
import shangrila.council.app.repository.OptionsRepository;
import shangrila.council.app.repository.QuestionRepository;
import shangrila.council.app.repository.ResponseRepository;

@Service
public class ResponseService {

	@Autowired
	private OptionsRepository optionRepository;
	
	@Autowired
	private QuestionRepository questionRepository;

	@Autowired 
	private ResponseRepository reponseRepository;
	
	public Object getAllResponses() {
		System.out.println("ResponseService: getAllOptions");
		return reponseRepository.findAll();
	}
	
	public Optional<Response> findById(Integer responseId) {	
		System.out.println("ResponseService: findById "+ 
				responseId);
		//return  questionRepository.findById(questionId).get();
		return  reponseRepository.findById(responseId);
	}
	
	public void save(Response response) {
		System.out.println("ResponseService: save" + 
				response.getUserID() + "  " + response.getQuestionID() +
				" " + response.getChosenOptionID());
		
		reponseRepository.save(response);
	}

}
