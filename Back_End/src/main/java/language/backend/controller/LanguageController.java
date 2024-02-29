package language.backend.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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
	
	@Operation(summary = "Creates a new Language resource given name and diffculty level.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Language resource successfully created.", headers = {
					@Header(name = "location", description = "URI to access the created resource") }, content = {
							@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }) })
	
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
	@Operation(summary = "Update Language resource with the given Language details in the database.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Language resource successfully updated and returned.", headers = {
					@Header(name = "location", description = "URI to access and return sources") }, content = {
							@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }) })
	@PutMapping("/{id}")
	public ResponseEntity<?> updateLangauge(@PathVariable Integer id, @RequestBody Language language) {

		if (this.languageService.updateLanguageById(id, language)) {

			return new ResponseEntity<>(HttpStatus.OK);

		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Operation(summary = "Find Language resource with the given id in the database.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Language resource successfully returned.", headers = {
					@Header(name = "location", description = "URI to access and return sources") }, content = {
							@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }) })
	@GetMapping("/{id}")
	public ResponseEntity<?> findLangaugeById(@PathVariable Integer id) {

		Language languageInDb = this.languageService.findLanguageById(id);

		if (languageInDb == null) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}

		return new ResponseEntity<>(languageInDb, HttpStatus.OK);
	}
	@Operation(summary = "Find all Language resources in the database.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Language resource(s) successfully returned.", headers = {
					@Header(name = "location", description = "URI to access and return sources") }, content = {
							@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }) })
	@GetMapping
	public ResponseEntity<?> findAllLanguages() {

		return new ResponseEntity<>(this.languageService.findAllLanguages(), HttpStatus.OK);
	}
	
	@Operation(summary = "Find Language resource with the given difficultyLevel in the database.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Langauge resource(s) successfully returned.", headers = {
					@Header(name = "location", description = "URI to access and return sources") }, content = {
							@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }) })
	@GetMapping("/difficultyLevel/{difficultyLevel}")
	public ResponseEntity<?> findLanguageByDifficultyLevel(@PathVariable String difficultyLevel ) {
		
		List<Language> langaugesInDb = this.languageService.findLanguagesByDifficultyLevel(difficultyLevel);
		
		if(langaugesInDb == null) {
			
			return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		
		return new ResponseEntity<>(langaugesInDb, HttpStatus.OK);
		
		
	}

}
