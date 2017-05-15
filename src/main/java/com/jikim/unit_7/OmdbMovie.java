package com.jikim.unit_7;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class OmdbMovie {
    private String title;
    private String imdbId;
    private String poster;
    private String year;

    public OmdbMovie(String title, String imdbId, String poster, String year) {
        this.title = title;
        this.imdbId = imdbId;
        this.poster = poster;
        this.year = year;
    }

    public OmdbMovie() {
    }

    @JsonGetter("title")
    public String getTitle() {
        return title;
    }

    @JsonSetter("Title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonGetter("imdbId")
    public String getImdbId() {
        return imdbId;
    }

    @JsonSetter("imdbID")
    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    @JsonGetter("poster")
    public String getPoster() {
        return poster;
    }

    @JsonSetter("Poster")
    public void setPoster(String poster) {
        this.poster = poster;
    }

    @JsonGetter("year")
    public String getYear() {
        return year;
    }

    @JsonSetter("Year")
    public void setYear(String year) {
        this.year = year;
    }
}
