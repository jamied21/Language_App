package language.backend.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import jakarta.validation.Valid;
import language.backend.model.Language;
import language.backend.service.LanguageService;

@RestController
@RequestMapping("/api/v1/languages")
@CrossOrigin(origins = "http://localhost:3000")
public class LanguageController {

	private LanguageService languageService;

	public LanguageController(LanguageService languageService) {

		this.languageService = languageService;
	}
	
	
	@PostMapping("/{id}/time-to-fluency")
    public ResponseEntity<Double> calculateTimeToFluency(
            @PathVariable Integer id,
            @RequestParam Double dailyStudyTime,
            @RequestParam String currentFluencyLevel,
            @RequestParam String desiredFluencyLevel) {

        Language language = languageService.findLanguageById(id);

        if (language == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println("Received request data: ");
        System.out.println("id: " + id);
        System.out.println("dailyStudyTime: " + dailyStudyTime);
        System.out.println("currentFluencyLevel: " + currentFluencyLevel);
        System.out.println("desiredFluencyLevel: " + desiredFluencyLevel);

        Double timeToFluency = language.timeToFluencyCalculator(
                dailyStudyTime,
                language,
                currentFluencyLevel,
                desiredFluencyLevel
        );

        return new ResponseEntity<>(timeToFluency, HttpStatus.OK);
    }

	@PostMapping
	public ResponseEntity<?> saveLanguage(@Valid @RequestBody Language language, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();

			for (FieldError error : bindingResult.getFieldErrors()) {

				errors.put(error.getField(), error.getDefaultMessage());
			}

			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

		}

		return new ResponseEntity<>(this.languageService.createLanguage(language), HttpStatus.CREATED);

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateLangauge(@PathVariable Integer id, @RequestBody Language language) {

		if (this.languageService.updateLanguageById(id, language)) {

			return new ResponseEntity<>(HttpStatus.OK);

		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findLangaugeById(@PathVariable Integer id) {

		Language languageInDb = this.languageService.findLanguageById(id);

		if (languageInDb == null) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}

		return new ResponseEntity<>(languageInDb, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> findAllLanguages() {

		return new ResponseEntity<>(this.languageService.findAllLanguages(), HttpStatus.OK);
	}
	
	@GetMapping("/difficultyLevel/{difficultyLevel}")
	public ResponseEntity<?> findLanguageByDifficultyLevel(@PathVariable String difficultyLevel ) {
		
		List<Language> langaugesInDb = this.languageService.findLanguagesByDifficultyLevel(difficultyLevel);
		
		if(langaugesInDb == null) {
			
			return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		
		return new ResponseEntity<>(langaugesInDb, HttpStatus.OK);
		
		
	}

}
