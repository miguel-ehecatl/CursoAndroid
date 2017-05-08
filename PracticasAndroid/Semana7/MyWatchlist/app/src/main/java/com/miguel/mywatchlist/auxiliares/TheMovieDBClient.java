package com.miguel.mywatchlist.auxiliares;

import com.miguel.mywatchlist.modelos.MovieResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by miguelangel on 5/6/17.
 */

public interface TheMovieDBClient {

    @GET("/3/search/movie")
    Call<MovieResult> getSearchMovie(@Query("api_key") String api,
                                     @Query("query") String busqueda,
                                     @Query("languaje") String idioma);

}
