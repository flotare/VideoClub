import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";

export default function Register({ setUser }) {
    const [mailAdress, setMailAdress] = useState("");
    const [password, setPassword] = useState("");
    const [pseudo, setPseudo] = useState("");
    const [error, setError] = useState("");

    const navigate = useNavigate();

    async function handleRegister(e) {
        e.preventDefault();
        setError("");

        try {
            const response = await fetch("/register/normal", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ mailAdress, password, pseudo })
            });

            if (!response.ok) {
                setError("Erreur lors de la création du compte");
                return;
            }

            const data = await response.json();
            console.log("Réponse backend register :", data);

            if (data.status !== "success") {
                setError(data.message || "Impossible de créer le compte");
                return;
            }

            const newUser = {
                id: data.id,
                pseudo: data.pseudo,
                email : data.email,
                role: data.role
            };

            localStorage.setItem("user", JSON.stringify(newUser));
            setUser(newUser);

            navigate("/");
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
                    required
                />

                <input
                    type="text"
                    placeholder="Pseudo"
                    value={pseudo}
                    onChange={e => setPseudo(e.target.value)}
                    required
                />

                <input
                    type="password"
                    placeholder="Mot de passe"
                    value={password}
                    onChange={e => setPassword(e.target.value)}
                    required
                />

                {error && <p className="error">{error}</p>}

                <button type="submit">Créer le compte</button>
                <div style={{ marginTop: "10px" }}>
                    <Link to="/login">
                        Déjà un compte ? Se connecter
                    </Link>
                </div>
            </form>
        </div>
    );
}
