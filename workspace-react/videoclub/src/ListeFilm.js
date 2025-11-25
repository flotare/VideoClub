import './ListeFilm.css';

import React from 'react';

import { MOCK_FILMS } from './Data';

export default function ListeFilm() {
    
    // Vérification de sécurité pour s'assurer que MOCK_FILMS est un tableau avant d'appeler map
    const filmsToDisplay = MOCK_FILMS || [];
    
    return (
        <div className="p-4 sm:p-8">
            <h2 className="text-4xl font-extrabold text-center mb-10 text-gray-900 border-b pb-4">
                Catalogue Complet du VideoClub
            </h2>
            <ul className="film-list">
                {MOCK_FILMS.map(film => ( 
                    <li 
                        key={film.id}
                        className="App-body-image"
                    >
                        <div className="group">
                            <img 
                                src={film.imageUrl} 
                                alt={`Affiche du film ${film.title}`} 
                                className="w-[200px] h-[300px] object-cover block transition duration-500 group-hover:scale-105"
                            />
                            <p className="text-center p-3 text-base text-gray-700 font-medium">
                                {film.title}
                            </p>
                        </div>
                    </li>
                ))}
            </ul>
            
            <p className="mt-12 text-center text-sm text-gray-500">
                Affichage du catalogue complet ({MOCK_FILMS.length} films - Simulation locale).
            </p>
        </div>
    );
}