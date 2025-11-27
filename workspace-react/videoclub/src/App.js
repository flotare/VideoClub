import './App.css';
import logo from './assets/logo.png';
import ListeFilm from './components/ListeVideo';
import VideoDetails from './pages/VideoDetails';
import AjoutElement from './pages/crud/AjoutElement';
import { Routes, Route, useNavigate } from 'react-router-dom';
import AjoutElementTagsGenre from './pages/crud/AjoutElementTagsGenre';
import AjoutElementVideo from './pages/crud/AjoutElementVideo';
import AjoutElementTagsActeur from './pages/crud/AjoutElementTagsActeur';
import Login from "./pages/Login";
import Register from "./pages/Register";
import Profil from "./pages/Profil";
import { useEffect, useState } from 'react';


function App() {

  const navigate = useNavigate();

  const [user, setUser] = useState(null);

  useEffect(() => {
    const stored = localStorage.getItem("user");
    if (stored) {
      setUser(JSON.parse(stored));
    }
  }, []);

  function handleClickLogo() {
    navigate(`/`);
  }
  function handleClick() {
    navigate(`/admin`);
  }
  function handleConnexionClick() {
    if (user) navigate(`/profil`);
    else navigate(`/login`);
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
            <li onClick={handleConnexionClick}>
              {user ? <span>👤 {user.pseudo}</span> : "Connexion"}
            </li>
          </ul>
        </nav>
      </header>

      <main className="App-body">
        <Routes>
          <Route path="/" element={<ListeFilm />} />
          <Route path="/video/:id" element={<VideoDetails />} />
          <Route path="/login" element={<Login setUser={setUser} />} />
          <Route path="/profil" element={<Profil user={user} setUser={setUser} />} />
          <Route path="/register" element={<Register setUser={setUser} />} />
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
