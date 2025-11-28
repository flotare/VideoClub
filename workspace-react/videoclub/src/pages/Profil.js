import { useNavigate } from 'react-router-dom';
import "./Profil.css";

export default function Profil({ user, setUser }) {

    const navigate = useNavigate();

    function handleLogout() {
        fetch("/logout", {
            method: "POST",
            credentials: "include"
        })
            .then(() => {
                localStorage.removeItem("user");
                setUser(null);
                navigate("/");
            })
            .catch(err => console.error("Erreur lors de la déconnexion:", err));        
    }

    async function handleDeleteAccount() {
        if (!window.confirm("Voulez-vous vraiment supprimer votre compte ? Cette action est irréversible.")) {
            return;
        }
        try {
            const response = await fetch(`/account/${user.id}`, {
                method: "DELETE",
            });

            if (!response.ok) {
                alert("Erreur lors de la suppression du compte.");
                return;
            }

            localStorage.removeItem("user");
            setUser(null);

            alert("Compte supprimé avec succès.");
            navigate("/");

        } catch (error) {
            console.error("Erreur backend :", error);
            alert("Erreur serveur.");
        }
    }

    return (
        <div className="profil-container">
            <h2 className="profil-title">Profil de {user?.pseudo}</h2>

            <button onClick={handleLogout} className="logout-btn">
                Se déconnecter
            </button>

            <button onClick={handleDeleteAccount} className="delete-btn">
                Supprimer mon compte
            </button>
        </div>
    );
}
