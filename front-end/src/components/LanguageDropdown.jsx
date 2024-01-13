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
    // Check if a language is selected before making the API call
    if (!selectedLanguageId) {
      console.error("Please select a language");
      return;
    }

    // Check if dailyStudyTime is a positive number
    if (isNaN(dailyStudyTime) || dailyStudyTime <= 0) {
      console.error(
        "Please enter a valid positive number for daily study time"
      );
      return;
    }
    console.log("dailyStudyTime:", dailyStudyTime);
    // Use Axios for the HTTP request
    axios
      .post(
        `http://localhost:8080/api/v1/languages/${selectedLanguageId}/time-to-fluency`,
        {
          dailyStudyTime,
          currentFluencyLevel,
          desiredFluencyLevel,
        }
      )
      .then((response) => {
        setCalculatedTimeToFluency(response.data);
      })
      .catch((error) => {
        console.error("Error calculating time to fluency:", error);
        // Log the error response
        if (error.response) {
          console.error("Response data:", error.response.data);
          console.error("Response status:", error.response.status);
          console.error("Response headers:", error.response.headers);
        }
      });
  };

  useEffect(() => {
    loadLanguages();
  }, []);

  return (
    <div>
      <label>Select a Language:</label>
      <select
        onChange={(e) => setSelectedLanguageId(e.target.value)}
        value={selectedLanguageId}
      >
        {/* Default option */}
        <option value="">Select a Language</option>

        {/* Language options */}
        {languages.map((language) => (
          <option key={language.languageId} value={language.languageId}>
            {language.languageName}
          </option>
        ))}
      </select>

      <label>Current Fluency Level:</label>
      <select
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

      <label>Desired Fluency Level:</label>
      <select
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

      <label>Daily Study Time (hours):</label>
      <input
        type="number"
        value={dailyStudyTime}
        onChange={(e) => setDailyStudyTime(e.target.value)}
      />

      <button onClick={handleSubmit}>Calculate Time to Fluency</button>

      {calculatedTimeToFluency !== null && (
        <p>Time to Fluency: {calculatedTimeToFluency} days</p>
      )}
    </div>
  );
};

export default LanguageDropdown;
