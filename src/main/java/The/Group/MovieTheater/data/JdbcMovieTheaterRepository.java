package The.Group.MovieTheater.data;

import The.Group.MovieTheater.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Repository
public class JdbcMovieTheaterRepository implements MovieTheaterRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public JdbcMovieTheaterRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Iterable<Movie> findAll() {
        return jdbc.query("SELECT * from movie_theater", this::mapRowToMovieTheater);
    }

    public Movie findById(Integer id) {
        return jdbc.queryForObject("SELECT id," + "director," + "title," + "genre," + "rating,"
                + "releaseDate FROM movie_theater WHERE id=?", this::mapRowToMovieTheater, id);
    }

    private Movie mapRowToMovieTheater(ResultSet rs, int rowNum) throws SQLException {
        return new Movie(rs.getInt("id"), rs.getString("director"), rs.getString("title"),
                rs.getString("genre"), rs.getInt("rating"), rs.getDate("releaseDate"));
    }

    public Movie save(Movie movie) {
        if (Objects.isNull(movie.id)) {
            return jdbc.queryForObject("INSERT INTO movie_theater (director," + "title," + "genre,"
                            + "rating," + "releaseDate) VALUES (?, ?, ?, ?, ?) "
                            + "RETURNING id," + "director," + "title," + "genre," + "rating," + "releaseDate",
                    this::mapRowToMovieTheater, movie.director, movie.title, movie.genre,
                    movie.rating, movie.releaseDate);
        } else {
            return jdbc.queryForObject("UPDATE movie_theater SET director = ?, " + "title = ?, "
                            + "genre = ?, " + "rating = ?, " + "releaseDate = ?" + "WHERE id = ? RETURNING id," + "director,"
                            + "title," + "genre," + "rating," + "releaseDate", this::mapRowToMovieTheater,
                    movie.director, movie.title, movie.genre, movie.rating,
                    movie.releaseDate, movie.id);
        }
    }
    public void delete(Integer id) {
        jdbc.update("DELETE from movie_theater where id = ?", id);
    }
}
