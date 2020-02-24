package The.Group.MovieTheater.web;

import The.Group.MovieTheater.data.MovieTheaterRepository;
import The.Group.MovieTheater.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie_theater")
public class MovieTheaterController {

    private MovieTheaterRepository movieTheaterRepository;

    @Autowired
    public MovieTheaterController(MovieTheaterRepository movieTheaterRepository) {
        this.movieTheaterRepository = movieTheaterRepository;
}

    @PostMapping
    public Movie submitMovie(@RequestBody Movie movie) {
        return movieTheaterRepository.save(movie);
    }

    @GetMapping
    public Iterable<Movie> allMovieTheaters() {
        return movieTheaterRepository.findAll();
    }

    @GetMapping("/{id}")
    public Movie getById(@PathVariable Integer id) {
        return movieTheaterRepository.findById(id);
    }

    @PatchMapping("/{id}")
    public Movie partialUpdate(@PathVariable Integer id, @RequestBody Movie movie) {
        Movie original = movieTheaterRepository.findById(id);
        original.update(movie);
        return movieTheaterRepository.save(original);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        movieTheaterRepository.delete(id);
    }
}
