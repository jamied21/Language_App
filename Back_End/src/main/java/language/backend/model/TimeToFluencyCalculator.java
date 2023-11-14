package language.backend.model;

import java.math.BigDecimal;

public class TimeToFluencyCalculator {
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
	 * '''
	 * Input 
	 * -Minutes/Hours a day you will spend a day
	 * -Language you want to learn
	 * - Your current fluency level A1-C1 as no point being C2 level at the start
	 * -Fluency you want to reach A1-C2
	 * 
	 * 
	 * 
	 * Returns ------- How many days are needed to reach desired level
	 * fluency level of target language
	 * '''
	 * 
	 * public languageCalculator(minutes,lang,fluency level):
	 * 
	 * 
	 * #M1 is the multiplier for cat 1 languages and so on
	 * 
	 * M1 = 1 M2 = round(900/750, 1) M3 = round(1100/750, 1) M4 = round(2200/750,1)
	 * 
	 * A1 = 60 A2 = 160 B1 = 210 B2 = 260 C1 = 700 C2 = 1000
	 */

	private Language language;

	
	
	


	public TimeToFluencyCalculator(Language language) {
		super();
		this.language = language;
	}






	public Double fluencyCalculator(BigDecimal minutesStudied, Language language, String currentFluencyLevel, String fluencyLevel ) {
		
		
		Double daysToFluency = 1.0;
		
		if (language.getDifficultyLevel().equals("Cat1")) {
			
			Double catOnemultiplier = 1.0;
			
			
			
			
		}
		
		
		return daysToFluency;
	}






	public Language getLanguage() {
		return language;
	}






	public void setLanguage(Language language) {
		this.language = language;
	}
	
	
	
}
	
	