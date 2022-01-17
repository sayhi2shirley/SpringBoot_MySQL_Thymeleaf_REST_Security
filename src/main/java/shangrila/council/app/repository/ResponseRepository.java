package shangrila.council.app.repository;

import org.springframework.data.repository.CrudRepository;

import shangrila.council.app.model.Response;
import shangrila.council.app.model.Options;

public interface ResponseRepository extends CrudRepository<Response, Integer> {

}
