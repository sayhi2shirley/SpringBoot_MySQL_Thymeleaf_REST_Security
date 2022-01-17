package shangrila.council.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shangrila.council.app.model.Options;
import shangrila.council.app.model.Questions;
import shangrila.council.app.repository.QuestionRepository;

@Service
public class QuestionService {
	@Autowired
	private QuestionRepository questionRepository;
	
	public Object getAllQuestions(){
		System.out.println("QuestionService: findBygetAllQuestionsId");
		return questionRepository.findAll();
	}
	
	public Optional<Questions> findById(Integer questionId){	
		System.out.println("QuestionService: findById "+ 
				questionId);
		return  questionRepository.findById(questionId);
	}
	
	public void deleteById(Integer id){
		System.out.println("QuestionService: deleteById "+ id);
		questionRepository.deleteById(id);;
	}
	
	
	public void save(Questions questions){
		System.out.println("QuestionService: save" + 
	       questions.getQuestionText() + "  " + questions.getQuestionID());
		
		questionRepository.save(questions);
	}
}
