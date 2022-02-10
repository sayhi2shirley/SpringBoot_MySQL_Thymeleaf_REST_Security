package shangrila.council.app.repository;

import org.springframework.data.repository.CrudRepository;

import shangrila.council.app.model.Questions;


public interface QuestionRepository extends CrudRepository<Questions, Integer> {
	
	Questions findById(int id);
}
