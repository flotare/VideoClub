import './App.css';
import logo from './assets/logo.png';
import ListeVideo from './components/ListeVideo';
import VideoDetails from './pages/VideoDetails';
import AjoutElement from './pages/crud/AjoutElement';
import { Routes, Route, useNavigate } from 'react-router-dom';
import AjoutElementTagsGenre from './pages/crud/AjoutElementTagsGenre';
import AjoutElementVideo from './pages/crud/AjoutElementVideo';
import AjoutElementTagsActeur from './pages/crud/AjoutElementTagsActeur';

import ListeVideoAdmin from './pages/crud/ListeVideoAdmin';
import VideoDetailsAdmin from './pages/crud/VideoDetailsAdmin';
import EditVideo from './pages/crud/EditVideo';


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
            <li> <img className="App-logo" src={logo} onClick={handleClickLogo} alt="Logo Vidéoclub" /> </li>
            <li onClick={handleClick}>AdminDatabase</li>
            <li> <a href="/">Link2</a> </li>
            <li> <a href="/">Link3</a> </li>
          </ul>
        </nav>
      </header>

      <main class="App-body">
        <Routes>
          <Route path="/" element={<ListeVideo />} />
          <Route path="/video/:id" element={<VideoDetails />} />
          <Route path="/admin" element={<AjoutElement />}>
            <Route path="video" element={<ListeVideoAdmin />} />
            <Route path="video/add" element={<AjoutElementVideo />} />
            <Route path="video/:id" element={<VideoDetailsAdmin />} />
            <Route path="video/edit/:id" element={<EditVideo />} />
            <Route path="genre" element={<AjoutElementTagsGenre />} />
            <Route path="actor" element={<AjoutElementTagsActeur />} />
          </Route>
        </Routes>
      </main>
    </div>
  );
}

export default App;
