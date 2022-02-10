package shangrila.council.app.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shangrila.council.app.model.Questions;
import shangrila.council.app.repository.QuestionRepository;

@Service
public class QuestionService {
	@Autowired
	private QuestionRepository questionRepository;

	public Object getAllQuestions() {
		return questionRepository.findAll();
	}

	public Optional<Questions> findById(Integer questionId) {	
		return  questionRepository.findById(questionId);
	}

	public void deleteById(Integer id) {
		questionRepository.deleteById(id);;
	}


	public void save(Questions questions) {
		questionRepository.save(questions);
	}
}
