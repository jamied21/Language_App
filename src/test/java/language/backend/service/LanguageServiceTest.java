package language.backend.service;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import language.backend.model.Language;
import language.backend.repository.LanguageRepository;
import language.backend.service.LanguageServiceImp;

@ExtendWith(MockitoExtension.class)
class LanguageServiceTest {

	@InjectMocks
	private LanguageServiceImp mockLanguageService;

	@Mock
	private LanguageRepository mockLanguageRepository;

	@Mock
	private Language language1;
	private Language language2;

	@BeforeEach
	void setUp() {

		language1 = new Language("Cat1", "Italian");
		language1.setLanguageId(1);

		language2 = new Language("Cat4", "Mandarin");
		language2.setLanguageId(2);

	}

	@Test
	@DisplayName("Get all Languages")
	void arrangeLanguageLists_actfindLanguage_assertLanguageIsCorrect() {
		// Arrange
		List<Language> languageList = new ArrayList<>();
		languageList.add(language1);
		languageList.add(language2);
		when(mockLanguageRepository.findAll()).thenReturn(languageList);
		// Act
		List<Language> result = mockLanguageService.findAllLanguages();
		// Assert
		assertThat(result).isNotNull();
		assertThat(result.size()).isEqualTo(2);
		assertThat(result.get(0).getLanguageId()).isEqualTo(language1.getLanguageId());
		verify(mockLanguageRepository, times(1)).findAll();
	}

	@Test
	@DisplayName("Find language by id")
	void arrangeLanguage_actfindlanguage_assertchecklanguageIscorrect() {
		// Arrange
		Optional<Language> optionaLanguage = Optional.of(language1);
		Integer id = 1;
		when(mockLanguageRepository.findById(id)).thenReturn(optionaLanguage);
		// Act
		Language result = mockLanguageService.findLanguageById(id);

		// Assert
		assertThat(result).isNotNull();
		assertThat(result.getLanguageId()).isEqualTo(language1.getLanguageId());
		verify(mockLanguageRepository, times(1)).findById(id);

	}

	@Test
	@DisplayName("Save Language Positive")
	void arrangeLanguageObject_actCreateLanguageObject_assertLanguageIsCreated() {
		// Arrange
		when(mockLanguageRepository.save(language1)).thenReturn(language1);
		// Act
		Language result = mockLanguageService.createLanguage(language1);
		// Assert
		assertThat(result).isNotNull();
		assertThat(result.getLanguageName()).isEqualTo(language1.getLanguageName());
		verify(mockLanguageRepository, times(1)).save(language1);
	}

	@Test
	@DisplayName("Update Language True")
	void arrangeLanguageObject_actFindLanguageById_assertReturnsTrueAndSaves() {
		// Arrange
		Integer id = 1;
		when(mockLanguageRepository.existsById(id)).thenReturn(true);
		// Act
		boolean result = mockLanguageService.updateLanguageById(id, language1);
		// Assert
		assertThat(result).isEqualTo(true);
		verify(mockLanguageRepository, times(1)).save(language1);

	}

	@Test
	@DisplayName("Update Language False")
	void arrangeLanguageObject_actFindLanguageById_assertReturnsFalseAndDoesNotSave() {
		// Arrange
		Integer id = 2;
		when(mockLanguageRepository.existsById(id)).thenReturn(false);
		// Act
		boolean result = mockLanguageService.updateLanguageById(id, language1);
		// Assert
		assertThat(result).isEqualTo(false);
		verify(mockLanguageRepository, times(0)).save(language1);

	}

	@AfterEach
	void tearDown() throws Exception {
		language1 = null;
		language2 = null;
	}

}
