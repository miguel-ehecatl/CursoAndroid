package com.miguel.mywatchlist.modelos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by miguelangel on 4/29/17.
 */

public class Genre {
    @SerializedName("id")
    public Integer id;
    @SerializedName("name")
    public String name;

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
}
