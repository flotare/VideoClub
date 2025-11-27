import { useNavigate } from 'react-router-dom';


export default function Profil({ user, setUser }) {

  const navigate = useNavigate();

  function handleLogout() {
    localStorage.removeItem("user");
    setUser(null);
    navigate("/");
  }

  return (
    <div>
      <h2>Profil de {user?.pseudo}</h2>
      <button onClick={handleLogout}>Se déconnecter</button>
    </div>
  );
}
