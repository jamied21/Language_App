package language.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import language.backend.model.LanguageLevel;

public interface LanguageLevelRepository extends JpaRepository<LanguageLevel, Integer> {
	
	//Need to be able to find number hours for level based on the difficulty level

}
