package language.Language_Predictor.controller;

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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import language.backend.controller.LanguageController;
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
	public void givenNothing_whenFindAllLanguages_thenReturnAllSavedLanguages() throws Exception {
		// Arrange
		List<Language> languageList = new ArrayList<>();
		languageList.add(language1);
		languageList.add(language2);

		when(mockLanguageService.findAllLanguages()).thenReturn(languageList);

		mockMvc.perform(get("/api/v1/languages")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[0].languageName", is(language1.getLanguageName())));

		verify(mockLanguageService, times(1)).findAllLanguages();

	}

	@AfterEach
	void tearDown() throws Exception {
		language1 = null;
		language2 = null;

	}

}
