import './App.css';
import logo from './assets/logo.png';
import ListeFilm from './components/ListeVideo';
import VideoDetails from './pages/VideoDetails';
import AjoutElement from './pages/crud/AjoutElement';
import { Routes, Route, useNavigate } from 'react-router-dom';
import AjoutElementTagsGenre from './pages/crud/AjoutElementTagsGenre';
import AjoutElementVideo from './pages/crud/AjoutElementVideo';
import AjoutElementTagsActeur from './pages/crud/AjoutElementTagsActeur';


function App() {

  const navigate = useNavigate();


  function handleClickLogo() {
    navigate(`/`);
  }
  function handleClick() {
    navigate(`/admin`);
  }


  return (
    <div className="App">
      <header className="App-header">
        <nav>
          <ul>
            <li> <img className="App-logo" src={logo} onClick={handleClickLogo} alt="Logo Vidéclub" /> </li>
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
          <Route path="/admin" element={<AjoutElement />}>
            <Route path="video" element={<AjoutElementVideo />} />
            <Route path="genre" element={<AjoutElementTagsGenre />} />
            <Route path="actor" element={<AjoutElementTagsActeur />} />
          </Route>
        </Routes>
      </main>
    </div>
  );
}

export default App;
