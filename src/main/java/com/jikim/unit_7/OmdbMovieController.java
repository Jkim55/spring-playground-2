package com.jikim.unit_7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OmdbMovieController {

    private OmdbMovieService omdbMovieService;

    @Autowired
    public void setOmdbMovieService(OmdbMovieService omdbMovieService) {
        this.omdbMovieService = omdbMovieService;
    }

    @GetMapping("/movies")
    public List<OmdbMovie> getMovies(@RequestParam String q) {
        return this.omdbMovieService.getMovies(q);
    }
}
