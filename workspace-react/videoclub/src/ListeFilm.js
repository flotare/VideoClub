import './ListeFilm.css';

export default function ListeFilm() {

    // Reminder : donner à l'attribut key des li l'id du film pour pouvoir les identifier et charger la bonne page

    return (
        <>
            <ul className="film-list">
                <li>
                    <div className="App-body-image">
                        <img src="https://res.cloudinary.com/dosvno2yl/image/upload/v1763032212/Interstellar_fywuyo.jpg?w=400&h=1000&c=pad" alt="Affiche du film Interstellar" href="."></img>
                        <p href=".">Nom_film</p>
                    </div>
                </li>
                <li>
                    <div className="App-body-image">
                        <img src="https://res.cloudinary.com/dosvno2yl/image/upload/c_pad/v1763032211/Inception_ljbj9z.jpg?w=400&h=1000&c=pad" alt="Affiche du film Inception"></img>
                        <p>Nom_film</p>
                    </div>
                </li>
                <li>
                    <div className="App-body-image">
                        <img src="https://res.cloudinary.com/dosvno2yl/image/upload/c_pad/v1763032211/RetourVersLeFutur_rsqadd.jpg?w=400&h=1000&c=fit" alt="Affiche du film Retour vers le futur"></img>
                        <p>Nom_film</p>
                    </div>
                </li>
            </ul>
        </>)
}