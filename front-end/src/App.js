import logo from './logo.svg';
import './App.css';
import {Routes, Route} from 'react-router-dom'
import Homepage from './pages/HomePage';
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle";
import Navbar from './components/Navbar';

import LanguageDropdown from './components/LanguageDropdown';


function App() {
  return (
    <div className="App">
      <Navbar/>
      <Routes>
        <Route path="/" element = {<Homepage/>}/>
        <Route path="/languages" element = {<LanguageDropdown/>}/>
      </Routes>

   
    </div>
  );
}

export default App;
