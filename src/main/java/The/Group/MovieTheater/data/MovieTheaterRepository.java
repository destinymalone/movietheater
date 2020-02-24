package The.Group.MovieTheater.data;

import The.Group.MovieTheater.domain.Movie;

public interface MovieTheaterRepository {
    Iterable<Movie> findAll();

    Movie findById(Integer id);

    Movie save(Movie movie);

    void delete(Integer id);
}
