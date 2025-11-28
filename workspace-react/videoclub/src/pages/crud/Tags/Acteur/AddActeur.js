import { useState } from "react";
import "../../AjoutElement.css";

export default function AddActeur() {
    // ------------------- STATE FORMULAIRE ACTEUR -------------------
    const [actorForm, setActorForm] = useState({ firstName: "", lastName: "" });


    // ------------------- HANDLERS ACTEUR -------------------
    const handleActorChange = (e) => {
        const { name, value } = e.target;
        setActorForm({ ...actorForm, [name]: value });
    };

    const handleActorSubmit = async (e) => {
        e.preventDefault();
        try {
            const res = await fetch("/api/tags/actor/add", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(actorForm)
            });

            if (!res.ok) throw new Error("Erreur lors de la création de l'acteur");

            alert("Acteur créé avec succès !");
            setActorForm({ firstName: "", lastName: "" });
        } catch (err) {
            console.error(err);
            alert("Erreur lors de la création de l'acteur");
        }
    };

    return (
        <div className="admin-panel"> {/* FORMULAIRE AJOUT TAG ACTEUR */}
            <h2>Ajouter un Tag (Acteur)</h2>
            <form onSubmit={handleActorSubmit}>
                <label>Prénom (ou pseudonyme)</label>
                <input type="text" name="firstName" value={actorForm.firstName} onChange={handleActorChange} required />

                <label>Nom (Optionnel)</label>
                <input type="text" name="lastName" value={actorForm.lastName} onChange={handleActorChange} required />

                <button className="btn-validate" type="submit">Créer le tag acteur</button>
            </form>
        </div>
    )
}