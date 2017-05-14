package com.miguel.mywatchlist.modelos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Creado por Miguel Ángel Hernández Muñoz
 * para Servicios de Software Ehecatl con fecha 03/05/2017.
 */

public class MovieVideo {
    @SerializedName("id")
    public Integer id;
    @SerializedName("results")
    public ArrayList<Result> results;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }
}
