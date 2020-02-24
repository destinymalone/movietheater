CREATE TABLE IF NOT EXISTS movie_theater (
    id SERIAL PRIMARY KEY,
    director TEXT NOT NULL,
    title TEXT NOT NULL,
    genre TEXT NOT NULL,
    rating INTEGER NOT NULL DEFAULT 0 CHECK (rating >= 0),
    releaseDate DATE NOT NULL
);