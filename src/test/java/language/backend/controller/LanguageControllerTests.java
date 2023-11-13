package language.backend.controller;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

	@Autowired
	private ObjectMapper objectMapper; // needed to convert to JSON object

	@BeforeEach
	void setUp() throws Exception {

		language1 = new Language("Cat1", "Italian");
		language1.setLanguageId(1);

		language2 = new Language("Cat4", "Mandarin");
		language2.setLanguageId(2);
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
		
		
		given(mockLanguageService.findLanguageById(3)).willReturn(null);

		mockMvc.perform(get("/api/v1/languages/3")).andDo(print()).andExpect(status().isNotFound());
				
		verify(mockLanguageService, times(1)).findLanguageById(3);

	}
	
	@Test
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
	
	

	@AfterEach
	void tearDown() throws Exception {
		language1 = null;
		language2 = null;

	}

}
