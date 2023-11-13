package language.backend.service;

import java.util.List;

import language.backend.model.Language;

public interface LanguageService {

	List<Language> findAllLanguages();

	Language findLanguageById(Integer id);

	Language createLanguage(Language language);

	boolean updateLanguageById(Integer id, Language language);

//	List<Language> findLanguagesByDifficultyLevel(String difficultyLevel);

}
