package shangrila.council.app.repository;

import org.springframework.data.repository.CrudRepository;

import shangrila.council.app.model.Questions;
import shangrila.council.app.model.Options;
public interface OptionsRepository extends CrudRepository<Options, Integer> {
	
}
