package The.Group.MovieTheater.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Movie {
    public Integer id;
    public String director;
    public String title;
    public String genre;
    public Integer rating = 0;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date releaseDate;

    public Movie() {
    }

    public Movie(String director, String title, String genre) {
        this.director = director;
        this.title = title;
        this.genre = genre;
    }

    public Movie(Integer id, String director, String title, String genre, Integer rating,
                 Date releaseDate) {
        this.id = id;
        this.director = director;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }

    public void update(Movie movie) {
        if (!Objects.isNull(movie.director)) this.director = movie.director;
        if (!Objects.isNull(movie.title)) this.title = movie.title;
        if (!Objects.isNull(movie.genre)) this.genre = movie.genre;
        if (!Objects.isNull(movie.rating)) this.rating = movie.rating;
        if (!Objects.isNull(movie.releaseDate)) this.releaseDate = movie.releaseDate;
    }
}
