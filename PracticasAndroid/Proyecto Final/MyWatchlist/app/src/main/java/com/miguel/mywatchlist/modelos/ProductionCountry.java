package com.miguel.mywatchlist.modelos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by miguelangel on 4/29/17.
 */

public class ProductionCountry {

    @SerializedName("iso_3166_1")
    public String iso31661;
    @SerializedName("name")
    public String name;

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
