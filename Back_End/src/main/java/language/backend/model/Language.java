package language.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "languages")
public class Language {
	
	@Id
	@SequenceGenerator(name = "LANGUAGE_ID_GEN", sequenceName = "language_id_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LANGUAGE_ID_GEN")
	private Integer languageId;

	@NotBlank(message = "Please type in a language level")
	private String difficultyLevel;
	// Difficulty level is split into 4 categories Level 1 to Level 4, with Level
	// one being the easiest level
	// It relates to how difficult it is to learn that language

	@NotBlank(message = "Please type in a language name")
	private String languageName;

	public Language() {

	}

	public Language(String difficultyLevel, String languageName) {

		this.difficultyLevel = difficultyLevel;
		this.languageName = languageName;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

}
