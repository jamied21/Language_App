import logo from './logo.svg';
import './App.css';
import {Routes, Route} from 'react-router-dom'
import Homepage from './pages/HomePage';
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle";
import Navbar from './components/Navbar';
import LoadLanguages from './components/LoadLanguages';


function App() {
  return (
    <div className="App">
      <Navbar/>
      <Routes>
        <Route path="/" element = {<Homepage/>}/>
        <Route path="/languages" element = {<LoadLanguages/>}/>
      </Routes>

   
    </div>
  );
}

export default App;
