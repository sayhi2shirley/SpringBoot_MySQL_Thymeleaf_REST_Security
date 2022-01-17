package shangrila.council.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import shangrila.council.app.model.Options;
import shangrila.council.app.model.Questions;


public interface QuestionRepository extends CrudRepository<Questions, Integer> {
	//List<Questions> findById(int id);
	Questions findById(int id);
}
