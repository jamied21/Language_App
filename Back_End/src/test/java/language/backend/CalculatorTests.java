package language.backend;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import language.backend.model.Language;

class CalculatorTests {

	@Mock
	
	Language catOneLanguage;
	Language catTwoLanguage;
	Language catThreeLanguage;
	Language catFourLanguage;
	
	Double dailyStudyTime;
	String currentFluencyLevel;
	String desiredFluencyLevel;
	
	@BeforeEach
	void setUp() throws Exception {
		
		dailyStudyTime = 2.5; //Hours
        currentFluencyLevel = "A2";
        desiredFluencyLevel = "B2";
        catOneLanguage = new Language("Cat1", "Italian");
        catTwoLanguage = new Language("Cat2", "German");
        catThreeLanguage = new Language("Cat3", "Greek");
        catFourLanguage = new Language("Cat4", "Chinese");
	}
	
	
	@Test
    void calculateFluency_Cat1() {
       

        

        Double result = catOneLanguage.timeToFluencyCalculator(dailyStudyTime, catOneLanguage, currentFluencyLevel, desiredFluencyLevel);
        Double expectedResult = 145.6;
        
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void calculateFluency_Cat2() {
       

       

        Double result = catTwoLanguage.timeToFluencyCalculator(dailyStudyTime, catTwoLanguage ,currentFluencyLevel, desiredFluencyLevel);
        Double expectedResult = 174.72;
        
        assertEquals(expectedResult, result, 0.01);
    }
    
    @Test
    void calculateFluency_Cat3() {
        
       

        Double result = catThreeLanguage.timeToFluencyCalculator(dailyStudyTime, catThreeLanguage ,currentFluencyLevel, desiredFluencyLevel);
        Double expectedResult = 218.4;
        
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void calculateFluency_Cat4() {
       
       

        Double result = catFourLanguage.timeToFluencyCalculator(dailyStudyTime, catFourLanguage ,currentFluencyLevel, desiredFluencyLevel);
        Double expectedResult = 422.24;
        
        assertEquals(expectedResult, result, 0.01);
    }

    

	@AfterEach
	void tearDown() throws Exception {
	}

	

}
