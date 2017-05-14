package com.miguel.mywatchlist.modelos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Creado por Miguel Ángel Hernández Muñoz
 * para Servicios de Software Ehecatl con fecha 05/05/2017.
 */

public class MovieCredits {

    @SerializedName("id")
    public Integer id;
    @SerializedName("cast")
    public ArrayList<Cast> cast;
    @SerializedName("crew")
    public ArrayList<Crew> crew;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Cast> getCast() {
        return cast;
    }

    public void setCast(ArrayList<Cast> cast) {
        this.cast = cast;
    }

    public ArrayList<Crew> getCrew() {
        return crew;
    }

    public void setCrew(ArrayList<Crew> crew) {
        this.crew = crew;
    }
}
