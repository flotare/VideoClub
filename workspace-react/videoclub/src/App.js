import './App.css';
import logo from './assets/logo.png';

import ListeVideo from './components/ListeVideo';
import VideoDetails from './pages/VideoDetails';

import { Routes, Route, useNavigate } from 'react-router-dom';
import AjoutElementTagsGenre from './pages/crud/AjoutElementTagsGenre';
import AjoutElementVideo from './pages/crud/AjoutElementVideo';
import AjoutElementTagsActeur from './pages/crud/AjoutElementTagsActeur';
import Login from "./pages/Login";
import Register from "./pages/Register";
import Profil from "./pages/Profil";
import { useEffect, useState } from 'react';

import AjoutElement from './pages/crud/AdminMainPage';

import GenreAdminManagement from './pages/crud/Tags/Genre/GenreAdminManagement';
import AddGenre from './pages/crud/Tags/Genre/AddGenre'
import EditGenre from './pages/crud/Tags/Genre/EditGenre'

import ActeurAdminManagement from './pages/crud/Tags/Acteur/ActeurAdminManagement';
import AddActeur from './pages/crud/Tags/Acteur/AddActeur';
import EditActor from './pages/crud/Tags/Acteur/EditActeur';

import AjoutElementVideo from './pages/crud/Video/AddVideo';
import ListeVideoAdmin from './pages/crud/Video/VideoAdminManagement';
import VideoDetailsAdmin from './pages/crud/Video/VideoDetailsAdmin';
import EditVideo from './pages/crud/Video/EditVideo';



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
            <li> <img className="App-logo" src={logo} onClick={handleClickLogo} alt="Logo Vidéoclub" /> </li>
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
          <Route path="/" element={<ListeVideo />} />
          <Route path="/video/:id" element={<VideoDetails />} />
          <Route path="/login" element={<Login setUser={setUser} />} />
          <Route path="/profil" element={<Profil user={user} setUser={setUser} />} />
          <Route path="/register" element={<Register setUser={setUser} />} />
          <Route path="/admin" element={<AjoutElement />}>

            <Route path="actor" element={<ActeurAdminManagement />} />
            <Route path="actor/add" element={<AddActeur />} />
            <Route path="actor/edit/:id" element={<EditActor />} />

            <Route path="genre" element={<GenreAdminManagement />} />
            <Route path="genre/add" element={<AddGenre />} />
            <Route path="genre/edit/:id" element={<EditGenre />} />

            <Route path="video" element={<ListeVideoAdmin />} />
            <Route path="video/add" element={<AjoutElementVideo />} />
            <Route path="video/:id" element={<VideoDetailsAdmin />} />
            <Route path="video/edit/:id" element={<EditVideo />} />
            
          </Route>
        </Routes>
      </main>
    </div>
  );
}

export default App;
