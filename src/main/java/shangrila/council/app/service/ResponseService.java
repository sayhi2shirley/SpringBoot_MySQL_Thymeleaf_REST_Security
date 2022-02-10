package shangrila.council.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shangrila.council.app.model.Response;
import shangrila.council.app.repository.ResponseRepository;

@Service
public class ResponseService {

	@Autowired 
	private ResponseRepository reponseRepository;

	public Object getAllResponses() {
		return reponseRepository.findAll();
	}

	public Optional<Response> findById(Integer responseId) {	
		return  reponseRepository.findById(responseId);
	}

	public void save(Response response) {	
		reponseRepository.save(response);
	}

}
