import './App.css';
import ListeFilm from './components/ListeFilm';
import VideoDetails from './pages/VideoDetails';
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';


function App() {
  return (
    <Router>
      <div className="App">
        <header className="App-header">
          <nav>
            <ul>
              <li> <a href="/">Link1</a> </li>
              <li> <a href="/">Link2</a> </li>
              <li> <a href="/">Link3</a> </li>
            </ul>
          </nav>
        </header>

        <main class="App-body">
          <Routes>
            <Route path="/" element={<ListeFilm/>}/>
            <Route path="/video/:id" element={<VideoDetails/>}/>
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;
