package com.jikim.unit_7;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.List;

@Service
public class OmdbMovieService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final OmdbMovieConfig config;

    public OmdbMovieService (OmdbMovieConfig config) {
        this.config = config;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public List<OmdbMovie> getMovies(String title) {
        URI uri = new UriTemplate("{host}/?s={title}").expand(this.config.getUrl(),title);

        RequestEntity request = RequestEntity.get(uri).build();

        ResponseEntity<OmdbApiResponse> responseEntity = this.restTemplate.exchange(
                request,
                OmdbApiResponse.class
        );

        return responseEntity.getBody().getSearch();
    }
}