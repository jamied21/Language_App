package language.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import language.backend.model.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer> {

	List<Language> findByDifficultyLevel(String difficultyLevel);

}
