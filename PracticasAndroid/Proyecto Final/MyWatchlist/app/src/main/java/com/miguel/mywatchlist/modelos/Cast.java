package com.miguel.mywatchlist.modelos;

import com.google.gson.annotations.SerializedName;

/**
 * Creado por Miguel Ángel Hernández Muñoz
 * para Servicios de Software Ehecatl con fecha 05/05/2017.
 */

public class Cast {

    @SerializedName("cast_id")
    public Integer castId;
    @SerializedName("character")
    public String character;
    @SerializedName("credit_id")
    public String creditId;
    @SerializedName("id")
    public Integer id;
    @SerializedName("name")
    public String name;
    @SerializedName("order")
    public Integer order;
    @SerializedName("profile_path")
    public String profilePath;

    public Integer getCastId() {
        return castId;
    }

    public void setCastId(Integer castId) {
        this.castId = castId;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
