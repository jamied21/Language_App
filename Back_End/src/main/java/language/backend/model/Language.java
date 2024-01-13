package language.backend.model;

import java.util.HashMap;

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
	 * #M1 is the multiplier for cat 1 languages and so on
	 * 
	 * M1 = 1 M2 = round(900/750, 1) M3 = round(1100/750, 1) M4 = round(2200/750,1)
	 * 
	 * A1 = 60 A2 = 160 B1 = 210 B2 = 260 C1 = 700 C2 = 1000
	 */
	// A1 = 60-100 , A2 = 160-200, B1 =210-400, B2 = 260-600, # C1 = 700-800, C2 = 1000-1200
	
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
	
	/**
	 * Calculates the number of days required to reach the desired fluency level in a target language.
	 *
	 * @param dailyStudyTime      the daily study time in hours
	 * @param language            the target language
	 * @param currentFluencyLevel the current fluency level (A1, A2, B1, B2, C1, C2)
	 * @param desiredFluencyLevel the desired fluency level (A2, B1, B2, C1, C2)
	 * @return the number of days required to reach the desired fluency level
	 */
	
public Double timeToFluencyCalculator(Double dailyStudyTime, Language language, String currentFluencyLevel, String desiredFluencyLevel ) {
		
	// (( Hours for target fluency level - hours of current fluency level) / (hourStudied) ) * Multiplier
	// A1 = 7% of hours to reach C2 Level
	// A2 = 16% of hours to reach C2 Level
	// B1 = 34% of hours to reach C2 Level
	// B2 = 54% of hours to reach C2 Level
	// C1 = 78% of hours to reach C2 Level
	// C2 = 100% of hours to reach C2 Level
		
		// I do not expect these values to change hence I have hardcoded them into the method
		//These are the hours needed for each level for a Cat1 language
		HashMap<String, Integer> levelToHours = new HashMap<String, Integer>();
		levelToHours.put("A0", 0); // Complete beginner, no past knowledge
		levelToHours.put("A1", 67);
		levelToHours.put("A2", 154);
		levelToHours.put("B1", 326);
		levelToHours.put("B2", 518);
		levelToHours.put("C1", 750);
		levelToHours.put("C2", 960);
		Double daysToFluency = null;
		
		
		if (language.getDifficultyLevel().equals("Cat1")) {
			
			// catOnemultiplier = 1.0;
			
			daysToFluency = ((levelToHours.get(desiredFluencyLevel)-levelToHours.get(currentFluencyLevel))/dailyStudyTime) ;
	
			
		}
		
		else if (language.getDifficultyLevel().equals("Cat2")) {
			
			// catTwomultiplier = 1.2; 900/750
			
			daysToFluency = ((levelToHours.get(desiredFluencyLevel)-levelToHours.get(currentFluencyLevel))/dailyStudyTime) * 1.2;
	
			
		}
		
		else if (language.getDifficultyLevel().equals("Cat3")) {
			
			//catThreemultiplier = 1.5; 1100 / 750
			
			daysToFluency = ((levelToHours.get(desiredFluencyLevel)-levelToHours.get(currentFluencyLevel))/dailyStudyTime) * 1.5;
	
			
		}
		
		else if (language.getDifficultyLevel().equals("Cat4")) {
			
			// catFourmultiplier = 2.9; 750/2200
			
			daysToFluency = ((levelToHours.get(desiredFluencyLevel)-levelToHours.get(currentFluencyLevel))/dailyStudyTime) * 2.9;
	
			
		}
		
		
		return daysToFluency;
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
