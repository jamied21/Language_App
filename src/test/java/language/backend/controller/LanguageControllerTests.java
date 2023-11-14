package language.backend.controller;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;


import language.backend.model.Language;
import language.backend.service.LanguageService;


@WebMvcTest(LanguageController.class)
class LanguageControllerTests {

	@Autowired
	private MockMvc mockMvc;
	//

	@MockBean
	private LanguageService mockLanguageService;

	private Language language1;
	private Language language2;
	private Language language3;

	@Autowired
	private ObjectMapper objectMapper; // needed to convert to JSON object

	@BeforeEach
	void setUp() throws Exception {

		language1 = new Language("Cat1", "Italian");
		language1.setLanguageId(1);

		language2 = new Language("Cat4", "Mandarin");
		language2.setLanguageId(2);
		
		language3 = new Language("Cat1", "English");
		language3.setLanguageId(3);
	}
	
	@Test
	@DisplayName("Save language - positive")
	public void givenCorrectObject_whenSaveLanguage_thenSavedLanguageObject() throws Exception {
		
		    when(mockLanguageService.createLanguage(ArgumentMatchers.any(Language.class))).thenReturn(language1);
		    
		
		    
mockMvc.perform(post("/api/v1/languages").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(language1))).andDo(print())
.andExpect(status().isCreated());


		    
		    verify(mockLanguageService, times(1)).createLanguage(ArgumentMatchers.any(Language.class));
		}
	
	@Test
	@DisplayName("Save language - negative")
	public void givenIncorrectObject_whenSaveLanguage_thenSavedReturnError() throws Exception {
			language1.setLanguageName(null);
		    when(mockLanguageService.createLanguage(ArgumentMatchers.any(Language.class))).thenReturn(language1);
		    
		
		    
mockMvc.perform(post("/api/v1/languages").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(language1))).andDo(print())
.andExpect(status().isBadRequest());


		    
		    verify(mockLanguageService, times(0)).createLanguage(ArgumentMatchers.any(Language.class));
		}
	

	@Test
	@DisplayName("Update language by Id - positive")
	public void givenId_whenFindLanguage_thenReturnUpdatedLanguage() throws Exception {
		
		    when(mockLanguageService.updateLanguageById(eq(1), any(Language.class))).thenReturn(true);

		    mockMvc.perform(put("/api/v1/languages/{id}", 1)
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(objectMapper.writeValueAsString(language1)))
		            .andExpect(status().isOk());
		    
		    verify(mockLanguageService, times(1)).updateLanguageById(eq(1), any(Language.class));
		}
	
	@Test
	@DisplayName("Update language by Id - negative")
	public void givenId_whenFindLanguage_thenReturnFalseAndNotFound() throws Exception {
		
		    when(mockLanguageService.updateLanguageById(eq(10), any(Language.class))).thenReturn(false);

		    mockMvc.perform(put("/api/v1/languages/{id}", 10)
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(objectMapper.writeValueAsString(language1)))
		            .andExpect(status().isNotFound());
		    
		    verify(mockLanguageService, times(1)).updateLanguageById(eq(10), any(Language.class));
		}
	
	@Test
	@DisplayName("Find language by Id - positive")
	public void givenId_whenFindLanguage_thenReturnCorrrectLanguage() throws Exception {
		// Arrange
		
		
		given(mockLanguageService.findLanguageById(1)).willReturn(language1);

		mockMvc.perform(get("/api/v1/languages/1")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.languageName", is(language1.getLanguageName())));

		verify(mockLanguageService, times(1)).findLanguageById(1);

	}
	
	@Test
	@DisplayName("Find language by Id - negative")
	public void givenNonExistentId_whenFindLanguage_thenReturnNotFound() throws Exception {
		// Arrange
		
		
		given(mockLanguageService.findLanguageById(10)).willReturn(null);

		mockMvc.perform(get("/api/v1/languages/10")).andDo(print()).andExpect(status().isNotFound());
				
		verify(mockLanguageService, times(1)).findLanguageById(10);

	}
	
	@Test
	@DisplayName("Find all languages")
	public void givenNothing_whenFindAllLanguages_thenReturnAllSavedLanguages() throws Exception {
		// Arrange
		List<Language> languageList = new ArrayList<>();
		languageList.add(language1);
		

		when(mockLanguageService.findAllLanguages()).thenReturn(languageList);

		mockMvc.perform(get("/api/v1/languages")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].languageName", is(language1.getLanguageName())));

		verify(mockLanguageService, times(1)).findAllLanguages();

	}
	
	@Test
	@DisplayName("Find language by diffcultyLevel - postive ")
	public void givenDifficultyLevel_whenFindLanguages_thenReturnAllCorrectLanguages() throws Exception {
		// Arrange
		List<Language> expectedList = new ArrayList<>();
		expectedList.add(language1);
		expectedList.add(language3);
		String difficultyLevel = "Cat1";
		

		when(mockLanguageService.findLanguagesByDifficultyLevel(difficultyLevel)).thenReturn(expectedList);

		mockMvc.perform(get("/api/v1/languages/difficultyLevel/{difficultyLevel}",difficultyLevel)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[0].languageName", is(language1.getLanguageName())))
				.andExpect(jsonPath("$[1].languageName", is(language3.getLanguageName()))); 
;

		verify(mockLanguageService, times(1)).findLanguagesByDifficultyLevel(difficultyLevel);

	}
	
	
	

	@AfterEach
	void tearDown() throws Exception {
		language1 = null;
		language2 = null;
		language3 = null;

	}

}
