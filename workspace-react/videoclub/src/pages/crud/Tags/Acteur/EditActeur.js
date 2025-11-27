import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "../../AjoutElement.css";

export default function EditActor() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [actorForm, setActorForm] = useState(null);

    useEffect(() => {
        fetch(`/api/tags/actor/${id}`)
            .then(res => res.json())
            .then(data => {
                setActorForm(data);
                console.log(data);
            })
            .catch(err => console.error(err));
    }, [id]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setActorForm({ ...actorForm, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const res = await fetch(`/api/tags/actor/${id}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(actorForm)
            });

            if (!res.ok) throw new Error("Erreur update");

            alert("Acteur modifié !");
            navigate("/admin/acteur");
        } catch (err) {
            console.error(err);
            alert("Erreur lors de la modification");
        }
    };

    if (!actorForm) return <p>Chargement...</p>;

    return (
        <div className="admin-panel">
            <h2>Modifier un Acteur</h2>

            <form onSubmit={handleSubmit}>
                <label>Prénom</label>
                <input type="text" name="firstName" value={actorForm.firstName || ""} onChange={handleChange} />

                <label>Nom</label>
                <input type="text" name="lastName" value={actorForm.lastName || ""} onChange={handleChange} />

                <button className="btn-add-element" type="submit">Sauvegarder</button>
            </form>
        </div>
    );
}
