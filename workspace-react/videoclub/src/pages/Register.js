import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Register() {
  const [mailAdress, setMailAdress] = useState("");
  const [password, setPassword] = useState("");
  const [pseudo, setPseudo] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  async function handleRegister(e) {
    e.preventDefault();
    setError("");

    try {
      const response = await fetch("/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ mailAdress, password, pseudo })
      });

      if (!response.ok) {
        setError("Erreur lors de la création du compte");
        return;
      }

      navigate("/login");
    } catch (err) {
      setError("Erreur serveur");
    }
  }

  return (
    <div className="register-container">
      <h2>Créer un compte</h2>

      <form onSubmit={handleRegister}>
        <input 
          type="mailAdress"
          placeholder="mailAdress"
          value={mailAdress}
          onChange={e => setMailAdress(e.target.value)}
        />

        <input 
          type="text"
          placeholder="Pseudo"
          value={pseudo}
          onChange={e => setPseudo(e.target.value)}
        />

        <input 
          type="password"
          placeholder="Mot de passe"
          value={password}
          onChange={e => setPassword(e.target.value)}
        />

        {error && <p className="error">{error}</p>}

        <button type="submit">Créer le compte</button>
      </form>
    </div>
  );
}
