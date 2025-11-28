import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../../AjoutElement.css";

export default function ListeActeurAdmin() {
    const [actors, setActors] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetch("/api/tags/actors", {
            credentials: "include"
        })
            .then(res => res.json())
            .then(data => {
                setActors(data);
                console.log(data);
            })
            .catch(err => console.error(err));
    }, []);

    const handleDelete = async (id) => {
        if (!window.confirm("Supprimer cet acteur ?")) return;

        try {
            const res = await fetch(`/api/tags/actor/delete/${id}`,
                {
                    method: "DELETE",
                    credentials: "include"
                });

            if (!res.ok) throw new Error("Erreur suppression");

            setActors(actors.filter(a => a.idTag !== id));

        } catch (err) {
            alert("Erreur lors de la suppression");
            console.error(err);
        }
    };

    return (
        <div className="admin-panel">
            <h2>Gestion des Acteurs</h2>

            <button className="btn-add" onClick={() => navigate("/admin/actor/add")}>
                ➕ Ajouter un acteur
            </button>

            <table className="crud-table">
                <thead>
                    <tr>
                        <th>Prénom</th>
                        <th>Nom</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {actors.map(actor => (
                        <tr key={actor.idTag}>
                            <td>{actor.firstName}</td>
                            <td>{actor.lastName || "-"}</td>
                            <td>
                                <div style={{ display: 'inline-flex', gap: '6px' }}>
                                    <button
                                        className="btn-edit"
                                        onClick={() => navigate(`/admin/actor/edit/${actor.idTag}`)}
                                    >
                                        Modifier
                                    </button>
                                    <button
                                        className="btn-delete"
                                        onClick={() => handleDelete(actor.idTag)}
                                    >
                                        Supprimer
                                    </button>
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}