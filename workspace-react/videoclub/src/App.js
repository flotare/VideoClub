import React from 'react';
import './App.css';
import ListeFilm from './ListeFilm';
import Recherche from './Recherche';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';

// Composant simple pour la page d'accueil (à afficher par défaut)
const HomePage = () => (
  <div className="App-intro">
    <h2> Bienvenue au VideoClub !</h2>
    <p> Cliquez sur "Rechercher" pour accéder à la liste et à la barre de recherche.</p>
  </div>
);

function App() {
  return (
   <Router> 
      <div className="App">
        <header className="App-header">
          <nav>
            <ul className="flex justify-center space-x-6 p-4 bg-indigo-600 text-white shadow-md">
              <li> <Link to="/">Accueil</Link> </li> 
              <li> <Link to="/rechercher">Rechercher</Link> </li> 
              <li> <Link to="/link3">Link3</Link> </li>
            </ul>
          </nav>
        </header>

        <main className="App-body">
           <Routes>
            <Route path="/" element={<ListeFilm />} />
            <Route path="/rechercher" element={<Recherche />} />
            <Route path="/link3" element={<h3>Page 3 bientôt disponible</h3>} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;