import './App.css';
import logo from './assets/logo.png';
import { Routes, Route, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';

// Pages de recherche
import Recherche from './pages/Recherche';
import ListeVideo from './components/ListeVideo';
import VideoDetails from './pages/VideoDetails';


// Pages compte utilisateur
import Login from "./pages/account/Login";
import Register from "./pages/account/Register";
import Profil from "./pages/account/Profil";

// Pages admin
import AdminMainPage from './pages/crud/AdminMainPage';

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
  const ALLOWED_ROLES = ["ROLE_ADMIN"];

  useEffect(() => {
    const stored = localStorage.getItem("user");
    if (stored) {
      setUser(JSON.parse(stored));
    }
  }, []);

  function handleConnexionClick() {
    if (user) navigate(`/profil`);
    else navigate(`/login`);
  }

  function RoleRoute({ user, roles, children }) {
    if (!user) return <h2>⛔ Vous devez être connecté</h2>;
    if (!roles.includes(user.role)) return <h2>⛔ Accès refusé</h2>;

    return children;
  }


  return (
    <div className="App">
      <header className="App-header">
        <nav>
          <ul>
            <li onClick={() => navigate(`/`)}> <img className="App-logo" src={logo} alt="Logo Vidéoclub" /> </li>
            {user && ALLOWED_ROLES.includes(user.role) && <li onClick={() => navigate(`/admin`)}>AdminDatabase</li>}
            <li onClick={() => navigate(`/recherche`)}>Rechercher</li>
            <li onClick={handleConnexionClick}>
              {user ? <span>👤 {user.pseudo}</span> : "Connexion"}
            </li>
          </ul>
        </nav>
      </header>

      <main className="App-body">
        <Routes>
          <Route path="/" element={<ListeVideo />} />
          <Route path="/recherche" element={<Recherche />} />
          <Route path="/video/:id" element={<VideoDetails />} />

          <Route path="/login" element={<Login setUser={setUser} />} />
          <Route path="/profil" element={<Profil user={user} setUser={setUser} />} />
          <Route path="/register" element={<Register setUser={setUser} />} />

          <Route
            path="/admin"
            element={
              <RoleRoute user={user} roles={ALLOWED_ROLES}>
                <AdminMainPage />
              </RoleRoute>}>

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