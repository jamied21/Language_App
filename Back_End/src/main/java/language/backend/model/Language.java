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
	/*
	 * #Dictionary of languages and their Difficulty levels #Cat 1 are languages
	 * which are easiest to learn 'Cat 1': ['Spanish', 'French',
	 * 'Italian','Dutch','Portuguese','Swedish','Danish','Norwegian'], 'Cat 2':
	 * ['German', 'Indonesian', 'Swahili'], 'Cat 3':
	 * ['Albanian','Bulgarian','Czech','Greek','Icelandic','Polish', 'Russian',
	 * 'Turkish'], 'Cat 4':['Arabic','Chinese','Japanese', 'Korean']}
	 * 
	 * #%% Multipliers
	 * 
	 * #Takes 600-750 hours to reach C1 proficiency in cat 1 # 900 hours to reach C1
	 * proficiency in cat 2 # 1100 hours to reach C1 proficiency in cat 3 # 2200
	 * hours to reach C1 proficiency in cat 4
	 * 
	 * # How many hours for C1 and other CEFR levels #Min/Max depends on age,
	 * language and exposure to the language # A1 = 60-100 , A2 = 160-200, B1 =
	 * 210-400, B2 = 260-600, # C1 = 700-800, C2 = 1000-1200
	 * 
	 * #Let CAT have a difficulty multiplier/coefficient of 1
	 * 
	 * 
	 * 
	 * 
	 * def multiplier(hours,lang): ''' Parameters ---------- hours: number of hours
	 * you study lang : string
	 * 
	 * 
	 * Returns ------- How much study hours are increased by based on the language
	 * difficulty
	 * 
	 * 
	 * '''
	 * 
	 * #M1 is the multiplier for cat 1 languages and so on
	 * 
	 * M1 = 1 M2 = round(900/750, 1) M3 = round(1100/750, 1) M4 = round(2200/750,1)
	 * 
	 * A1 = 60 A2 = 160 B1 = 210 B2 = 260 C1 = 700 C2 = 1000
	 */
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
