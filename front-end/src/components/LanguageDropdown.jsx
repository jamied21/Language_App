import axios from "axios";
import { useEffect, useState } from "react";

import "../styles/AllStyles.css";

const LanguageDropdown = () => {
  const [languages, setLanguages] = useState([]);
  const api = "http://localhost:8080/api/v1/languages";
  const [selectedLanguageId, setSelectedLanguageId] = useState("");
  const [currentFluencyLevel, setCurrentFluencyLevel] = useState("");
  const [desiredFluencyLevel, setDesiredFluencyLevel] = useState("");
  const [dailyStudyTime, setDailyStudyTime] = useState("");
  const [calculatedTimeToFluency, setCalculatedTimeToFluency] = useState(null);

  // Error messages
  const [languageError, setLanguageError] = useState("");
  const [currentFluencyError, setCurrentFluencyError] = useState("");
  const [desiredFluencyError, setDesiredFluencyError] = useState("");
  const [studyTimeError, setStudyTimeError] = useState("");
  const [studyTimeRealisticError, setStudyTimeRealisticError] = useState("");

  const timeToFluencyCalculator = (
    dailyStudyTime,
    currentFluencyLevel,
    desiredFluencyLevel
  ) => {
    const levelToHours = {
      A0: 0,
      A1: 67,
      A2: 154,
      B1: 326,
      B2: 518,
      C1: 750,
      C2: 960,
    };

    const daysToFluency =
      (levelToHours[desiredFluencyLevel] - levelToHours[currentFluencyLevel]) /
      dailyStudyTime;

    return daysToFluency;
  };

  const loadLanguages = () => {
    axios
      .get(api)
      .then((response) => {
        setLanguages(response.data);
      })
      .catch((error) => {
        console.log("unable to load languages");
      });
  };

  const handleSubmit = () => {
    // Reset error messages
    setLanguageError("");
    setCurrentFluencyError("");
    setDesiredFluencyError("");
    setStudyTimeError("");
    setStudyTimeRealisticError("");

    // Validation
    if (!selectedLanguageId) {
      setLanguageError("Please select a language");
      return;
    }

    if (!currentFluencyLevel) {
      setCurrentFluencyError("Please select the current fluency level");
      return;
    }

    if (!desiredFluencyLevel) {
      setDesiredFluencyError("Please select the desired fluency level");
      return;
    }

    if (isNaN(dailyStudyTime) || dailyStudyTime <= 0) {
      setStudyTimeError(
        "Please enter a valid positive number for daily study time"
      );
      return;
    }

    if (dailyStudyTime >= 14) {
      setStudyTimeError(
        "Are you really going to study 14 hours or more a day?"
      );
      return;
    }

    // Calculate time to fluency
    const calculatedTimeToFluency = timeToFluencyCalculator(
      parseFloat(dailyStudyTime),
      currentFluencyLevel,
      desiredFluencyLevel
    );

    setCalculatedTimeToFluency(Math.ceil(calculatedTimeToFluency));
  };

  useEffect(() => {
    setCalculatedTimeToFluency(null);
  }, [selectedLanguageId]);

  useEffect(() => {
    loadLanguages();
  }, []);

  return (
    <div className="language-dropdown-container container mt-4">
      <form>
        <div className="form-group">
          <label>Select a Language:</label>
          <select
            className={`form-control ${languageError && "is-invalid"}`}
            onChange={(e) => {
              setSelectedLanguageId(e.target.value);
              setLanguageError("");
            }}
            value={selectedLanguageId}
          >
            <option value="">Select a Language</option>
            {languages.map((language) => (
              <option key={language.languageId} value={language.languageId}>
                {language.languageName}
              </option>
            ))}
          </select>
          {languageError && (
            <div className="invalid-feedback">{languageError}</div>
          )}
        </div>

        <div className="form-group">
          <label>Current Fluency Level:</label>
          <select
            className={`form-control ${currentFluencyError && "is-invalid"}`}
            onChange={(e) => {
              setCurrentFluencyLevel(e.target.value);
              setCurrentFluencyError("");
            }}
            value={currentFluencyLevel}
          >
            <option value="">Select Fluency Level</option>
            <option value="A0">A0</option>
            <option value="A1">A1</option>
            <option value="A2">A2</option>
            <option value="B1">B1</option>
            <option value="B2">B2</option>
            <option value="C1">C1</option>
            <option value="C2">C2</option>
          </select>
          {currentFluencyError && (
            <div className="invalid-feedback">{currentFluencyError}</div>
          )}
        </div>

        <div className="form-group">
          <label>Desired Fluency Level:</label>
          <select
            className={`form-control ${desiredFluencyError && "is-invalid"}`}
            onChange={(e) => {
              setDesiredFluencyLevel(e.target.value);
              setDesiredFluencyError("");
            }}
            value={desiredFluencyLevel}
          >
            <option value="">Select Fluency Level</option>
            <option value="A1">A1</option>
            <option value="A2">A2</option>
            <option value="B1">B1</option>
            <option value="B2">B2</option>
            <option value="C1">C1</option>
            <option value="C2">C2</option>
          </select>
          {desiredFluencyError && (
            <div className="invalid-feedback">{desiredFluencyError}</div>
          )}
        </div>

        <div className="form-group">
          <label>Daily Study Time (hours):</label>
          <input
            type="number"
            className={`form-control ${studyTimeError && "is-invalid"}`}
            value={dailyStudyTime}
            onChange={(e) => {
              setDailyStudyTime(e.target.value);
              setStudyTimeError("");
              setStudyTimeRealisticError("");
            }}
          />
          {studyTimeError && (
            <div className="invalid-feedback">{studyTimeError}</div>
          )}
          {studyTimeRealisticError && (
            <div className="invalid-feedback">{studyTimeRealisticError}</div>
          )}
        </div>

        <button
          type="button"
          className="btn btn-primary"
          onClick={handleSubmit}
        >
          Calculate Time to Fluency
        </button>

        {calculatedTimeToFluency !== null && (
          <p className="mt-2">
            Time to Fluency: {calculatedTimeToFluency} days
          </p>
        )}
      </form>
    </div>
  );
};

export default LanguageDropdown;
