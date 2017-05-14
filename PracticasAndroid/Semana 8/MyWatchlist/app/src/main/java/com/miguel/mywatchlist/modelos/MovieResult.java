package com.miguel.mywatchlist.modelos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by miguelangel on 5/6/17.
 */

public class MovieResult {

    @SerializedName("page")
    private Integer page;
    @SerializedName("results")
    private ArrayList<Result> results;
    @SerializedName("total_results")
    private Integer totalResults;
    @SerializedName("total_pages")
    private Integer totalPages;


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
