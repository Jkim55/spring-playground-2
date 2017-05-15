package com.jikim.unit_7;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OmdbApiResponse {
    private List<OmdbMovie> search;

    public OmdbApiResponse(List<OmdbMovie> search) {
        this.search = search;
    }

    public OmdbApiResponse() {
    }

    @JsonProperty("Search")
    public List<OmdbMovie> getSearch() {
        return search;
    }

    public void setSearch(List<OmdbMovie> search) {
        this.search = search;
    }
}