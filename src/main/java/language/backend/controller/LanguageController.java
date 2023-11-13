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

}
