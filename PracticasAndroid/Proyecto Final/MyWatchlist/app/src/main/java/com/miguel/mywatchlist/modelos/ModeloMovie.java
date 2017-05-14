package com.miguel.mywatchlist.modelos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Creado por Miguel Ángel Hernández Muñoz
 * para Servicios de Software Ehecatl con fecha 17/04/2017.
 */

public class ModeloMovie implements Parcelable{

    @SerializedName("page")
    private Integer page;
    @SerializedName("results")
    private ArrayList<ModeloResult> results;
    @SerializedName("total_results")
    private Integer totalResults;
    @SerializedName("total_pages")
    private Integer totalPages;

    public Integer getPage() {
        return page;
    }

    public ArrayList<ModeloResult> getResults() {
        return results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.page);
        dest.writeTypedList(this.results);
        dest.writeValue(this.totalResults);
        dest.writeValue(this.totalPages);
    }

    public ModeloMovie() {
    }

    protected ModeloMovie(Parcel in) {
        this.page = (Integer) in.readValue(Integer.class.getClassLoader());
        this.results = in.createTypedArrayList(ModeloResult.CREATOR);
        this.totalResults = (Integer) in.readValue(Integer.class.getClassLoader());
        this.totalPages = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<ModeloMovie> CREATOR = new Creator<ModeloMovie>() {
        @Override
        public ModeloMovie createFromParcel(Parcel source) {
            return new ModeloMovie(source);
        }

        @Override
        public ModeloMovie[] newArray(int size) {
            return new ModeloMovie[size];
        }
    };
}
