package language.backend.model;

import java.util.HashMap;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
//This hours for each level for Category 1 languages

@Entity
@Table(name = "LanguageLevels")
public class LanguageLevel{
	@Id
	@SequenceGenerator(name = "LANGUAGELEVEL_ID_GEN", sequenceName = "languagelevel_id_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LANGUAGELEVEL_ID_GEN")
	private Integer id;
	
	//E.g C1, C2
	@Column(name = "levelForLanguage", unique = true)
	private String levelForLanguage;
	
	// C1 = 750 hours max for CAT 1 language
	@Column(name = "HoursForLevel", unique = true)
	private Integer hoursForLevel;

	
	
	public LanguageLevel() {
	
	}



	public LanguageLevel(Integer id, String levelForLanguage, Integer hoursForLevel) {
		
		this.id = id;
		this.levelForLanguage = levelForLanguage;
		this.hoursForLevel = hoursForLevel;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getLevelForLanguage() {
		return levelForLanguage;
	}



	public void setLevelForLanguage(String levelForLanguage) {
		this.levelForLanguage = levelForLanguage;
	}



	public Integer getHoursForLevel() {
		return hoursForLevel;
	}



	public void setHoursForLevel(Integer hoursForLevel) {
		this.hoursForLevel = hoursForLevel;
	}

}