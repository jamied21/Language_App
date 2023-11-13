package language.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import language.backend.model.Language;
import language.backend.repository.LanguageRepository;

@Transactional
@Service
public class LanguageServiceImp implements LanguageService {

	@Autowired
	private LanguageRepository languageRepository;

	public LanguageServiceImp(LanguageRepository languageRepository) {

		this.languageRepository = languageRepository;
	}

	@Override
	public List<Language> findAllLanguages() {

		return languageRepository.findAll();
	}

	@Override
	public Language findLanguageById(Integer id) {

		return this.languageRepository.findById(id).orElse(null);
	}

	@Override
	public Language createLanguage(Language language) {

		return languageRepository.save(language);
	}

	@Override
	public boolean updateLanguageById(Integer id, Language language) {
		if (this.languageRepository.existsById(id)) {

			this.languageRepository.save(language);

			return true;
		}

		return false;
	}

	/*
	 * @Override public List<Language> findLanguagesByDifficultyLevel(String
	 * difficultyLevel) { // TODO Auto-generated method stub return null; }
	 */

}
