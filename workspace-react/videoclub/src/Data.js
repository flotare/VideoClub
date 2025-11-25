export const MOCK_FILMS = [
    { 
        id: 1, 
        title: "Interstellar", 
        director: "Christopher Nolan", 
        imageUrl: "https://res.cloudinary.com/dosvno2yl/image/upload/v1763032212/Interstellar_fywuyo.jpg?w=400&h=1000&c=pad",
        tags: [
            { idGenre: 1, genreName: "Science-Fiction" },
            { idGenre: 2, genreName: "Espace" },
            { idGenre: 3, genreName: "Voyage dans le temps" }
        ]
    },
    { 
        id: 2, 
        title: "Inception", 
        director: "Christopher Nolan", 
        imageUrl: "https://res.cloudinary.com/dosvno2yl/image/upload/c_pad/v1763032211/Inception_ljbj9z.jpg?w=400&h=1000&c=pad",
        tags: [
            { idGenre: 1, genreName: "Science-Fiction" },
            { idGenre: 4, genreName: "Action" },
            { idGenre: 5, genreName: "Rêves" }
        ]
    },
    { 
        id: 3, 
        title: "Retour Vers Le Futur", 
        director: "Robert Zemeckis", 
        imageUrl: "https://res.cloudinary.com/dosvno2yl/image/upload/c_pad/v1763032211/RetourVersLeFutur_rsqadd.jpg?w=400&h=1000&c=fit",
        tags: [
            { idGenre: 1, genreName: "Science-Fiction" },
            { idGenre: 6, genreName: "Comédie" },
            { idGenre: 3, genreName: "Voyage dans le temps" }
        ]
    },
    { 
        id: 5, 
        title: "Titanic", 
        director: "James Cameron", 
        imageUrl: "https://placehold.co/400x600/374151/FFFFFF?text=Titanic",
        tags: [
            { idGenre: 7, genreName: "Drame" },
            { idGenre: 8, genreName: "Romance" },
            { idGenre: 9, genreName: "Historique" }
        ]
    },
    { 
        id: 6, 
        title: "Le Prestige", 
        director: "Christopher Nolan", 
        imageUrl: "https://placehold.co/400x600/22C55E/FFFFFF?text=Le+Prestige",
        tags: [
            { idGenre: 7, genreName: "Drame" },
            { idGenre: 10, genreName: "Mystère" },
            { idGenre: 11, genreName: "Thriller" }
        ]
    },
    { 
        id: 7, 
        title: "Alien", 
        director: "Ridley Scott", 
        imageUrl: "https://placehold.co/400x600/EF4444/FFFFFF?text=Alien",
        tags: [
            { idGenre: 1, genreName: "Science-Fiction" },
            { idGenre: 12, genreName: "Horreur" },
            { idGenre: 2, genreName: "Espace" }
        ]
    },
];
