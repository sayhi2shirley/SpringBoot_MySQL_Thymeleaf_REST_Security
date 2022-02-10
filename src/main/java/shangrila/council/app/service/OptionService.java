package shangrila.council.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shangrila.council.app.model.Options;
import shangrila.council.app.repository.OptionsRepository;

@Service
public class OptionService {

	@Autowired
	private OptionsRepository optionRepository;

	public Object getAllOptions() {	
		return optionRepository.findAll();
	}

	public Optional<Options> findById(Integer optionId) {	
		return  optionRepository.findById(optionId);
	}

	public void deleteById(Integer id) {
		optionRepository.deleteById(id);;
	}

	public void save(Options options) {
		optionRepository.save(options);
	}
}
