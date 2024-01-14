import React, { useState } from "react";
import { FaMoon, FaSun } from "react-icons/fa";
import "../styles/DarkModeToggle.css";

const DarkModeToggle = () => {
  const [isDarkMode, setIsDarkMode] = useState(false);

  const toggleDarkMode = () => {
    setIsDarkMode(!isDarkMode);
    updateDarkModeStyles(!isDarkMode);
  };

  const updateDarkModeStyles = (darkMode) => {
    const body = document.body;
    body.classList.toggle("dark-mode", darkMode);
  };

  return (
    <div
      className={`dark-mode-toggle ${isDarkMode ? "dark" : "light"}`}
      onClick={toggleDarkMode}
    >
      {isDarkMode ? <FaMoon /> : <FaSun />}{" "}
      {/* Use moon or sun icon based on dark mode state */}
    </div>
  );
};

export default DarkModeToggle;
