import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const LanguageDropdown = () => {
  const [languages, setLanguages] = useState([]);
  const api = "http://localhost:8080/api/v1/languages";
  const [selectedLanguageId, setSelectedLanguageId] = useState("");
  const [currentFluencyLevel, setCurrentFluencyLevel] = useState("");
  const [desiredFluencyLevel, setDesiredFluencyLevel] = useState("");
  const [dailyStudyTime, setDailyStudyTime] = useState("");
  const [calculatedTimeToFluency, setCalculatedTimeToFluency] = useState(null);

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
    if (!selectedLanguageId) {
      console.error("Please select a language");
      return;
    }

    if (isNaN(dailyStudyTime) || dailyStudyTime <= 0) {
      console.error(
        "Please enter a valid positive number for daily study time"
      );
      return;
    }

    const calculatedTimeToFluency = timeToFluencyCalculator(
      parseFloat(dailyStudyTime),
      currentFluencyLevel,
      desiredFluencyLevel
    );

    setCalculatedTimeToFluency(calculatedTimeToFluency);
  };

  useEffect(() => {
    loadLanguages();
  }, []);

  useEffect(() => {
    loadLanguages();
  }, []);

  return (
    <div className="container mt-4">
      <form>
        <div className="form-group">
          <label>Select a Language:</label>
          <select
            className="form-control"
            onChange={(e) => setSelectedLanguageId(e.target.value)}
            value={selectedLanguageId}
          >
            <option value="">Select a Language</option>
            {languages.map((language) => (
              <option key={language.languageId} value={language.languageId}>
                {language.languageName}
              </option>
            ))}
          </select>
        </div>

        <div className="form-group">
          <label>Current Fluency Level:</label>
          <select
            className="form-control"
            onChange={(e) => setCurrentFluencyLevel(e.target.value)}
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
        </div>

        <div className="form-group">
          <label>Desired Fluency Level:</label>
          <select
            className="form-control"
            onChange={(e) => setDesiredFluencyLevel(e.target.value)}
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
        </div>

        <div className="form-group">
          <label>Daily Study Time (hours):</label>
          <input
            type="number"
            className="form-control"
            value={dailyStudyTime}
            onChange={(e) => setDailyStudyTime(e.target.value)}
          />
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
