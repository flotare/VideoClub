import { Link, Outlet } from "react-router-dom";
import "./AjoutElement.css";

export default function AjoutElement() {
  return (
    <div className="admin-panel">
      <h1>Panel Admin</h1>

      <nav className="admin-tabs">
        <Link to="video">➕ Vidéo</Link>
        <Link to="genre">🎭 Genre</Link>
        <Link to="actor">🎬 Acteur</Link>
      </nav>

      <div className="admin-content">
        <Outlet />
      </div>
    </div>
  );
}