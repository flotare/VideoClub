import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Login({ setUser }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  async function handleSubmit(e) {
    e.preventDefault();
    setError("");

    try {
      const response = await fetch("/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password }),
      });

      if (!response.ok) {
        setError("Email ou mot de passe incorrect");
        return;
      }

      const userData = await response.json();

      if (userData.status !== "success") {
        setError("Email ou mot de passe incorrect");
        return;
      }
      console.log(userData);

      const loggedUser = { id: userData.id, pseudo: userData.pseudo };

      localStorage.setItem("user", JSON.stringify(loggedUser));

      setUser(loggedUser);

      navigate("/");

    } catch (err) {
      setError("Erreur serveur");
    }
  }

  function createAccount() {
    navigate(`/register`);
  }

  return (
    <div className="login-container">
      <h2>Connexion</h2>

      <form onSubmit={handleSubmit}>
        <input 
          type="email" 
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />

        <input 
          type="password" 
          placeholder="Mot de passe"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />

        {error && <p className="error">{error}</p>}

        <button type="submit">Se connecter</button>
        <button type="button" onClick={createAccount}>Créer un compte</button>
      </form>
    </div>
  );
}