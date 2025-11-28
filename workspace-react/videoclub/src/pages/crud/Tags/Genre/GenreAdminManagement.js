import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../../AjoutElement.css";

export default function GenreAdminManagement() {
    const [genres, setGenres] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetch("/api/tags/genres",
            {
                credentials: "include"
            }
        )
            .then(res => res.json())
            .then(data => setGenres(data))
            .catch(err => console.error(err));
    }, []);

    const handleDelete = async (id) => {
        if (!window.confirm("Supprimer ce genre ?")) return;

        try {
            const res = await fetch(`/api/tags/genre/delete/${id}`,
                {
                    method: "DELETE",
                    credentials: "include"
                });


            if (!res.ok) throw new Error("Erreur suppression");

            setGenres(genres.filter(g => g.idTag !== id));
        } catch (err) {
            alert("Erreur lors de la suppression");
            console.error(err);
        }
    };

    return (
        <div className="admin-panel">
            <h2>Gestion des Genres</h2>

            <button className="btn-add" onClick={() => navigate("/admin/genre/add")}>
                ➕ Ajouter un genre
            </button>

            <table className="crud-table">
                <thead>
                    <tr>
                        <th>Nom du Genre</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {genres.map(genre => (
                        <tr key={genre.idTag}>
                            <td>{genre.genreName}</td>
                            <td>
                                <div style={{ display: 'inline-flex', gap: '6px' }}>
                                    <button
                                        className="btn-edit"
                                        onClick={() => navigate(`/admin/genre/edit/${genre.idTag}`)}
                                    >
                                        Modifier
                                    </button>
                                    <button
                                        className="btn-delete"
                                        onClick={() => handleDelete(genre.idTag)}
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
