import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const LanguageList = () => {
  const [languages, setLanguages] = useState([]);
  const api = "http://localhost:8080/api/v1/languages";
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

  useEffect(() => {
    loadLanguages();
  }, []);

  return <div></div>;
};

export default LanguageList;
