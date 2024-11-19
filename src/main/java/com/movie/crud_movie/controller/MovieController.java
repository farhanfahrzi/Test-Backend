package com.movie.crud_movie.controller;

import com.movie.crud_movie.model.Movie;
import com.movie.crud_movie.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public String listMovies(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Movie> movies = (search == null || search.isEmpty())
                ? movieService.getAll()
                : movieService.searchMovies(search);

        if (movies.isEmpty() && search != null) {
            model.addAttribute("errorMessage", "Film Not Found");
        }
        model.addAttribute("movies", movies);
        return "movies/list";
    }

    @GetMapping("/new")
    public String newMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "movies/form";
    }

    @PostMapping
    public String saveMovie(@Valid @ModelAttribute Movie movie, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("movie", movie);
            return "movies/form";
        }
        movieService.create(movie);
        redirectAttributes.addFlashAttribute("successMessage", movie.getId() == null
                ? "Movie successfully added"
                : "Movie successfully updated");
        return "redirect:/movies";
    }


    @GetMapping("/{id}")
    public String editMovieForm(@PathVariable String id, Model model) {
        movieService.getById(id).ifPresent(movie -> model.addAttribute("movie", movie));
        return "movies/form";
    }


    @PostMapping("/{id}/delete")
    public String deleteMovie(@PathVariable String id, RedirectAttributes redirectAttributes) {
        movieService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Movie successfully deleted");
        return "redirect:/movies";
    }
}
