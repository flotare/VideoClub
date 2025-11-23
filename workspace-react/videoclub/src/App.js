import './App.css';
import logo from './assets/logo.png';
import ListeFilm from './components/ListeVideo';
import VideoDetails from './pages/VideoDetails';
import AjoutElemendBDD from './pages/AjoutElementBDD';
import { Routes, Route, useNavigate } from 'react-router-dom';


function App() {

  const navigate = useNavigate();


  function handleClickLogo(){
    navigate(`/`);
  }
  function handleClick() {
    navigate(`/admin/database`);
  }


  return (
    <div className="App">
      <header className="App-header">
        <nav>
          <ul>
            <li> <img className="App-logo" src={logo} onClick={handleClickLogo} alt="Logo Vidéclub"/> </li>
            <li onClick={handleClick}>AdminDatabase</li>
            <li> <a href="/">Link2</a> </li>
            <li> <a href="/">Link3</a> </li>
          </ul>
        </nav>
      </header>

      <main class="App-body">
        <Routes>
          <Route path="/" element={<ListeFilm />} />
          <Route path="/video/:id" element={<VideoDetails />} />
          <Route path="/admin/database" element={<AjoutElemendBDD />} />
        </Routes>
      </main>
    </div>
  );
}

export default App;
