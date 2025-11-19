import './App.css';
import ListeFilm from './ListeFilm';

function App() {
  return (
    <>
      <div className="App">
        <header className="App-header">
          <nav>
            <ul>
              <li> <a href=".">Link1</a> </li>
              <li> <a href=".">Link2</a> </li>
              <li> <a href=".">Link3</a> </li>
            </ul>
          </nav>
        </header>

        <main class="App-body">
          <ListeFilm></ListeFilm>
        </main>
        
      </div>
    </>
  );
}

export default App;
