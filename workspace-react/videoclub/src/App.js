import './App.css';
import logo from './assets/logo.png';

import ListeVideo from './components/ListeVideo';
import VideoDetails from './pages/VideoDetails';

import { Routes, Route, useNavigate } from 'react-router-dom';

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

      <main className="App-body">
        <Routes>
          <Route path="/" element={<ListeVideo />} />
          <Route path="/video/:id" element={<VideoDetails />} />
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
